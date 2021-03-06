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

	List<EntryDetails> getEntries() {
		driver.findElements(By.cssSelector("div.entry")).collect { new EntryDetails(it) }
	}

	List<PersonDetails> getPeople() {
		driver.findElements(By.cssSelector("ul.people li")).collect { new PersonDetails(it) }
	}

	def getNextPage() {
		driver.findElement(By.cssSelector("div.pagination a.next"))
	}

	def getPrevPage() {
		driver.findElement(By.cssSelector("div.pagination a.prev"))
	}

	def getSubtitle() {
		driver.findElement(By.tagName("h3")).text
	}
}

class PersonDetails {

	WebElement element

	PersonDetails(WebElement element) {
		this.element = element
	}

	def clickOn() {
		element.findElement(By.tagName("a")).click()
		true
	}

	def isSelected() {
		element.getAttribute("class").contains("selected")
	}
}