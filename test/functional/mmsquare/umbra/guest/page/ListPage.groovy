package mmsquare.umbra.guest.page

import mmsquare.umbra.Page
import org.openqa.selenium.By
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.openqa.selenium.WebElement

class ListPage extends Page {

	private final static URL = "/"

	String getUrl() {
		URL
	}

	boolean verify() {
		this.pageTitle == ConfigurationHolder.config.umbra.title
	}

	def getEntries() {
		driver.findElements(By.cssSelector("ul.entries li.entry")).collect { new EntryDetails(it) }
	}
}

class EntryDetails {

	WebElement element

	EntryDetails(WebElement element) {
		this.element = element
	}

	def getUrl() {
		element.findElement(By.cssSelector("h2 a")).getAttribute("href")
	}
}