package mmsquare.umbra.guest

import mmsquare.umbra.Entry
import mmsquare.umbra.guest.page.SingleEntryPage
import mmsquare.umbra.webdriver.WebSpec
import org.joda.time.LocalDate
import mmsquare.umbra.Picture
import mmsquare.umbra.Format
import mmsquare.umbra.FormatType

class EntryPageSpec extends WebSpec {

	def "guest can view single entry page"() {
		given: "An entry exists"
		def fixture = fixtureLoader.load {
			entry(Entry) {
				title = "A test entry"
				publishDate = new LocalDate(2010, 10, 1)
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
			format1(Format) {
				width: 640
				hegiht: 320
				path: "2010/10/IMAGE1-640x320.jpg"
				type: FormatType.SMALL
			}
			format2(Format) {
				width: 1200
				hight: 320
				path: "2010/10/IMAGE1-1200x320.jpg"
				type: FormatType.LARGE
			}
			format3(Format) {
				width: 2300
				hight: 640
				path: "2010/10/IMAGE1.jpg"
				type: FormatType.ORIGINAL
			}
			format4(Format) {
				width: 640
				hegiht: 320
				path: "2010/10/IMAGE2-640x320.jpg"
				type: FormatType.SMALL
			}
			format5(Format) {
				width: 1200
				hight: 320
				path: "2010/10/IMAGE2-1200x320.jpg"
				type: FormatType.LARGE
			}
			format6(Format) {
				width: 2300
				hight: 640
				path: "2010/10/IMAGE2.jpg"
				type: FormatType.ORIGINAL
			}
			photo1(Picture) {
				formats: [format1, format2, format3]
				dateTaken: new LocalDate(2010,10,1)
			}
			photo2(Picture) {
				formats: [format4, format5, format6]
				dateTaken: new LocalDate(2010,10,2)
			}
			entry(Entry) {
				title: "Zosia i Franek"
				pictures: [photo1, photo2]
				publishDate: new LocalDate(2010,10,2)
				permalink: "2010/10/zosia-i-franek"
			}
		}

		when: "guest opens the entry page"
		open SingleEntryPage, [permalink: fixture.entry.permalink]

		then: "photos are displayed"
		page.photos.size() == 2
	}

}
