package mmsquare.umbra

import grails.plugin.spock.IntegrationSpec
import org.joda.time.DateTime

/* Created 06-Nov-2010 22:04:38 by mfloryan */

class PictureServiceSpec extends IntegrationSpec {

	def pictureService
	def fixtureLoader

	def "can correctly find all orphaned pictures"() {
		given:
		def fixture = fixtureLoader.load {
			build {
				p1(Picture) {
					dateTaken = new DateTime().minusDays(2)
				}
				p2(Picture) {
					dateTaken = new DateTime().minusDays(3)
				}
				entry(Entry) {
					pictures = [p2]
				}
			}
		}

		when:
		def orphans = pictureService.getAvailablePictures()

		then:
		orphans == [fixture.p1]
	}
}
