package mmsquare.umbra

import org.joda.time.DateTime

class EntryService {

	static transactional = true

	def getEntries() {
		Entry.createCriteria().list {
			le("publishDate", new DateTime())
			order("publishDate", "desc")
		}
	}
}
