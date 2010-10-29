package mmsquare.umbra

import grails.plugin.spock.IntegrationSpec
import org.joda.time.LocalDate

/* Created 23-Oct-2010 17:32:51 by mfloryan */

class EntryServiceSpec extends IntegrationSpec {

	def entryService

	def "Entries return in correct order"() {
		given: "some entries exist"
		buildSomeEntries()

		when:
		def results = entryService.entries

		then:
		results.size == 3
		results*.title = ["Entry 2","Entry 1","Entry 3"]
	}

	void buildSomeEntries() {

		def e1 = new Entry(title:"Entry 1", publishDate: new LocalDate(2010,10,2), permalink: "/2010/10/entry-1" )
		e1.save(flush: true)

		def e2 = new Entry(title:"Entry 2", publishDate: new LocalDate(2010,10,3), permalink: "/2010/10/entry-2" )
		e2.save(flush: true)

		def e3 = new Entry(title:"Entry 3", publishDate: new LocalDate(2010,10,1), permalink: "/2010/10/entry-3" )
		e3.save(flush: true)


	}

}
