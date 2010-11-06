package mmsquare.umbra

import grails.plugin.spock.UnitSpec
import org.joda.time.DateTime

class PictureSpec extends UnitSpec {

	def "picture with required values can be created"() {
		setup:
		mockDomain Picture

		when:
		def p = new Picture(dateTaken: new DateTime())

		then:
		p.validate()
	}
}
