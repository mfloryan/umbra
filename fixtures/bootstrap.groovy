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

import mmsquare.umbra.Entry
import org.joda.time.LocalDate
import mmsquare.umbra.Picture
import org.joda.time.DateTime
import mmsquare.umbra.Format
import mmsquare.umbra.FormatType

fixture {
	build {
		photoOne(Picture) {
			dateTaken = new DateTime()
		}
		photoTwo(Picture) {
			dateTaken = new DateTime().minusDays(10)
		}
	}
	format1(Format) {
		width = 480
		height = 320
		path = "/2010/10/IMAGE1-640x320.jpg"
		type = FormatType.ORIGINAL
		picture = photoOne
	}
	format2(Format) {
		width = 2000
		height = 1200
		path = "/2010/10/IMAGE2-2000x1200.jpg"
		type = FormatType.ORIGINAL
		picture = photoTwo
	}
	format3(Format) {
		width = 480
		height = 320
		path = "/2010/10/IMAGE2-640x320.jpg"
		type = FormatType.SMALL
		picture = photoTwo
	}
	format4(Format) {
		width = 1200
		height = 800
		path = "/2010/10/IMAGE2-1200x800.jpg"
		type = FormatType.LARGE
		picture = photoTwo
	}
	one(Entry) {
		title = "Entry One"
		publishDate = new DateTime(2010, 10, 01, 10, 10, 1, 0)
		permalink = "/2010/10/entry-one"
		pictures = [photoOne, photoTwo]
	}
	two(Entry) {
		title = "Entry Two"
		publishDate = new DateTime(2010, 10, 02, 10, 10, 1, 0)
		permalink = "/2010/10/entry-two"
	}
	three(Entry) {
		title = "Entry Three"
		publishDate = new DateTime().minusDays(2)
		permalink = "/2010/10/entry-three"
	}
	four(Entry) {
		title = "Entry Four"
		publishDate = new DateTime().minusDays(3)
		permalink = "/2010/10/entry-four"
	}
}