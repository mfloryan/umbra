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

class UmbraController {

	def entryService

	def defaultAction = "list"

	def list = { EntryListCommand listCommand ->
		def model = [listCommand: listCommand]
		if (listCommand.person) {
			def p = Person.findByShortNameIlike(listCommand.person)
			if (p) {
				model.person = p
			} else {
				listCommand.person = null
			}
		}
		model.entries = entryService.getEntries(listCommand)
		model
	}

	def show = {
		def permalink = "/${params?.year}/${params?.month}/${params?.id}"
		def entry = Entry.findByPermalink(permalink)
		if (!entry) {
			redirect action: "list"
		} else {
			[entry: entry]
		}
	}

	def rssFeed = {
		response.contentType = "application/atom+xml"

		def newEntries = entryService.getRecent()
		def xml = new MarkupBuilder(response.getWriter())
		xml.mkp.xmlDeclaration(version: '1.0', encoding: 'utf-8')
		xml.feed(xmlns: 'http://www.w3.org/2005/Atom') {
			id("umbra-" + (config.grails.serverUrl - "http://"))
			title(config.umbra.title)
			subtitle(config.umbra.description)
			link(href: config.grails.serverUrl + "/feed", rel: "self")
			link(href: config.grails.serverUrl)
			updated(newEntries.first().publishDate.toString())
			author {
				name("Marcin Floryan")
				uri("http://marcin.floryan.pl")
			}
			rights('(c) Małgorzata Floryan & Marcin Floryan cc-by-nc-sa')
			icon(config.grails.serverUrl + "/images/3f-logo.png")
			generator(version: config.app.version, config.app.name)
			newEntries.each { Entry e ->
				entry {
					title(type: 'text', e.title)
					link(href: config.grails.serverUrl + e.permalink)
					published(e.publishDate.toString())
					updated(e.publishDate.toString())
//					if (e.pictures) {
//						content(type:"xhtml") {
//							e.pictures.each { Picture p ->
//								p.getFormatBy(FormatType.THUMBNAIL).url
//							}
//						}
//					}
				}
			}
		}
	}


	private static ConfigObject getConfig() {
		ConfigurationHolder.config
	}

}