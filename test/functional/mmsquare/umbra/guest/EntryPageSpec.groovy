package mmsquare.umbra.guest

import mmsquare.umbra.Entry
import mmsquare.umbra.Format
import mmsquare.umbra.FormatType
import mmsquare.umbra.Picture
import mmsquare.umbra.guest.page.SingleEntryPage
import mmsquare.umbra.webdriver.WebSpec
import org.joda.time.DateTime
import org.joda.time.LocalDate

class EntryPageSpec extends WebSpec {

	def setupSpec() {
		fixtureLoader.load "tearDown"
	}

	def cleanup() {
		fixtureLoader.load "tearDown"
	}

	def "guest can view single entry page"() {
		given: "An entry exists"
		def fixture = fixtureLoader.load {
			entry(Entry) {
				title = "A test entry"
				publishDate = new DateTime().minusDays(2)
				permalink = "/2010/10/a-test-entry"
			}
		}

		when: "User opens the Entry page"
		open SingleEntryPage, [permalink: "/2010/10/a-test-entry"]

		then: "The entry is shown"
		page.entryTitle == "A test entry"
		page.pageTitle == "3F » A test entry"
	}

	def "guest can view photos on an entry page"() {
		given: "an entry with some photos"
		def fixture = fixtureLoader.load {
			photoOne(Picture) {
				dateTaken = new DateTime()
			}
			photoTwo(Picture) {
				dateTaken = new DateTime()
			}
			format1(Format) {
				width = 640
				height = 320
				path = "/2010/10/IMAGE1-640x320.jpg"
				type = FormatType.SMALL
				picture = photoOne
			}
			format2(Format) {
				width = 1200
				height = 320
				path = "/2010/10/IMAGE1-1200x320.jpg"
				type = FormatType.LARGE
				picture = photoOne
			}
			format3(Format) {
				width = 2300
				height = 640
				path = "/2010/10/IMAGE1.jpg"
				type = FormatType.ORIGINAL
				picture = photoOne
			}
			format4(Format) {
				width = 640
				height = 320
				path = "/2010/10/IMAGE2-640x320.jpg"
				type = FormatType.SMALL
				picture = photoTwo
			}
			format5(Format) {
				width = 1200
				height = 320
				path = "/2010/10/IMAGE2-1200x320.jpg"
				type = FormatType.LARGE
				picture = photoTwo
			}
			format6(Format) {
				width = 2300
				height = 640
				path = "/2010/10/IMAGE2.jpg"
				type = FormatType.ORIGINAL
				picture = photoTwo
			}
			entry(Entry) {
				title = "Zosia i Franek"
				pictures = [photoOne, photoTwo]
				publishDate = new DateTime().minusDays(2)
				permalink = "/2010/10/zosia-i-franek"
			}
		}

		when: "guest opens the entry page"
		open SingleEntryPage, [permalink: fixture.entry.permalink]

		then: "photos are displayed"
		page.photos.size() == 2
	}

}
