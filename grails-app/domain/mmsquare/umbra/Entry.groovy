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

package mmsquare.umbra

import org.joda.time.DateTime
import org.joda.time.LocalDate

class Entry {

	String permalink
	DateTime publishDate
	String title
	String content
	List<Picture> pictures

	DateTime dateCreated
	DateTime lastUpdated

	static hasMany = [
		tags: Tag,
		pictures: Picture
	]

	static constraints = {
		permalink(blank: false, unique: true, maxSize: 1500)
		title(blank: false)
		content(nullable: true, maxSize: 5000)
	}

	String toString() {
		"Entry:$id ($title)"
	}

}
