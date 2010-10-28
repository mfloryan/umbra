package mmsquare.umbra.guest

import mmsquare.umbra.webdriver.WebSpec
import org.joda.time.LocalDate
import mmsquare.umbra.Entry
import mmsquare.umbra.guest.page.SingleEntryPage

class EntryPageSpec extends WebSpec {

  //def fixtureLoader

  def "guest can view single entry page" () {
    given: "An entry exists"
      def fixture = fixtureLoader.load {
        entry(Entry) {
          title = "A test entry"
          publishDate = new LocalDate(2010,10,1)
          permalink = "/2010/10/a-test-entry"
        }
      }

    when: "User opens the Entry page"
    open SingleEntryPage, [permalink: "/2010/10/a-test-entry"]

    then: "The entry is shown"
    page.entryTitle == "A test entry"

    println "Testing"

  }

}
