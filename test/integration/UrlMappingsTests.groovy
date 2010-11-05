import grails.test.GrailsUrlMappingsTestCase
import org.junit.Test

class UrlMappingsTests extends GrailsUrlMappingsTestCase {

	static mappings = UrlMappings

	@Test
	void "List url mappings"() {

		assertForwardUrlMapping("/", controller: "umbra", action: "list")

		assertForwardUrlMapping("/page/2", controller: "umbra", action: "list") {
			page = 2
		}

		assertForwardUrlMapping("/person/Zosia", controller: "umbra", action: "list") {
			person = "Zosia"
		}

		assertForwardUrlMapping("/person/Zosia/page/3", controller: "umbra", action: "list") {
			person = "Zosia"
			page = 3
		}
	}

	@Test
	void "Entry url mappings"() {

		assertUrlMapping("/2010/08/daymark", controller: "umbra", action: "show") {
			year = '2010'
			month = '08'
			id = 'daymark'
		}
	}
}
