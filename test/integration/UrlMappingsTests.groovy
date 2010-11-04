import grails.test.GrailsUrlMappingsTestCase
import org.junit.Test

class UrlMappingsTests extends GrailsUrlMappingsTestCase {

	static mappings = UrlMappings

	@Test
	void "List url mappings"() {

		assertForwardUrlMapping("/", controller: "entry", action: "list")

		assertForwardUrlMapping("/page/2", controller: "entry", action: "list") {
			page = 2
		}

		assertForwardUrlMapping("/person/Zosia", controller: "entry", action: "list") {
			person = "Zosia"
		}

		assertForwardUrlMapping("/person/Zosia/page/3", controller: "entry", action: "list") {
			person = "Zosia"
			page = 3
		}
	}

	@Test
	void "Entry url mappings"() {

		assertUrlMapping("/2010/08/daymark", controller: "entry", action: "show") {
			year = '2010'
			month = '08'
			id = 'daymark'
		}
	}
}
