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

import org.springframework.web.multipart.commons.CommonsMultipartFile

class PictureService {

	static transactional = true

	List<Picture> getAvailablePictures() {
		Picture.executeQuery("select p from Picture p where p.id not in (select p2.id from Entry e left join e.pictures as p2)")
	}

	def saveUploadedPicture(CommonsMultipartFile picture) {
		def p = new Picture()
		p.originalFilename = picture.originalFilename

		def imageInfo = ImageUtil.getImageProperties(picture.inputStream)
		p.dateTaken = imageInfo.dateTaken

		Format originalFormat = new Format(width: imageInfo.width, height:imageInfo.height)
		p.addToFormats(originalFormat)
		picture.transferTo(originalFormat)
//		p.save()
	}

	def private Format createImageFormat(File image, FormatType formatType) {
		if (formatType != FormatType.ORIGINAL) {
			//resize
		}
		new Format(width:0, height:0, size: 0, path: "")
	}

}
