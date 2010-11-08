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

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class Format {

	int width
	int height
	int size
	String path
	FormatType type = FormatType.ORIGINAL

	static belongsTo = [picture: Picture]

	static constraints = {
		width(notEqual: 0)
		height(notEqual: 0)
		path(blank: false, unique: true)
	}

	static transients = ["url", "file", "fileName", "relativePath","fileDir"]

	void setType(FormatType type) {
		this.type = type
		if (type.formatTypeWidth && !width) width = type.formatTypeWidth
	}

	FormatType getType() {
		this.type
	}

	String getUrl() {
		imagesBaseUrl + path
	}

	File getFile() {
		new File(imagesBaseDir, path)
	}

	String getFileName(String originalName) {
		originalName = originalName.toLowerCase()[0..-5]
		"${originalName}-${width}x${height}.jpg"
	}

	String getRelativePath() {
		"/${picture.dateTaken.year}/${picture.dateTaken.monthOfYear}/"
	}

	File getFileDir() {
		if (!picture) throw new InvalidObjectException()
		def dir = new File(imagesBaseDir + relativePath)
		if (!dir.isDirectory()) {
			dir.mkdirs()
		}
		dir
	}

	static File getImagesBaseDir() {
		config.umbra.image.base.dir
	}

	static String getImagesBaseUrl() {
		config.umbra.image.base.url
	}

	private static ConfigObject getConfig() {
		ConfigurationHolder.config
	}
}

enum FormatType {
	ORIGINAL, LARGE(1200), SMALL(640), THUMBNAIL(100)
	public final int formatTypeWidth

	FormatType(int width) {
		this.formatTypeWidth = width
	}

	FormatType() {

	}

	def static getAllForWidth(int width) {
		def availableFormats = FormatType.values() - FormatType.ORIGINAL

		def types = []
		availableFormats.each {
			if (width > it.formatTypeWidth) types << it
		}
		types
	}
}