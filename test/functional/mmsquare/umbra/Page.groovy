package mmsquare.umbra

import org.openqa.selenium.WebDriver

/* Created 23-Oct-2010 14:03:43 by mfloryan */

abstract class Page {
	protected WebDriver driver
	protected Map params = [:]

	abstract String getUrl()

	abstract boolean verify()

	String getBaseUrl() {
		DriverContext.config.webdriver.base.url + "/catalogue"
	}

	String getPageTitle() {
		driver.title
	}
}
