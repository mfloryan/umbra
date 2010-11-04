package mmsquare.umbra

import grails.plugin.spock.UnitSpec
import spock.lang.Unroll

/* Created 04-Nov-2010 21:43:27 by mfloryan */

class EntryListCommandSpec extends UnitSpec {
	@Unroll("EntryListCommand totalPages returns #totalPages pages for #totalCount items")
	def "EntryListCommand returns correct total number of pages"() {
		when:
		def lc = new EntryListCommand()

		then:
		lc.getTotalPages(totalCount) == totalPages

		where:
		totalCount | totalPages
		0          | 1
		1          | 1
		3          | 1
		4          | 2
		5          | 2
		6          | 2
		7          | 3
	}
}
