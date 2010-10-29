import mmsquare.umbra.Entry
import grails.util.GrailsUtil

class BootStrap {

  def fixtureLoader

  def init = { servletContext ->

    if (GrailsUtil.environment == "development" && !Entry.count()) {
      fixtureLoader.load("bootstrap")
    }

  }

  def destroy = {
  }
}
