package mmsquare.umbra

import grails.plugin.spock.UnitSpec
import org.joda.time.DateTime

class PictureSpec extends UnitSpec {

	def "picture with required values can be created"() {
		setup:
		mockDomain Picture

		when:
		def e = new Entry(publishDate: new DateTime(), title:"Test", permalink:"/test")
		def p = new Picture(dateTaken: new DateTime(), entry: e)

		then:
		p.validate()
	}
}
