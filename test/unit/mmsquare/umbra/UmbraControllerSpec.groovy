package mmsquare.umbra

import grails.plugin.spock.ControllerSpec
import org.joda.time.DateTime
import groovy.mock.interceptor.MockFor

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
		def entry1 = new Entry(publishDate: new DateTime(), title: "Test entry", permalink: "/2010/12/test-entry", content: "jiffy")
		def entry2 = new Entry(publishDate: new DateTime().minusDays(2), title: "Test entry 2", permalink: "/2010/12/test-entry-2", content: "Some <b>html</b> content")
		def entries = [entry1, entry2]

		mockDomain Entry, entries
		mockConfig """
			umbra.title = "$title"
			umbra.description = "$subtitle"
			grails.serverUrl = "$server"
			app.version= "1.2"
			app.name= "Umbra"
		"""

		def mock = new MockFor(EntryService)
		mock.demand.getRecent {
			entries
		}

		controller.entryService = mock.proxyInstance()

		when:
		controller.rssFeed()
		def responseString = controller.response.getContentAsString()

		println responseString
		def feed = new XmlSlurper().parseText(responseString)

		then:
		controller.response.contentType == "application/atom+xml"
		feed
		feed.id == "umbra-test.server.com"
		feed.title == title
		feed.subtitle == subtitle
		feed.updated == entry1.publishDate.toString()
		feed.link[0].@rel == "self"
		feed.link[0].@href == "$server/feed"
		feed.link[1].@href == "$server"
		feed.author.name == "Marcin Floryan"
//		feed.author.email == "marcin-3f-feed@szara.waw.pl"
		feed.rights == "(c) Małgorzata Floryan & Marcin Floryan cc-by-nc-sa"

		feed.generator == "Umbra"
		feed.generator.@version == "1.2"

		feed.entry.size() == 2

		feed.entry.eachWithIndex { e, i ->
			assert e.title == entries[i].title
			assert e.title.@type == "text"
			assert e.published == entries[i].publishDate.toString()
			assert e.updated == entries[i].publishDate.toString()
			assert e.link.@href == "http://test.server.com"+entries[i].permalink
		}

		where:
		title = "Some title"
		subtitle = "Feed subtitle"
		server = "http://test.server.com"
	}
}
