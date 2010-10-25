package mmsquare.umbra.webdriver

import mmsquare.umbra.Page
import org.codehaus.groovy.grails.commons.ApplicationHolder
import grails.plugin.fixtures.FixtureLoader
import spock.lang.Shared

/* Created 23-Oct-2010 14:00:22 by mfloryan */

class WebSpec {

  private Page currentPage
  @Shared protected FixtureLoader fixtureLoader = ApplicationHolder.application?.mainContext?.fixtureLoader


  protected <T extends Page> T getPage() { currentPage }

  /**
   * Navigates to the specified page type and returns a new instance
   */
  void open(Class<? extends Page> pageType, Map params = [:]) {
    def driver = DriverContext.driver
    currentPage = pageType.newInstance()
    currentPage.driver = driver
    currentPage.params = params
    def url = currentPage.baseUrl + currentPage.getUrl()
    driver.get(url)
    currentPage.verify()
  }

  /**
   * Confirms that the web driver is on the specified page type and returns a new instance
   */
  boolean verifyAt(Class<? extends Page> pageType, Map params = [:]) {
    def driver = DriverContext.driver
    currentPage = pageType.newInstance()
    currentPage.driver = driver
    currentPage.params = params
    currentPage.verify()
  }

  def cleanupSpec() {
    DriverContext.closeDriver()
  }
}
