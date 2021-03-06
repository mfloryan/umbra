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
import com.drew.metadata.exif.ExifDirectory
import com.drew.metadata.exif.GpsDirectory
import com.drew.metadata.jpeg.JpegDirectory
import org.joda.time.DateTime
import org.im4java.core.ConvertCmd
import org.im4java.core.IMOperation

/* Created 07-Nov-2010 22:38:36 by mfloryan */

class ImageUtil {

	private static ConvertCmd cmd = new ConvertCmd()

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

		def ii = new ImageInfo(width: jdir.getInt(JpegDirectory.TAG_JPEG_IMAGE_WIDTH),
				height: jdir.getInt(JpegDirectory.TAG_JPEG_IMAGE_HEIGHT))

		def exifDirectory = m.getDirectory(ExifDirectory.class)
		if (exifDirectory) {
			if (exifDirectory.containsTag(ExifDirectory.TAG_DATETIME_ORIGINAL)) {
				ii.dateTaken = new DateTime(exifDirectory.getDate(ExifDirectory.TAG_DATETIME_ORIGINAL))
			}
			ii.cameraModel = exifDirectory.getString(ExifDirectory.TAG_MODEL)
			ii.cameraMake = exifDirectory.getString(ExifDirectory.TAG_MAKE)
		}

		def gpsDirectory = m.getDirectory(GpsDirectory.class)
		if (gpsDirectory) {
			ii.latitude = gpsDirectory.getDescription(GpsDirectory.TAG_GPS_LATITUDE)
			ii.longitude = gpsDirectory.getDescription(GpsDirectory.TAG_GPS_LONGITUDE)
			ii.altitude = gpsDirectory.getDescription(GpsDirectory.TAG_GPS_ALTITUDE)
		}
		ii
	}

	static ImageInfo getImageDimensions(File file) {
		Metadata m = JpegMetadataReader.readMetadata(file)

		def jdir = m.getDirectory(JpegDirectory.class)
		if (jdir == null) {
			throw new IllegalArgumentException("Invalid file - could not find JPEG metadata")
		}

		if (!jdir.containsTag(JpegDirectory.TAG_JPEG_IMAGE_HEIGHT) ||
				!jdir.containsTag(JpegDirectory.TAG_JPEG_IMAGE_WIDTH)) {
			throw new IllegalArgumentException("File must contain EXIF width and height")
		}
		new ImageInfo(width: jdir.getInt(JpegDirectory.TAG_JPEG_IMAGE_WIDTH),
						   height: jdir.getInt(JpegDirectory.TAG_JPEG_IMAGE_HEIGHT))
	}

	static void resizeImage(String sourceImage, String targetImage, FormatType type) {
		IMOperation op = new IMOperation()
		op.addImage sourceImage
		if (type == FormatType.THUMBNAIL) {
			op.thumbnail(type.formatTypeWidth,type.formatTypeWidth,"^").gravity("center").crop(type.formatTypeWidth,type.formatTypeWidth,0,0)
		} else {
			op.resize(type.formatTypeWidth)
		}
		op.addImage targetImage
		cmd.run(op);
	}
}

class ImageInfo {
	int width
	int height
	long size
	DateTime dateTaken = new DateTime()
	String cameraMake
	String cameraModel
	String latitude
	String longitude
	String altitude
}