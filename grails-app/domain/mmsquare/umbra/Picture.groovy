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

import org.joda.time.DateTime

class Picture {

	String title
	DateTime dateTaken
	String originalFilename

	static hasMany = [
			people: Person,
			formats: Format
	]

//	static fetchMode = [formats:"eager", people:"eager"]

	static constraints = {
		title(nullable: true, maxSize: 400)
		originalFilename(blank: false, maxSize: 400)
	}

	String toString() {
		"Picture:$id ($originalFilename)"
	}
}