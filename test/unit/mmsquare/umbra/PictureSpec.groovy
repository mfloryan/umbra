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
import static mmsquare.umbra.FormatType.*

class PictureSpec extends UnitSpec {

	def "picture with required values can be created"() {
		setup:
		mockDomain Picture

		when:
		def p = new Picture(dateTaken: new DateTime())

		then:
		p.validate()
	}

	def "picture provides list of required FORMATS given original's width"() {
		when:
		def types = Picture.getFormatTypesForWidth(width)

		then:
		types == formatTypes

		where:
		width | formatTypes
		10    | []
		120   | [THUMBNAIL]
		640   | [THUMBNAIL]
		800   | [SMALL, THUMBNAIL]
		1200  | [SMALL, THUMBNAIL]
		1400  | [LARGE, SMALL, THUMBNAIL ]
	}
}
