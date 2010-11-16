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

package mmsquare.umbra

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND

class UmbraPictureController {

	def show = {
		Picture picture = Picture.get(params.id)
		if (!picture) {
			response.sendError SC_NOT_FOUND
			return
		}
		Format image = picture.getFormatBy(FormatType.valueOf(params.format.toUpperCase())) 
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
