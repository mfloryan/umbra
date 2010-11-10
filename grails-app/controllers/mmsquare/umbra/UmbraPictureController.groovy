package mmsquare.umbra

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND

class UmbraPictureController {

	def show = {
		Picture picture = Picture.get(params.id)
		if (!picture) {
			response.sendError SC_NOT_FOUND
			return
		}
		Format image = picture.getFormatBy(FormatType.valueOf(params.type.toUpperCase())) 
		if (!image || !image.file.exists()) {
			log.debug "Image not found: $params.path"
			response.sendError SC_NOT_FOUND
			return
		}
		if (params.download) {
			response.addHeader "Content-disposition","attachment; filename=${picture.originalFilename}"
		}

		response.contentType = "image/jpeg"
		response.contentLength = image.file.length()
		response.outputStream << image.file.newInputStream()
	}
}
