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
import org.joda.time.DateTime

/* Created 07-Nov-2010 22:38:36 by mfloryan */

class ImageUtil {
	static ImageInfo getImageProperties(InputStream stream) {

		Metadata m = JpegMetadataReader.readMetadata(stream)


		def jdir = m.getDirectory(JpegDirectory.class)
		if (jdir == null) {
			throw new IllegalArgumentException("Invalid file - could not find JPEG metadata")
		}

		if (!jdir.containsTag(JpegDirectory.TAG_JPEG_IMAGE_HEIGHT) ||
			!jdir.containsTag(JpegDirectory.TAG_JPEG_IMAGE_WIDTH)) {
			throw new IllegalArgumentException("File must contain EXIF width and height")
		}

		new ImageInfo(  width: jdir.getInt(JpegDirectory.TAG_JPEG_IMAGE_WIDTH),
						height: jdir.getInt(JpegDirectory.TAG_JPEG_IMAGE_HEIGHT))
	}
}

class ImageInfo {
	int width
	int height
	long size
	DateTime dateTaken = new DateTime()
}