package mmsquare.umbra.admin

import org.springframework.web.multipart.commons.CommonsMultipartFile

class PictureController {

    def upload = {
	    
    }

	def uploaded = {
		List<CommonsMultipartFile> pictures = [params.picture1, params.picture2, params.picture3]
		if (pictures.every {it.empty}) {
			flash.message = "Please upload some files"
			redirect(action:"upload")
		} else {
			if (pictures.any {!it.empty && it.contentType != "image/jpeg"}) {
				flash.message = "Please only upload JPEG images"
				redirect(action:"upload")
			} else {
				def uploadedFiles = []
				//TODO: Save images to some temp location and move on?
				pictures.each {
					if (!it.empty) {
						uploadedFiles << [filename: it.originalFilename, size: it.size]
					}
				}
				redirect(controller: "entry", action: "create", params: [files: uploadedFiles])
			}
		}
	}
}
