package mmsquare.umbra

import grails.plugin.spock.IntegrationSpec
import org.joda.time.DateTime

/* Created 23-Oct-2010 17:32:51 by mfloryan */

class EntryServiceSpec extends IntegrationSpec {

	def entryService

	def "Entries return in correct order"() {
		given: "some entries exist"
		buildSomeEntries([0, 2, 1])

		when:
		def results = entryService.getEntries(new EntryListCommand()).list

		then:
		results.size == 3
		results*.title = ["Entry 1", "Entry 3", "Entry 2"]
	}

	def "Should not return entries with publishDate in the future"() {
		given: "some entries exist"
		buildSomeEntries([-1, 0, 1])

		when:
		def results = entryService.getEntries(new EntryListCommand()).list

		then:
		results.size == 2
		results*.title = ["Entry 2", "Entry 3"]
	}

	def "Pagination works for entries"() {
		given: "enough entries exist for pagination"
		buildEntries(7)

		when:
		def command = new EntryListCommand(pageNumber:page)
		def results = entryService.getEntries(command)

		then:
		results.totalCount = 7
		results.list.size() == resultsCount

		where:
		page | resultsCount
		1    | 3
		2    | 3
		3    | 1
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
		1.upto(howMany) { i->
			def entry = Entry.buildWithoutSave(title:"Entry $i", permalink:"/test/entry-${i}", publishDate: new DateTime().minusDays(i))
			entry.save(failOnError:true, flush:true)
		}
	}

}
