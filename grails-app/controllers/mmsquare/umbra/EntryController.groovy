package mmsquare.umbra

class EntryController {

	def entryService

    def defaultAction = "list"

    def list = {
	    [entries: entryService.entries]
    }

    def show = {
      def permalink = "/${params?.year}/${params?.month}/${params?.id}"
      def entry = Entry.findByPermalink(permalink)
      if (!entry) {
        redirect action:"list"
      } else {
        [entry: entry]
      }
    }
}
