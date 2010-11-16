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
import spock.lang.Unroll
import static mmsquare.umbra.FormatType.*

/* Created 08-Nov-2010 12:29:13 by mfloryan */

class FormatTypeSpec extends UnitSpec {

	@Unroll("Picture #width px wide requires #formatTypes format types")
	def "FormatType provides list of required FORMATS given original's width"() {
		when:
		def types = FormatType.getAllForWidth(width)

		then:
		types == formatTypes

		where:
		width | formatTypes
		10    | []
		120   | [THUMBNAIL]
		480   | [THUMBNAIL]
		640   | [SMALL, THUMBNAIL]
		800   | [SMALL, THUMBNAIL]
		1200  | [SMALL, THUMBNAIL]
		1400  | [LARGE, SMALL, THUMBNAIL]
	}
}
