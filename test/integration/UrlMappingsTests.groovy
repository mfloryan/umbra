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

import grails.test.GrailsUrlMappingsTestCase
import org.junit.Test

class UrlMappingsTests extends GrailsUrlMappingsTestCase {

	static mappings = UrlMappings

	@Test
	void "List url mappings"() {

		assertForwardUrlMapping("/", controller: "umbra", action: "list")

		assertForwardUrlMapping("/page/2", controller: "umbra", action: "list") {
			page = 2
		}

		assertForwardUrlMapping("/person/Zosia", controller: "umbra", action: "list") {
			person = "Zosia"
		}

		assertForwardUrlMapping("/person/Zosia/page/3", controller: "umbra", action: "list") {
			person = "Zosia"
			page = 3
		}
	}

	@Test
	void "Entry url mappings"() {

		assertUrlMapping("/2010/08/daymark", controller: "umbra", action: "show") {
			year = '2010'
			month = '08'
			id = 'daymark'
		}
	}

	@Test
	void "Image controller mappings"() {

		assertUrlMapping("/picture/1/original.jpg", controller: "umbraPicture", action: "show") {
			id = 1
			format = 'original'
		}
	}

	@Test
	void "Admin site mappings"() {
		assertForwardUrlMapping("/admin/entry/create", controller: "entry", action: "create")
		assertForwardUrlMapping("/admin/entry/edit/1", controller: "entry", action: "edit") {
			id = 1
		}
		assertForwardUrlMapping("/admin/entry/list", controller: "entry", action: "list")
		assertForwardUrlMapping("/admin/entry/show/2", controller: "entry", action: "show") {
			id = 2
		}
		assertForwardUrlMapping("/admin/picture/upload", controller: "picture", action: "upload")
		assertForwardUrlMapping("/admin/tag/list", controller: "tag", action: "list")
	}
}
