package mmsquare.umbra.guest

import mmsquare.umbra.webdriver.WebSpec
import mmsquare.umbra.guest.page.ListPage
import mmsquare.umbra.Entry
import org.joda.time.DateTime

class ListPageSpec extends WebSpec {

	def setupSpec() {
		fixtureLoader.load "tearDown"
	}

	def cleanup() {
		fixtureLoader.load "tearDown"
	}

	def "entries are shown on the list page"() {
		given: "some entires exist"
		def fixture = fixtureLoader.load {
			entry1(Entry) {
				publishDate = new DateTime(2010, 10, 1, 10, 10, 1, 0)
				title = "Entry 1"
				permalink = "/2010/10/entry-1"
			}
			entry2(Entry) {
				publishDate = new DateTime(2010, 10, 2, 10, 10, 1, 0)
				title = "Entry 2"
				permalink = "/2010/10/entry-2"
			}
			entry3(Entry) {
				publishDate = new DateTime(2010, 10, 3, 10, 10, 1, 0)
				title = "Entry 3"
				permalink = "/2010/10/entry-3"
			}
		}

		when: "guest opens the main page"
		open ListPage

		then: "a list of entries is shown"
		page.entries.size == 3

		and: "entries have correct links"
		page.entries*.url == [
				"http://localhost:8080/umbra/2010/10/entry-3",
				"http://localhost:8080/umbra/2010/10/entry-2",
				"http://localhost:8080/umbra/2010/10/entry-1"
		]
	}

}
