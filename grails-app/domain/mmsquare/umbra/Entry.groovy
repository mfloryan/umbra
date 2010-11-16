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

import mmsquare.umbra.util.StringUtil
import org.joda.time.DateTime

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
		permalink(blank: false, unique: true, maxSize: 1500, matches:/\/[0-9]{4}\/[0-9]{2}\/[-\w]+/)
		title(blank: false, maxSize: 400)
		content(nullable: true, maxSize: 5000)
	}

	String toString() {
		"Entry:$id ($title)"
	}

	public createPermalink() {
		if (!permalink && title && publishDate) {
			permalink = "/${publishDate.year.toString().padLeft(4,'0')}/${publishDate.monthOfYear.toString().padLeft(2,'0')}/${StringUtil.clean(title)}"			
		}
	}
}
