/*
 * Copyright (c) 2010 Marcin Floryan. http://www.mmsquare.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mmsquare.umbra.admin

import mmsquare.umbra.Entry
import mmsquare.umbra.Picture

class EntryController {

	def pictureService

    static allowedMethods = [save: "POST", update: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
	    if (!params.sort && !params.order) {
		    params.sort = "publishDate"
		    params.order = "desc"
	    }
        [entryInstanceList: Entry.list(params), entryInstanceTotal: Entry.count()]
    }

    def create = {
        def entryInstance = new Entry()
	    def pictures = pictureService.getAvailablePictures()
	    if (!params.publishDate && pictures) params.publishDate = pictures[0].dateTaken
        entryInstance.properties = params
        return [entryInstance: entryInstance, pictures: pictures]
    }

    def save = {
		def pictureData = params.picture.findAll { index, value -> index.isNumber() }
		pictureData.each { id, data ->
			def p = Picture.get(id)
			if (p) {
				p.properties = data
				p.save(failOnError: true)
			}
		}

        def entryInstance = new Entry(params)
		if (!entryInstance.permalink) entryInstance.createPermalink()
        if (entryInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'entry.label', default: 'Entry'), entryInstance.id])}"
            redirect(action: "show", id: entryInstance.id)
        }
        else {
            render(view: "create", model: [entryInstance: entryInstance])
        }
    }

    def show = {
        def entryInstance = Entry.get(params.id)
        if (!entryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'entry.label', default: 'Entry'), params.id])}"
            redirect(action: "list")
        }
        else {
            [entryInstance: entryInstance]
        }
    }

    def edit = {
        def entryInstance = Entry.get(params.id)
        if (!entryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'entry.label', default: 'Entry'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [entryInstance: entryInstance]
        }
    }

    def update = {
        def entryInstance = Entry.get(params.id)
        if (entryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (entryInstance.version > version) {
                    
                    entryInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'entry.label', default: 'Entry')] as Object[], "Another user has updated this Entry while you were editing")
                    render(view: "edit", model: [entryInstance: entryInstance])
                    return
                }
            }
            entryInstance.properties = params
            if (!entryInstance.hasErrors() && entryInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'entry.label', default: 'Entry'), entryInstance.id])}"
                redirect(action: "show", id: entryInstance.id)
            }
            else {
                render(view: "edit", model: [entryInstance: entryInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'entry.label', default: 'Entry'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def entryInstance = Entry.get(params.id)
        if (entryInstance) {
            try {
                entryInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'entry.label', default: 'Entry'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'entry.label', default: 'Entry'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'entry.label', default: 'Entry'), params.id])}"
            redirect(action: "list")
        }
    }


	def permalink = {
		def entry = new Entry(params)
		if (entry.title) {
			entry.createPermalink()
			render entry.permalink
		} else {
			response.sendError javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST, "Missing title"
		}
	}
}
