package mmsquare.umbra.guest

import geb.spock.GebSpec

/* Created 31-Oct-2010 22:35:03 by mfloryan */

class HomePageSpec extends GebSpec {
	  def "test something"() {
        when:
        to "/"
        then:
        $("h1").text() == "3F"
    }
}
