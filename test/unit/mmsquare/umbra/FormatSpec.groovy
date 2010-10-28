package mmsquare.umbra

import grails.plugin.spock.UnitSpec

class FormatSpec extends UnitSpec {

  def "Default type of Format is ORIGINAL"() {
    given:
    mockDomain Picture
    def picture = new Picture()

    when:
    def format = new Format(picture: picture)

    then:
    format.type = FormatType.ORIGINAL
  }

  def "Format fails validation with missing properties"() {
    setup:
    mockDomain Format

    when:
    def format = new Format()

    then:
    !format.validate()
    format.errors.hasFieldErrors("width")
    format.errors.hasFieldErrors("height")
    format.errors.hasFieldErrors("path")
    format.errors.hasFieldErrors("picture")
  }

  def "Format with correct properties set validates"() {
    setup:
    mockDomain Picture
    def picture = new Picture()
    mockDomain Format

    when:
    def format = new Format(
      width: 100, height: 200, path: "/some/path/to/file.JPG", picture: picture
    )

    then:
    format.validate()
  }

  def "Only one format can exist with a given path"() {
    given:
    mockDomain Format
    mockDomain Picture
    def picture = new Picture()

    and: "A format exists with some path"
    def format1 = new Format(width:100, height:200, path: "/some/path/to/file.jpg", picture:picture)

    when: "Then first format is saved"
    format1.save(failOnError:true)

    and: "New format with the same path is crated"
    def format2 = new Format(width:200, height: 300, path: "/some/path/to/file.jpg", picture:picture)

    then: "Validation fails for the second format"
    !format2.validate()
  }

  def "Format type sets width from hint if hint exists and with is not set"() {
    given:
    def picture = new Picture()

    when: "A format is created with LARGE type"
    def format = new Format(height: 200, path:"/file.jpg", picture:picture )
    format.type = FormatType.LARGE

    then:
    format.type == FormatType.LARGE
    format.width == FormatType.LARGE.formatTypeWidth

    when: "A format is created with LARGE type and existing width"
    def format1 = new Format(width: 100, height:200, path:"/file.jpg")
    format1.type = FormatType.LARGE

    then:
    format1.width == 100
  }
}
