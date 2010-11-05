/*
 * Copyright (c) 2010 Marcin Floryan. http://www.mmsquare.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
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

		"/"(controller: "umbra", action: 'list') {
			page = 1
		}

		"/page/$page"(controller: "umbra", action: 'list')

		"/person/$person/page?/$page?"(controller: "umbra", action: 'list')

		"/admin/" (controller: 'entry', action: "index")

		"/admin/$controller/$action?/$id?"

		"500"(controller: 'umbra', action: 'list')
	}
}
