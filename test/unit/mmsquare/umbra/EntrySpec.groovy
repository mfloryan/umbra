package mmsquare.umbra

import grails.plugin.spock.UnitSpec
import org.joda.time.LocalDate

class EntrySpec extends UnitSpec {

  def "A title, permalink and publishDate are required for an Entry"() {
    setup:
    mockDomain Entry

    when:
    def entry = new Entry()

    then:
    !entry.validate()
    entry.errors.hasFieldErrors("permalink")
    entry.errors.hasFieldErrors("publishDate")
    entry.errors.hasFieldErrors("title")

  }

  def "An Entry validates with all correct properties"() {

    setup:
    mockDomain Entry

    when:
    def entry = new Entry(
            title:"A test entry",
            publishDate: new LocalDate(2010,10,1),
            permalink: "/2010/10/test")

    then:
    entry.validate()

  }

}
