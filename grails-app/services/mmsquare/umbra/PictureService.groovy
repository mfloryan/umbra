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

import com.drew.imaging.jpeg.JpegMetadataReader
import com.drew.metadata.Metadata
import com.drew.metadata.jpeg.JpegDirectory
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.springframework.web.multipart.commons.CommonsMultipartFile

class PictureService {

	static transactional = true

	List<Picture> getAvailablePictures() {
		Picture.executeQuery("select p from Picture p where p.id not in (select p2.id from Entry e left join e.pictures as p2)")
	}

	private static final File tempDir = new File(System.properties."java.io.tmpdir")

	def saveUploadedPicture(CommonsMultipartFile picture) {
		def p = new Picture()
		p.originalFilename = picture.originalFilename

		Metadata m = JpegMetadataReader.readMetadata(picture.inputStream)

		def jdir = m.getDirectory(JpegDirectory.class)
		if (jdir == null) {
			throw new IllegalArgumentException("Invalid file - could not find JPEG metadata")
		}

		if (!jdir.containsTag(JpegDirectory.TAG_JPEG_IMAGE_HEIGHT) || !jdir.containsTag(JpegDirectory.TAG_JPEG_IMAGE_WIDTH)) {
			throw new IllegalArgumentException("File must contain EXIF width and height")
		}

		// Retrieve width and height
		def imageWidth = jdir.getInt(JpegDirectory.TAG_JPEG_IMAGE_WIDTH)
		def imageHeight = jdir.getInt(JpegDirectory.TAG_JPEG_IMAGE_HEIGHT)

		println "$imageWidth x $imageHeight"

		def destDir = new File(ConfigurationHolder.config.umbra.image.base.dir,"/2010/10/") //from DateTake or DT-Now()
		if (!destDir.isDirectory()) {
			destDir.mkdirs()
		}
		def baseOriginalName = picture.originalFilename.toLowerCase()[0..-5]
		def newName = "$baseOriginalName-${imageWidth}x${imageHeight}.jpg"
		def newFile = new File(destDir, newName)
		picture.transferTo(newFile)
//		p.save()
	}

}
