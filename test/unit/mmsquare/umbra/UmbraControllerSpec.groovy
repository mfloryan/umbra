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

	def "Should return rss feed with most recent items"() {
		given:
		mockDomain Entry, []
		mockConfig """
			umbra.title = "$title"
			umbra.description = "$subtitle"
			grails.serverUrl = "$server"
		"""

		when:
		controller.rssFeed()
		def responseString = controller.response.getContentAsString()
		def feed = new XmlSlurper().parseText(responseString)

		println responseString
		then:
		controller.response.contentType == "application/atom+xml"
		feed
		feed.title == title
//		feed.@xmlns == "http://www.w3.org/2005/Atom"
		feed.subtitle == subtitle
		feed.link[0].@href == "$server/feed"
		feed.link[1].@href == "$server"
//		feed.id == "id"
//		feed.updated == ""
//		feed.author.name == "Marcin Floryan"
//		feed.author.email == "marcin-3f-feed@szara.waw.pl"
//		feed.rights == "(c) Małgorzata Floryan & Marcin Floryan"
//
//		feed.entry.size() == 2
//		
		
		where:
		title = "Some title"
		subtitle = "Feed subtitle"
		server = "http://test.server.com"
	}
}
