import mmsquare.umbra.Entry
import org.joda.time.LocalDate

fixture {
  one(Entry) {
    title = "Entry One"
    publishDate = new LocalDate(2010,10,01)
    permalink = "/2010/10/entry-one"
  }
  two(Entry) {
    title = "Entry Two"
    publishDate = new LocalDate(2010,10,02)
    permalink = "/2010/10/entry-two"
  }
}