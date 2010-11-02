package mmsquare.umbra

class EntryService {

    static transactional = true

    def getEntries() {
		Entry.list(sort:"publishDate", order:"desc")	    
    }
}
