package umbra

import mmsquare.umbra.Entry

class EntryService {

    static transactional = true

    def getEntries() {
		Entry.list(sort:"publishDate", order:"desc")	    
    }
}
