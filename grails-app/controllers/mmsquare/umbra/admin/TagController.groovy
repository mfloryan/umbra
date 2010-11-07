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

import mmsquare.umbra.Tag

class TagController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index = {
		redirect(action: "list", params: params)
	}

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[tagInstanceList: Tag.list(params), tagInstanceTotal: Tag.count()]
	}

	def create = {
		def tagInstance = new Tag()
		tagInstance.properties = params
		return [tagInstance: tagInstance]
	}

	def save = {
		def tagInstance = new Tag(params)
		if (tagInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'tag.label', default: 'Tag'), tagInstance.id])}"
			redirect(action: "show", id: tagInstance.id)
		}
		else {
			render(view: "create", model: [tagInstance: tagInstance])
		}
	}

	def show = {
		def tagInstance = Tag.get(params.id)
		if (!tagInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tag.label', default: 'Tag'), params.id])}"
			redirect(action: "list")
		}
		else {
			[tagInstance: tagInstance]
		}
	}

	def edit = {
		def tagInstance = Tag.get(params.id)
		if (!tagInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tag.label', default: 'Tag'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [tagInstance: tagInstance]
		}
	}

	def update = {
		def tagInstance = Tag.get(params.id)
		if (tagInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (tagInstance.version > version) {

					tagInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tag.label', default: 'Tag')] as Object[], "Another user has updated this Tag while you were editing")
					render(view: "edit", model: [tagInstance: tagInstance])
					return
				}
			}
			tagInstance.properties = params
			if (!tagInstance.hasErrors() && tagInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tag.label', default: 'Tag'), tagInstance.id])}"
				redirect(action: "show", id: tagInstance.id)
			}
			else {
				render(view: "edit", model: [tagInstance: tagInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tag.label', default: 'Tag'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def tagInstance = Tag.get(params.id)
		if (tagInstance) {
			try {
				tagInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tag.label', default: 'Tag'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tag.label', default: 'Tag'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tag.label', default: 'Tag'), params.id])}"
			redirect(action: "list")
		}
	}
}
