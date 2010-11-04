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
//		"/$controller/$action?/$id?"{
//			constraints {
//				// apply constraints here
//			}
//		}

    "/$year/$month/$id"(controller: "entry", action: "show") {
      constraints {
        year(matches: /\d{4}/)
        month(matches: /\d{2}/)
      }
    }

    "/$lang/"(controller: "entry")

	"/page/$page"(controller: "entry", action:'list')

	"/person/$person"(controller: "entry", action:'list')

	"/person/$person/page/$page"(controller: "entry", action:'list')

	"/"(controller: "entry", action:'list')

    "500"(controller: 'entry', action: 'list')
  }
}
