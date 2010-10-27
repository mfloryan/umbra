package mmsquare.umbra

class EntryController {

    def defaultAction = "list"
  
    def list = {}
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
