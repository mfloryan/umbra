/*
 * Copyright (c) 2010 Marcin Floryan. http://www.mmsquare.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mmsquare.umbra

import groovy.xml.MarkupBuilder
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class UmbraTagLib {
	static namespace = "umbra"

	def showPicture = { attrs ->
		def picture = attrs.remove("picture")
		if (picture && picture.formats) {
			def linkUrl = pictureFormatForLink(picture).url
			def imageFormat = pictureFormatForImage(picture)
			def altText = picture.title ?: imageFormat?.path.find(/[\w.-]+$/)
			if (attrs["class"]) attrs["class"] += " fancyboxImage"
			else attrs["class"] = "fancyboxImage"

			def html = new MarkupBuilder(out)
			html.a(href: linkUrl, class: attrs["class"]) {
				img src: imageFormat?.url, width: imageFormat.width, height: imageFormat.height, alt: altText
			}
			out
		}
	}

	private def pictureFormatForLink(picture) {
			formatForType(picture, FormatType.LARGE) ?: formatForType(picture, FormatType.ORIGINAL)
	}

	private def pictureFormatForImage(picture) {
			formatForType(picture, FormatType.SMALL) ?: formatForType(picture, FormatType.ORIGINAL)
	}

	private def formatForType(picture, type) {
		def format = picture.formats.find {
			it.type == type
		}
	}

	def people = { attrs ->
		def list = Person.listOrderByShortName(order: 'desc')
		def person = attrs.remove("person")
		if (list) {
			out << '<ul class="people">'
			list.each { p ->
				def personLinkName = p.shortName.toLowerCase()
				out << "<li class=\"person ${personLinkName}"
				if (personLinkName == person) out << " selected"
				out << "\">"
				out << '<a href="'
				out << ConfigurationHolder.config.grails.serverURL
				out << "/person/${personLinkName}\">${p.fullName}</a>"
				out << "</li>"
			}
			out << "</ul>"
		}
		out
	}


	def pagination = { attrs ->
		def direction = attrs.remove("direction")
		if (!direction) throwTagError("attribute 'direction' is required")

		def page = attrs.int("page")
		def totalPages = attrs.int("totalPages")
		def person = attrs.remove("person")
		if (totalPages && totalPages > 1) {
			if ((direction == 'prev' && page > 1) ||
				(direction == 'next' && page < totalPages)) {

				int newPage
				if (direction == 'next') newPage = page + 1
				if (direction == 'prev') newPage = page - 1

				out << '<div class="pagination">'
				out << '<a '
				out << "class=\"$direction\" "
				out << 'href="'
				out << ConfigurationHolder.config.grails.serverURL
				out << getPageLink(newPage, person)

				out << '">'
				out << (direction == 'next' ? '&#8595; previous entries' : '&#8593; more recent entries')
				out << '</a>'
				out << '</div>'
			}
		}
	}

	private String getPageLink(int page, String person) {
		String url = ""
		if (person) {
			url += "/person/$person"
		}
		if (page > 1) {
			url += "/page/$page"
		} else {
			if (!url) url = '/'
		}
		url
	}

	def imageLink = { attrs ->
		def picture = attrs.remove("picture")
		def format = attrs.remove("format")?:"original"
		def isDownload = attrs.remove("isDownload")

		def formatInstance = picture.getFormatBy(FormatType.valueOf(format.toUpperCase()))
		if (formatInstance) {
			out << formatInstance.url
			if (isDownload) out << "?download=true"
		}
	}
}