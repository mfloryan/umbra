import mmsquare.umbra.Entry
import grails.util.GrailsUtil
import mmsquare.umbra.Person
import mmsquare.umbra.Tag

class BootStrap {

  def fixtureLoader

  def init = { servletContext ->

    if (GrailsUtil.environment == "development") {
      if (!Entry.count()) fixtureLoader.load("bootstrap")
	  if (!Person.count()) fixtureLoader.load("furniture/people")
	  if (!Tag.count()) fixtureLoader.load("furniture/tags")
    }

  }

  def destroy = {
  }
}
