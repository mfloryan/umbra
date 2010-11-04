import grails.test.GrailsUrlMappingsTestCase
import org.junit.Test

class UrlMappingsTests extends GrailsUrlMappingsTestCase {

	static mappings = UrlMappings

	@Test
	void "List url mappings"() {

		assertForwardUrlMapping("/", controller: "entry", action: "list")

		assertUrlMapping("/page/2", controller: "entry", action: "list") {
			page = 2
		}

		assertUrlMapping("/person/Zosia", controller: "entry", action: "list") {
			person = "Zosia"
		}

		assertUrlMapping("/person/Zosia/page/3", controller: "entry", action: "list") {
			person = "Zosia"
			page = 3
		}

//    assertUrlMapping("/tag/test", controller: "entry", action: "tag") {
//      tag = 'test'
//    }
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
