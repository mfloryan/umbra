/*
 * Copyright (c) 2010 Marcin Floryan. http://www.mmsquare.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
			matylda(Person) {
				shortName = "Matylda"
				fullName = "Matylda"
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
				p8(Picture) {
					title = "Matylda 1"
					people = [matylda]
					dateTaken = new DateTime().minusDays(5)
				}
				p9(Picture) {
					title = "Matylda 2"
					people = [matylda]
					dateTaken = new DateTime().minusDays(5)
				}
			}
			entry1(Entry) {
				title = "Entry 1"
				publishDate = new DateTime().minusDays(1)
				permalink = "/2010/10/entry-1"
				pictures = [p1, p6]
			}
			entry2(Entry) {
				title = "Entry 2"
				publishDate = new DateTime().minusDays(2)
				permalink = "/2010/10/entry-2"
				pictures = [p2]
			}
			entry3(Entry) {
				title = "Entry 3"
				publishDate = new DateTime().minusDays(3)
				permalink = "/2010/10/entry-3"
				pictures = [p3]
			}
			entry4(Entry) {
				title = "Entry 4"
				publishDate = new DateTime().minusDays(4)
				permalink = "/2010/10/entry-4"
				pictures = [p4, p5, p7]
			}
			entry5(Entry) {
				title = "Entry 5"
				publishDate = new DateTime().minusDays(5)
				permalink = "/2010/10/entry-5"
				pictures = [p8, p9]
			}
		}

		when:
		def results = entryService.getEntries(new EntryListCommand(person: person)).list

		then:
		results.title == titles
		results.pictures*.size() == photosCount

		where:
		person | titles | photosCount
		"zosia" | ["Entry 1", "Entry 3", "Entry 4"] | [1, 1, 2]
		"franek" | ["Entry 2", "Entry 3", "Entry 4"] | [1, 1, 1]
		"matylda" | ["Entry 5"] | [2]

	}

	def "verify if a bogus picture is returned from filtered result set"() {
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
			}
			entry1(Entry) {
				title = "Entry 1"
				publishDate = new DateTime().minusDays(1)
				permalink = "/2010/10/entry-1"
				pictures = [p1, p2, p3]
			}
		}

		when:
		def results = entryService.getEntries(new EntryListCommand(person: person)).list

		then:
		results.title == ["Entry 1"]
		results[0].pictures.size() == 2
		results.pictures.title == [["Franek", "Franek i Zosia"]]

		where:
		person = "franek"
	}

	def "should return recent entries for rss feed"() {
		given: "some entries of different publish dates"
		def fixture = fixtureLoader.load {
			entry1(Entry) {
				title = "Entry in the future"
				publishDate = new DateTime().plusDays(2)
				permalink = "/2010/10/entry-future"
			}
			entry1(Entry) {
				title = "Test Entry B"
				publishDate = new DateTime().minusDays(10)
				permalink = "/2010/10/entry-now-1"
			}
			entry2(Entry) {
				title = "Test Entry A"
				publishDate = new DateTime().minusDays(1)
				permalink = "/2010/10/entry-now-2"
			}
			entry3(Entry) {
				title = "Entry in the past"
				publishDate = new DateTime().minusDays(40)
				permalink = "/2010/10/entry-past-1"
			}
		}

		when:
		def entries = entryService.getRecent()

		then:
		entries.size() == 2
		entries.title == ["Test Entry A", "Test Entry B"]

	}

	private void buildSomeEntries(offset) {

		def e1 = new Entry(title: "Entry 1", publishDate: new DateTime().minusDays(offset[0]), permalink: "/2010/10/entry-1")
		e1.save(flush: true)

		def e2 = new Entry(title: "Entry 2", publishDate: new DateTime().minusDays(offset[1]), permalink: "/2010/10/entry-2")
		e2.save(flush: true)

		def e3 = new Entry(title: "Entry 3", publishDate: new DateTime().minusDays(offset[2]), permalink: "/2010/10/entry-3")
		e3.save(flush: true)
	}

	private void buildEntries(int howMany) {
		1.upto(howMany) { i ->
			def entry = Entry.buildWithoutSave(title: "Entry $i", permalink: "/2010/10/entry-${i}", publishDate: new DateTime().minusDays(i))
			entry.save(failOnError: true, flush: true)
		}
	}
}
