package mmsquare.umbra

import groovy.xml.MarkupBuilder

class UmbraTagLib {
	static namespace = "umbra"

	def showPicture = { attrs ->
		if (attrs.picture) {
			def linkUrl = pictureFormatForLink(attrs.picture).path
			def imageSrc = pictureFormatForImage(attrs.picture).path

			def html = new MarkupBuilder(out)
			html.a(href: linkUrl) {
				img src: imageSrc
			}
			out						
		}	
	}

	private def pictureFormatForLink(picture) {
		formatForType(picture, FormatType.LARGE)?:formatForType(picture, FormatType.ORIGINAL)		
	}

	private def pictureFormatForImage(picture) {
		formatForType(picture, FormatType.SMALL)?:formatForType(picture, FormatType.ORIGINAL)
	}

	private def formatForType(picture, type) {
		return picture.formats.find {
			it.type == type
		}
	}
}
