package mmsquare.umbra

import grails.plugin.spock.ControllerSpec
import org.joda.time.LocalDate

class EntryControllerSpec extends ControllerSpec {

  def "Should return model for an existing entry"() {
    given:
    def entry = new Entry(permalink: '/2010/10/daymark', publishDate: new LocalDate(2010,10,1), title:"Daymark")
    mockDomain Entry, [entry]

    controller.params.year = "2010"
    controller.params.month = "10"
    controller.params.id = "daymark"


    when:
    def model = controller.show()

    then:
    model
    model.entry == entry
  }

  def "Should redirect to list when entry does not exists"() {
    given:
    mockDomain Entry
    
    controller.params.year = "2010"
    controller.params.month = "10"
    controller.params.id = "daymark"

    when:
    def model = controller.show()

    then:
    !model
    controller.redirectArgs.action == 'list'
  }

}
