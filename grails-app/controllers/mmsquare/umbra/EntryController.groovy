package mmsquare.umbra

class EntryController {

	def entryService

	def defaultAction = "list"

	def list = { EntryListCommand filterCommand ->
		[filterCommand: filterCommand, entries: entryService.getEntries(filterCommand)]
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
	Integer pageNumber = 1

	Integer getOffset() {
		(pageNumber - 1) * ENTRIES_PER_PAGE
	}
}