package mmsquare.umbra.admin

import grails.plugin.spock.ControllerSpec
import groovy.mock.interceptor.MockFor
import mmsquare.umbra.Entry
import mmsquare.umbra.Picture
import mmsquare.umbra.PictureService
import org.joda.time.DateTime
import mmsquare.umbra.Person

/* Created 08-Nov-2010 19:41:13 by mfloryan */

class EntryControllerSpec extends ControllerSpec {

	def "create sets publishDate from existing pictures"() {
		given: "Some pictures exist without an Entry"
		def p1 = new Picture(dateTaken: new DateTime(2010,10,10,16,14,0,0),	originalFilename : "IMG_123.JPG");
		def p2 = new Picture(dateTaken: new DateTime(2010,10,12,16,14,0,0),	originalFilename : "IMG_124.JPG");
		mockDomain Entry

		def mock = new MockFor(PictureService)
		mock.demand.getAvailablePictures {
			[p1, p2]
		}
		controller.pictureService = mock.proxyInstance()

		when: "controller create is called with given publishDate in params"
		def model
		controller.params.publishDate = paramsPublishDate
		model = controller.create()

		then: "Entry publishDate is set to first picture's dateTaken"
		model
		if (paramsPublishDate) {
			assert model.entryInstance.publishDate == paramsPublishDate
		} else {
			assert model.entryInstance.publishDate == p1.dateTaken
		}

		where:
		paramsPublishDate << [ null, new DateTime(2010,8,8,10,8,2,0)]
	}

	def "save updates title and people tags for an entry"() {
		given: "Some pictures exist without an Entry"
		def p1 = new Picture(dateTaken: new DateTime(2010,10,10,16,14,0,0),	originalFilename : "IMG_123.JPG");
		def p2 = new Picture(dateTaken: new DateTime(2010,10,12,16,14,0,0),	originalFilename : "IMG_124.JPG");
		def zosia = new Person(shortName: "Zosia", fullName:"Zofia")
		def franek = new Person(shortName: "Franek", fullName:"Franciszek")
		mockDomain Entry
		mockDomain Picture, [p1, p2]
		mockDomain Person, [zosia, franek]

		def mock = new MockFor(PictureService)
		mock.demand.getAvailablePictures {
			[p1, p2]
		}
		controller.pictureService = mock.proxyInstance()

		when: "controller save is called with properties for pictures in params"
		def picture = [:]
		picture["${p1.id}"] = [title:"Some title"]
		picture["${p1.id}"] << [people: zosia.id]
		picture["${p2.id}"] = [people: [zosia.id, franek.id]]
		controller.params.picture = picture
		controller.save()

		then: "The pictures are updated with relevant data"
		p1.title == "Some title"
		p1.people.size() == 1
		p2.people.size() == 2		
	}
}

