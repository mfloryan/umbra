package mmsquare.umbra

import grails.plugin.spock.UnitSpec
import org.joda.time.LocalDate
import org.joda.time.DateTime
import spock.lang.Unroll

class EntrySpec extends UnitSpec {

	def "A title, permalink and publishDate are required for an Entry"() {
		setup:
		mockDomain Entry

		when:
		def entry = new Entry()

		then:
		!entry.validate()
		entry.errors.hasFieldErrors("permalink")
		entry.errors.hasFieldErrors("publishDate")
		entry.errors.hasFieldErrors("title")

	}

	def "An Entry validates with all correct properties"() {

		setup:
		mockDomain Entry

		when:
		def entry = new Entry(
				title: "A test entry",
				publishDate: new DateTime(),
				permalink: "/2010/10/test")

		then:
		entry.validate()

	}

	@Unroll("The permalink [#permalink] for Entry is #validates")
	def "Permalink validates when matches expected format"() {
		setup:
		mockForConstraintsTests Entry

		when:
		def entry = new Entry(title:"A test entry", publishDate: new DateTime(), permalink: permalink)

		then:
		entry.validate() == validates
		if (!validates) {
			assert entry.errors.permalink
		}

		where:
		permalink               | validates
		""				        | false
		"/"				        | false
		"/2010/10/test"         | true
		"/2010/2/test"          | false
		"/2010/02/test"         | true
		"2010/02/test"          | false
		"/2010/2010/test"       | false
		"/2010/2010/test"       | false
		"/2010/10/test entry"   | false
		"/2010/10/test-entry"   | true
		"/2010/10/test-entry_2" | true
	}

	def "Can create permalink from an entry's title and publishDate"() {
		given:
		def time = new DateTime(2010,2,1,10,10,2,0)
		def entry = new Entry(title:"A test entry", publishDate: time)
		!entry.permalink

		when:
		entry.createPermalink()

		then:
		entry.permalink == "/2010/02/a-test-entry"

	}

}
