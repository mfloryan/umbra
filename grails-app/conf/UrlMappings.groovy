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

class UrlMappings {

	static mappings = {

		"/$year/$month/$id"(controller: "umbra", action: "show") {
			constraints {
				year(matches: /\d{4}/)
				month(matches: /\d{2}/)
			}
		}

		"/admin/" (controller: "entry", action: "list")
//
//		"/admin/$controller/$action?/$id?"

		"/admin/entry/$action/$id?"(controller: "entry")
		"/admin/tag/$action/$id?"(controller: "tag")
		"/admin/picture/$action/$id?"(controller: "picture")
		"/admin/person/$action/$id?"(controller: "person")
		"/admin/auth/$action/$id?"(controller: "auth")

		"/picture/$id/${format}.jpg" (controller:"umbraPicture", action: "show")

		"/"(controller: "umbra", action: 'list') {
			page = 1
		}

		"/page/$page"(controller: "umbra", action: 'list')

		"/person/$person/page?/$page?"(controller: "umbra", action: 'list')

		"404"(controller: 'umbra', action: 'list')
		"500"(view: 'error')
	}
}
