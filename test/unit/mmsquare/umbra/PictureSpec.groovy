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
import spock.lang.Unroll

class PictureSpec extends UnitSpec {

	def "picture with required values can be created"() {
		setup:
		mockDomain Picture

		when:
		def p = new Picture(dateTaken: new DateTime(), originalFilename: "IMG123.JPG")

		then:
		p.validate()
	}

	@Unroll("Can get format from picture for #formatToGet")
	def "can get format from picture by format type"() {
		setup:
		def p = new Picture(dateTaken: new DateTime(), originalFilename: "IMG.jpg")
		def f = new Format(picture:p)
		def f1 = new Format(picture:p, type:FormatType.THUMBNAIL)
		def f2 = new Format(picture:p, type:FormatType.SMALL)
		p.formats = [f, f1, f2]

		when:
		def format = p.getFormatBy(formatToGet)

		then:
		if (expectedFormat) {
			assert format.type == expectedFormat
		} else {
			assert !format 
		}

		where:
		formatToGet         | expectedFormat
		FormatType.ORIGINAL | FormatType.ORIGINAL
		FormatType.SMALL    | FormatType.SMALL
		FormatType.LARGE    | null

	}
}