package mmsquare.umbra.guest

import mmsquare.umbra.Entry
import mmsquare.umbra.Format
import mmsquare.umbra.FormatType
import mmsquare.umbra.Picture
import mmsquare.umbra.guest.page.SingleEntryPage
import mmsquare.umbra.webdriver.WebSpec
import org.joda.time.DateTime
import org.joda.time.LocalDate
import mmsquare.umbra.guest.page.EntryDetails

class EntryPageSpec extends WebSpec {

	def setupSpec() {
		fixtureLoader.load "tearDown"
	}

	def cleanup() {
		fixtureLoader.load "tearDown"
	}

	def "guest can view single entry page with some content"() {
		given: "An entry exists with content"
		def fixture = fixtureLoader.load {
			entry(Entry) {
				title = "A test entry"
				publishDate = new DateTime().minusDays(2)
				permalink = "/2010/10/a-test-entry"
				content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam nec placerat enim. Praesent ut justo tortor. Pellentesque id ligula justo. Curabitur mauris ipsum, pretium eget viverra ac, pharetra a mauris. Donec dui velit, interdum ut ultrices at, tempus a velit. Phasellus in neque libero. Mauris porta, est sed consectetur varius, ante nisl posuere massa, in volutpat libero urna vitae leo. Etiam sit amet elit leo, malesuada rutrum eros. Nunc sit amet eros ligula, sit amet pellentesque tellus. Quisque volutpat, libero quis congue sodales, ligula libero tincidunt mi, sit amet auctor diam sapien quis diam. Fusce nec ante a mi ornare tempor eu sed massa. Donec dui dolor, imperdiet id aliquam id, congue vel nibh. Nulla facilisi."
			}
		}

		when: "User opens the Entry page"
		open SingleEntryPage, [permalink: "/2010/10/a-test-entry"]

		then: "The entry is shown"
		page.entry.title == "A test entry"
		page.pageTitle == "3F » A test entry"
		page.entry.content.startsWith("Lorem ipsum dolor sit amet")
		page.entry.content.endsWith("Nulla facilisi.")

	}

	def "guest can view photos on an entry page and download originals"() {
		given: "an entry with some photos"
		def fixture = fixtureLoader.load {
			build {
				photoOne(Picture) {
					dateTaken = new DateTime()
				}
				photoTwo(Picture) {
					dateTaken = new DateTime()
				}
			}
			format1(Format) {
				width = 480
				height = 320
				path = "/2010/10/IMAGE1-480x320.jpg"
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
				width = 480
				height = 320
				path = "/2010/10/IMAGE2-480x320.jpg"
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

		then: "pictures are displayed with donwload links"
		page.entry.pictures.size() == 2
		page.entry.downloads.size() == 2
	}

	def "Guest can see picture's title"() {
		given: "an entry with two pictures, one of which has a title"
		def fixture = fixtureLoader.load {
			build {
				photoOne(Picture) {
					dateTaken = new DateTime()
					title = "Warszawa"
					originalFilename = "warszawa.jpg"
				}
				photoTwo(Picture) {
					dateTaken = new DateTime()
					originalFilename = "otherPicture.jpg"
				}
			}
			format1(Format) {
				width = 480
				height = 320
				path = "/2010/10/IMAGE1-480x320.jpg"
				type = FormatType.SMALL
				picture = photoOne
			}
			format2(Format) {
				width = 800
				height = 640
				path = "/2010/10/IMAGE1.jpg"
				type = FormatType.ORIGINAL
				picture = photoOne
			}
			format4(Format) {
				width = 480
				height = 320
				path = "/2010/10/IMAGE2-480x320.jpg"
				type = FormatType.SMALL
				picture = photoTwo
			}
			format6(Format) {
				width = 800
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

		when: "guest goes to the entry page"
		open SingleEntryPage, [permalink: fixture.entry.permalink]

		then: "the picutre with a title has a title set"
		EntryDetails entry = page.entry
		entry.pictures[0].title == "Warszawa"
		entry.pictures[0].alt == "Warszawa"
		!entry.pictures[1].title
		entry.pictures[1].alt == "otherPicture.jpg"

	}

}
