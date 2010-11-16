package mmsquare.umbra.guest

import mmsquare.umbra.webdriver.WebSpec
import mmsquare.umbra.Entry
import org.joda.time.DateTime
import mmsquare.umbra.guest.page.ListPage

class ListPagePaginationSpec extends WebSpec {

	def setupSpec() {
		fixtureLoader.load "tearDown"
	}

	def cleanup() {
//		fixtureLoader.load "tearDown"
	}


	def "guest can paginate the list of results"() {
		given: "enough entries for pagination"
		def fixture = fixtureLoader.load {
			build {
				1.upto(7) { i ->
					"entry$i"(Entry) {
						permalink = "/2010/10/entry-$i"
						title = "Entry $i"
						publishDate = new DateTime().minusDays(i)
					}
				}
			}
		}

		when: "guest opens the main page"
		open ListPage

		then: "First 3 entries are shown and there is a next page link"
		page.entries.size() == 3
		page.entries[0].title == "Entry 1"
		page.nextPage

		when: "guest clicks next page"
		page.nextPage.click()

		then: "still at list page"
		verifyAt ListPage

		and: "three entries are show"
		page.entries.size() == 3
		page.entries[0].title == "Entry 4"
		page.nextPage
		page.prevPage
	}
}