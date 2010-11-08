package mmsquare.umbra

import grails.plugin.spock.IntegrationSpec
import org.joda.time.DateTime
import spock.lang.Unroll

/* Created 23-Oct-2010 17:32:51 by mfloryan */

class EntryServiceSpec extends IntegrationSpec {

	def entryService
	def fixtureLoader

	def "Entries return in correct order"() {
		given: "some entries exist"
		buildSomeEntries([0, 2, 1])

		when:
		def results = entryService.getEntries(new EntryListCommand()).list

		then:
		results.size == 3
		results*.title == ["Entry 1", "Entry 3", "Entry 2"]
	}

	def "Should not return entries with publishDate in the future"() {
		given: "some entries exist"
		buildSomeEntries([-1, 0, 1])

		when:
		def results = entryService.getEntries(new EntryListCommand()).list

		then:
		results.size == 2
		results*.title == ["Entry 2", "Entry 3"]
	}

	def "Pagination works for entries"() {
		given: "enough entries exist for pagination"
		buildEntries(7)

		when:
		def command = new EntryListCommand(page: page)
		def results = entryService.getEntries(command)

		then:
		results.totalCount == 7
		results.list.size() == resultsCount

		where:
		page | resultsCount
		1 | 3
		2 | 3
		3 | 1
	}

	@Unroll("Filtered by #person correct entries: #titles are returned with pictures #photosCount")
	def "List of entries can be filtered by Person"() {
		given: "some entries exist with pictures of people"
		def fixture = fixtureLoader.load {
			zosia(Person) {
				shortName = "Zosia"
				fullName = "Zofia Teodora"
			}
			franek(Person) {
				shortName = "Franek"
				fullName = "Franciszek"
			}
			build {
				p1(Picture) {
					title = "Zosia"
					people = [zosia]
					dateTaken = new DateTime().minusDays(2)
				}
				p2(Picture) {
					title = "Franek"
					people = [franek]
					dateTaken = new DateTime().minusDays(2)
				}
				p3(Picture) {
					title = "Franek i Zosia"
					people = [zosia, franek]
					dateTaken = new DateTime().minusDays(2)
				}
				p4(Picture) {
					title = "Franek 2"
					people = [franek]
					dateTaken = new DateTime().minusDays(3)
				}
				p5(Picture) {
					title = "Zosia 2"
					people = [zosia]
					dateTaken = new DateTime().minusDays(3)
				}
				p6(Picture) {
					title = "Someone else"
					dateTaken = new DateTime().minusDays(3)
				}
				p7(Picture) {
					title = "Zosia 3"
					people = [zosia]
					dateTaken = new DateTime().minusDays(4)
				}
			}
			entry1(Entry) {
				title = "Entry 1"
				publishDate = new DateTime().minusDays(1)
				permalink = "/test/entry-1"
				pictures = [p1, p6]
			}
			entry2(Entry) {
				title = "Entry 2"
				publishDate = new DateTime().minusDays(2)
				permalink = "/t pest/entry-2"
				pictures = [p2]
			}
			entry3(Entry) {
				title = "Entry 3"
				publishDate = new DateTime().minusDays(3)
				permalink = "/test/entry-3"
				pictures = [p3]
			}
			entry4(Entry) {
				title = "Entry 4"
				publishDate = new DateTime().minusDays(4)
				permalink = "/test/entry-4"
				pictures = [p4, p5, p7]
			}
		}

		when:
		def results = entryService.getEntries(new EntryListCommand(person: person)).list

		then:
		results.title == titles
		results.pictures*.size() == photosCount

		where:
		person   | titles                            | photosCount
		"zosia"  | ["Entry 1", "Entry 3", "Entry 4"] | [1, 1, 2]
		"franek" | ["Entry 2", "Entry 3", "Entry 4"] | [1, 1, 1]

	}

	private void buildSomeEntries(offset) {

		def e1 = new Entry(title: "Entry 1", publishDate: new DateTime().minusDays(offset[0]), permalink: "/test/entry-1")
		e1.save(flush: true)

		def e2 = new Entry(title: "Entry 2", publishDate: new DateTime().minusDays(offset[1]), permalink: "/test/entry-2")
		e2.save(flush: true)

		def e3 = new Entry(title: "Entry 3", publishDate: new DateTime().minusDays(offset[2]), permalink: "/test/entry-3")
		e3.save(flush: true)
	}

	private void buildEntries(int howMany) {
		1.upto(howMany) { i ->
			def entry = Entry.buildWithoutSave(title: "Entry $i", permalink: "/test/entry-${i}", publishDate: new DateTime().minusDays(i))
			entry.save(failOnError: true, flush: true)
		}
	}
}
