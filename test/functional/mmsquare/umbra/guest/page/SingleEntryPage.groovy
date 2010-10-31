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

	String getEntryTitle() {
		driver.findElement(By.cssSelector("h2")).text
	}

	List<EntryPhoto> getPhotos() {
		driver.findElements(By.cssSelector("ul.photos li")).collect { new EntryPhoto(it) }
	}

}

class EntryPhoto {

	@Lazy WebElement image = container.findElement(By.tagName("img"))

	WebElement container

	EntryPhoto(WebElement container) {
		this.container = container
	}
}
