import grails.test.*
import org.junit.Test

class UrlMappingsTests extends GrailsUrlMappingsTestCase {

  static mappings = UrlMappings

  @Test
  void "List url mappings"() {

    assertForwardUrlMapping("/", controller: "entry", action: "list")

//    assertUrlMapping("/tag/test", controller: "entry", action: "tag") {
//      tag = 'test'
//    }
  }

  @Test void "Entry url mappings"() {

    assertUrlMapping("/2010/08/daymark", controller: "entry", action: "show") {
      year = '2010'
      month = '08'
      id = 'daymark'
    }
  }
}
