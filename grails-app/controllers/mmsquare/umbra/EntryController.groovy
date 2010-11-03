package mmsquare.umbra

class EntryController {

	def entryService

	def defaultAction = "list"

	def list = { EntryListCommand listCommand ->
		[listCommand: listCommand, entries: entryService.getEntries(listCommand)]
	}

	def show = {
		def permalink = "/${params?.year}/${params?.month}/${params?.id}"
		def entry = Entry.findByPermalink(permalink)
		if (!entry) {
			redirect action: "list"
		} else {
			[entry: entry]
		}
	}
}

class EntryListCommand {

	static final int ENTRIES_PER_PAGE = 3
	int page = 1

	int getOffset() {
		(page - 1) * ENTRIES_PER_PAGE
	}

	int getTotalOnCurrentPage() {
		page * ENTRIES_PER_PAGE	
	}
}