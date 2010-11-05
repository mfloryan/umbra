package mmsquare.umbra.guest

import mmsquare.umbra.webdriver.WebSpec
import mmsquare.umbra.Person
import mmsquare.umbra.guest.page.ListPage

class ListPagePersonSpec extends WebSpec {


	def "List of people is shown on the list page"() {
		given: "Some people exits"
		def fixture = fixtureLoader.load {
			zosia(Person) {
				shortName = "Zosia"
				fullName = "Zofia Teodora"
			}
			matylda(Person) {
				shortName = "Matylda"
				fullName = "Matylda Eurydyka"
			}
			franek(Person) {
				shortName = "Franek"
				fullName = "Franciszek"
			}
		}

		when: "guest opens list page"
		open ListPage

		then: "a list of people is shown"
		page.people.size() == 3

		and: "user can click on a person"
		page.people[1].clickOn()

		and: "the clicked person is selected"
		page.people[1].isSelected()
	}

	def "guest can select a person to see photos of"() {


	}

}
