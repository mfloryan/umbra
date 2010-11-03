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
		def results = entryService.entries

		then:
		results.size == 3
		results*.title = ["Entry 1", "Entry 3", "Entry 2"]
	}

	def "Should not return entries with publishDate in the future"() {
		given: "some entries exist"
		buildSomeEntries([-1, 0, 1])

		when:
		def results = entryService.entries
		println results.publishDate

		then:
		results.size == 2
		results*.title = ["Entry 2", "Entry 3"]

	}

	void buildSomeEntries(offset) {

		def e1 = new Entry(title: "Entry 1", publishDate: new DateTime().minusDays(offset[0]), permalink: "/test/entry-1")
		e1.save(flush: true)

		def e2 = new Entry(title: "Entry 2", publishDate: new DateTime().minusDays(offset[1]), permalink: "/test/entry-2")
		e2.save(flush: true)

		def e3 = new Entry(title: "Entry 3", publishDate: new DateTime().minusDays(offset[2]), permalink: "/test/entry-3")
		e3.save(flush: true)
	}

}
