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

import grails.plugin.spock.UnitSpec
import org.joda.time.DateTime

class FormatSpec extends UnitSpec {

	def "Default type of Format is ORIGINAL"() {
		given:
		mockDomain Picture
		def picture = new Picture()

		when:
		def format = new Format(picture: picture)

		then:
		format.type == FormatType.ORIGINAL
	}

	def "Format fails validation with missing properties"() {
		setup:
		mockForConstraintsTests Format

		when:
		def format = new Format()

		then:
		!format.validate()
		format.errors.hasFieldErrors("width")
		format.errors.hasFieldErrors("height")
		format.errors.hasFieldErrors("path")
		format.errors.hasFieldErrors("picture")
	}

	def "Format with correct properties set validates"() {
		setup:
		mockDomain Picture
		def picture = new Picture()
		mockDomain Format

		when:
		def format = new Format(
				width: 100, height: 200, path: "/some/path/to/file.JPG", picture: picture
		)

		then:
		format.validate()
	}

	def "Only one format can exist with a given path"() {
		given:
		mockDomain Format
		mockDomain Picture
		def picture = new Picture()

		and: "a format exists with some path"
		def format1 = new Format(width: 100, height: 200, path: "/some/path/to/file.jpg", picture: picture)

		when: "Then first format is saved"
		format1.save(failOnError: true)

		and: "New format with the same path is crated"
		def format2 = new Format(width: 200, height: 300, path: "/some/path/to/file.jpg", picture: picture)

		then: "Validation fails for the second format"
		!format2.validate()
	}

	def "Format type sets width from hint if hint exists and width is not set"() {
		given:
		def picture = new Picture()

		when: "A format is created with LARGE type"
		def format = new Format(height: 200, path: "/file.jpg", picture: picture)
		format.type = FormatType.LARGE

		then:
		format.type == FormatType.LARGE
		format.width == FormatType.LARGE.formatTypeWidth

		when: "A format is created with LARGE type and existing width"
		def format1 = new Format(width: 100, height: 200, path: "/file.jpg")
		format1.type = FormatType.LARGE
		
		then:
		format1.width == 100
	}

	def "Creates correct path for a new image"() {
		given:

		mockConfig("""umbra.image.base.dir = new File("/tmp/umbra")
					  umbra.image.base.url = "http://static.floryan.pl/images"
					  """)

		def p = new Picture(dateTaken: new DateTime(2010,10,12,19,12,0,0), originalFilename: "IMG_1234.JPG")
		def f1 = new Format(type:FormatType.ORIGINAL, picture:p)
		def f2 = new Format(type:FormatType.THUMBNAIL, picture:p)

		when:
		f1.generatePath()
		f2.generatePath()

		def path1 = f1.file
		def path2 = f2.file

		then:
		path1.toString() == "/tmp/umbra/2010/10/img_1234.jpg"
		path2.toString() == "/tmp/umbra/2010/10/img_1234-thumbnail.jpg"
	}
}
