package mmsquare.umbra.guest

import mmsquare.umbra.webdriver.WebSpec
import org.joda.time.LocalDate
import mmsquare.umbra.Entry

class EntryPageSpec extends WebSpec {

  def "Guest can view single entry page"() {
    given: "An entry exists"
      def fixture = fixtureLoader.load {
        entry(Entry) {
          title = "A test entry"
          publishDate = new LocalDate(2010,10,1)
        }
      }

    when: "User opens the Entry page"

    then: "The entry is shown"
    page.entryTitle = "A test entry"

  }

}
