package mmsquare.umbra.guest.page

import mmsquare.umbra.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/* Created 23-Oct-2010 14:05:17 by mfloryan */

class SingleEntryPage extends Page {

	private final static URL = "/"

	String getUrl() {
		params.permalink
	}

	boolean verify() {
		driver.currentUrl =~ /\/[0-9]{4}\/[0-9]{2}\/\.*$/
	}

	EntryDetails getEntry() {
		new EntryDetails(driver.findElement(By.cssSelector("div.entry")))
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

	def getTitle() {
		element.findElement(By.cssSelector("h2")).text
	}

	def getContent() {
		element.findElement(By.cssSelector("div.entry-content")).text
	}

	List<EntryPicture> getPictures() {
		element.findElements(By.cssSelector("ul.pictures li")).collect { new EntryPicture(it) }
	}

	List<WebElement> getDownloads() {
		element.findElements(By.cssSelector("ul.downloads li")).collect { it }
	}
}


class EntryPicture {
	@Lazy WebElement image = container.findElement(By.tagName("img"))

	WebElement container

	EntryPicture(WebElement container) {
		this.container = container
	}

	String getTitle() {
		container.findElement(By.tagName("a")).getAttribute("title")
	}

	String getAlt() {
		image.getAttribute("alt")
	}
}
