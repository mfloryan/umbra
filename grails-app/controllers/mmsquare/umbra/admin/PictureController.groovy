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

import mmsquare.umbra.Picture
import org.springframework.web.multipart.commons.CommonsMultipartFile
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap

class PictureController {

	def pictureService

	def index = {
		redirect(action: "list", params: params)
	}

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[pictureInstanceList: Picture.list(params), pictureInstanceTotal: Picture.count()]
	}


	def upload = {

	}

	def uploadedForEntry = {
		if (processUploadedPictures()) {
			redirect(controller: "entry", action: "create")
		} else {
			redirect(action: "upload")
		}
	}

	def uploaded = {
		processUploadedPictures()
		redirect(action: "upload")
	}

	private boolean processUploadedPictures() {
		List<CommonsMultipartFile> pictures = [params.picture1, params.picture2, params.picture3]
		if (pictures.every {it.empty}) {
			flash.message = "Please upload some files"
			return false
		} else {
			if (pictures.any {!it.empty && it.contentType != "image/jpeg"}) {
				flash.message = "Please only upload JPEG images"
				return false
			} else {
				pictures.each {
					if (!it.empty) {
						pictureService.saveUploadedPicture(it)
					}
				}
				flash.message = "Pictures uploaded successfully"
				return true
			}
		}
	}

	def delete = {
		def pictureInstance = Picture.get(params.id)
		if (pictureInstance) {
			try {
				pictureInstance.delete(flush: true)
				flash.message = "Picture (${params.id}) deleted"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "Picture (${params.id}) not deleted"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "Picture (${params.id}) not found"
			redirect(action: "list")
		}
	}

	def show = {
		getPictureInstance(params)
	}

	private def getPictureInstance(GrailsParameterMap params) {
		def pictureInstance = Picture.get(params.id)
		if (!pictureInstance) {
			flash.message = "Picture ${params.id} not found"
			redirect(action: "list")
		}
		else {
			[pictureInstance: pictureInstance]
		}
	}

	def edit = {
		getPictureInstance(params)
	}

	def update = {
		def pictureInstance = Picture.get(params.id)
		if (pictureInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (pictureInstance.version > version) {

					pictureInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tag.label', default: 'Picture')] as Object[], "Another user has updated this Picture while you were editing")
					render(view: "edit", model: [pictureInstance: pictureInstance])
					return
				}
			}
			pictureInstance.properties = params
			if (!pictureInstance.hasErrors() && pictureInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tag.label', default: 'Picture'), pictureInstance.id])}"
				redirect(action: "show", id: pictureInstance.id)
			}
			else {
				render(view: "edit", model: [pictureInstance: pictureInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tag.label', default: 'Picture'), params.id])}"
			redirect(action: "list")
		}
	}

}
