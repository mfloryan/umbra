package mmsquare.umbra

import org.joda.time.DateTime
import grails.orm.PagedResultList

class EntryService {

	static transactional = true

	PagedResultList getEntries(EntryListCommand filterCommand) {
		Entry.createCriteria().list([max:filterCommand.ENTRIES_PER_PAGE, offset:filterCommand.offset]) {
			le("publishDate", new DateTime())
			order("publishDate", "desc")
		}
	}
}
