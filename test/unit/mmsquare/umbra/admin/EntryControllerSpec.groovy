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

package mmsquare.umbra.admin

import grails.plugin.spock.ControllerSpec
import groovy.mock.interceptor.MockFor
import mmsquare.umbra.Entry
import mmsquare.umbra.Picture
import mmsquare.umbra.PictureService
import org.joda.time.DateTime

/* Created 08-Nov-2010 19:41:13 by mfloryan */

class EntryControllerSpec extends ControllerSpec {

	def "create sets publishDate from existing pictures"() {
		given: "Some pictures exist without an Entry"
		def p1 = new Picture(dateTaken: new DateTime(2010,10,10,16,14,0,0),	originalFilename : "IMG_123.JPG");
		def p2 = new Picture(dateTaken: new DateTime(2010,10,12,16,14,0,0),	originalFilename : "IMG_124.JPG");
		mockDomain Entry

		def mock = new MockFor(PictureService)
		mock.demand.getAvailablePictures {
			[p1, p2]
		}
		controller.pictureService = mock.proxyInstance()

		when: "controller create is called with given publishDate in params"
		def model
		controller.params.publishDate = paramsPublishDate
		model = controller.create()

		then: "Entry publishDate is set to first picture's dateTaken"
		model
		if (paramsPublishDate) {
			assert model.entryInstance.publishDate == paramsPublishDate
		} else {
			assert model.entryInstance.publishDate == p1.dateTaken
		}

		where:
		paramsPublishDate << [ null, new DateTime(2010,8,8,10,8,2,0)]
	}
}
