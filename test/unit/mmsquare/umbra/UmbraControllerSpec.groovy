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

import grails.plugin.spock.ControllerSpec
import org.joda.time.DateTime

class UmbraControllerSpec extends ControllerSpec {

	def "Should return model for an existing entry"() {
		given:
		def entry = new Entry(permalink: '/2010/10/daymark', publishDate: new DateTime(2010, 10, 1, 10, 12, 2, 0), title: "Daymark")
		mockDomain Entry, [entry]

		controller.params.year = "2010"
		controller.params.month = "10"
		controller.params.id = "daymark"

		when:
		def model = controller.show()

		then:
		model
		model.entry == entry
	}

	def "Should redirect to list when entry does not exists"() {
		given:
		mockDomain Entry

		controller.params.year = "2010"
		controller.params.month = "10"
		controller.params.id = "daymark"

		when:
		def model = controller.show()

		then:
		!model
		controller.redirectArgs.action == 'list'
	}
}
