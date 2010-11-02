package mmsquare.umbra

import grails.plugin.spock.TagLibSpec
import static mmsquare.umbra.FormatType.*
import java.util.Formatter.DateTime
import spock.lang.Unroll

class UmbraTagLibSpec extends TagLibSpec {

	def "picture tag outputs nothing when there is no picture"() {
		when:
		def out = picture()

		then:
		out == 'null'
	}

	@Unroll("Picture tags shows #image and links to #link for #availableFormats")
	def "picture tag outputs correct picture"() {
		given: "a picture"
		def picture = [dateTaken: new DateTime(), title: title, formats: []]

		and: "defined formats"
		availableFormats.each {
			picture.formats << [width:100, height: 200, type: it, path: "/path/to/images/${it.toString().toLowerCase()}.jpg", url: "http://images.floryan.pl/images/path/to/images/${it.toString().toLowerCase()}.jpg"]
		}

		when:
		String out = showPicture(picture: picture)

		then:
		def html = new XmlSlurper().parseText(out)
		html.@href.toString().endsWith(link)
		html.img.@src.toString().endsWith(image)
		html.'@class'.toString().contains("fancyboxImage")
		html.img.@width == '100'
		html.img.@height == '200'
		if (title) {
			assert html.img.@alt == title
		} else {
			assert html.img.@alt == image
		}

		where:
		availableFormats         | image          | link            | title
		[ORIGINAL]               | "original.jpg" | "original.jpg"  | null
		[ORIGINAL, SMALL, LARGE] | "small.jpg"    | "large.jpg"     | 'Pretty picture'
		[ORIGINAL, SMALL]        | "small.jpg"    | "original.jpg"  | null
	}


}
