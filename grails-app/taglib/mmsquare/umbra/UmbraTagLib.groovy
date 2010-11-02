package mmsquare.umbra

import groovy.xml.MarkupBuilder

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
}