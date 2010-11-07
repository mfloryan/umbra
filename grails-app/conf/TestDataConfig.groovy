import org.joda.time.DateTime
/* Created 06-Nov-2010 22:07:12 by mfloryan */

testDataConfig {
    sampleData {
        'mmsquare.umbra.Entry' {
            title = "Test entry"
	        permalink = "/test/test-entry"
	        publishDate = new DateTime().minusDays(2)
        }
    }
}