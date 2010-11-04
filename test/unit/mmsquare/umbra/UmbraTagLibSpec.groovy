package mmsquare.umbra

import grails.plugin.spock.TagLibSpec
import java.util.Formatter.DateTime
import spock.lang.Unroll
import static mmsquare.umbra.FormatType.*

class UmbraTagLibSpec extends TagLibSpec {

	def setup() {
		Map.metaClass."int" = { key ->
			def value = delegate.get(key)
			value != null ? value.toInteger() : null
		}
	}

	def "picture tag outputs nothing when there is no picture"() {
		when:
		def out = showPicture()

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

	@Unroll("Pagination #direction from #page/#totalPages with person #person goes to #expectedLink")
	def "Pagination tag lib outputs correct pagination link"() {
		setup:
		mockConfig('grails.serverURL = "http://localhost:8080/umbra"')
		
		when:
		String out = tagLib.pagination(direction:direction, page:page, totalPages:totalPages, person:person)

		then:
		if (expectedLink) {
			def div = new XmlSlurper().parseText(out)
			assert div.'@class' == 'pagination'
			assert div.a.@href.toString().endsWith(expectedLink)
		} else {
			assert !out
		}

		where:
		direction | page | totalPages | person  | expectedLink
		'next'    | 1    | 1          | ''      | ''
		'next'    | 1    | 2          | ''      | '/page/2'
		'next'    | 2    | 3          | ''      | '/page/3'
		'next'    | 3    | 3          | ''      | ''
		'next'    | 1    | 2          | 'Zosia' | '/person/Zosia/page/2'
		'prev'    | 1    | 1          | ''      | ''
		'prev'    | 1    | 3          | ''      | ''
		'prev'    | 2    | 2          | ''      | '/'
		'prev'    | 3    | 3          | ''      | '/page/2'
		'prev'    | 4    | 4          | ''      | '/page/3'
		'prev'    | 2    | 2          | 'Zosia' | '/person/Zosia'
		'prev'    | 3    | 3          | 'Zosia' | '/person/Zosia/page/2'
	}
}
