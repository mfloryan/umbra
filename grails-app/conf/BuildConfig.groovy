grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolution = {
  // inherit Grails' default dependencies
  inherits("global") {
    // uncomment to disable ehcache
    // excludes 'ehcache'
  }
  log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
  repositories {
    grailsPlugins()
    grailsHome()
    grailsCentral()

    // uncomment the below to enable remote dependency resolution
    // from public Maven repositories
    //mavenLocal()
    mavenCentral()
    //mavenRepo "http://snapshots.repository.codehaus.org"
    //mavenRepo "http://repository.codehaus.org"
    //mavenRepo "http://download.java.net/maven/2/"
    //mavenRepo "http://repository.jboss.com/maven2/"
    mavenRepo "http://m2repo.spockframework.org/ext/"
  }
  dependencies {
    // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

//    test("org.gmock:gmock:0.8.1") {
//        excludes "junit", "groovy-all"
//    }
    test("org.spockframework:spock-core:0.5-groovy-1.7-SNAPSHOT") {
        excludes "junit", "groovy-all"
    }    

    // runtime 'mysql:mysql-connector-java:5.1.5'
    plugins {
      test ":spock:0.5-groovy-1.7-SNAPSHOT"
      test ":build-test-data:1.1.1"
      test("com.energizedwork:spock-webdriver:1.0.2-SNAPSHOT") {
        excludes "selenium-htmlunit-driver"
	    excludes "selenium-ie-driver"
      }
    }
  }
}