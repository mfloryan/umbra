package mmsquare.umbra.guest

import mmsquare.umbra.webdriver.WebSpec
import mmsquare.umbra.Person
import mmsquare.umbra.guest.page.ListPage

class ListPagePersonSpec extends WebSpec {

	def setupSpec() {
		fixtureLoader.load "tearDown"
		def fixture = fixtureLoader.load("furniture/people")
	}

	def cleanupSpec() {
		fixtureLoader.load "tearDown"
	}

	def "List of people is shown on the list page"() {
		given: "Some people exits"


		when: "guest opens list page"
		open ListPage

		then: "a list of people is shown"
		page.people.size() == 3
	}

	def "guest can select a person to see photos of"() {

		when: "guest selected a person on the list page"
		open ListPage
		page.people[1].clickOn()

		then: "the clicked person is selected"
		page.people[1].isSelected()

		and: "a subtitle will indicate the selected person"
		page.subtitle == "Showing pictures of Matylda"

	}

}
