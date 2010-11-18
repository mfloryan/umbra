/*
 * Copyright (c) 2010 Marcin Floryan. http://www.mmsquare.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.war.file = "target/${appName}-${appVersion}.war"
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
		mavenRepo "http://mvnrepository.com/artifact/"
		mavenRepo "http://m2repo.spockframework.org/ext/"
//		mavenReop "http://maven.cedarsoft.com/content/repositories/thirdparty/"
	}
	dependencies {
		// specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

//    test("org.gmock:gmock:0.8.1") {
//        excludes "junit", "groovy-all"
//    }
		test("org.spockframework:spock-core:0.5-groovy-1.7-SNAPSHOT") {
			excludes "junit", "groovy-all"
		}

		compile 'im4java:im4java:1.1.0'
//		compile 'metadata-extractor:2.3.1'
		runtime 'postgresql:postgresql:9.0-801.jdbc4'
		// runtime 'mysql:mysql-connector-java:5.1.5'
		plugins {
			test ":spock:0.5-groovy-1.7-SNAPSHOT"
			test ":build-test-data:1.1.1"
			test("com.energizedwork:spock-webdriver:1.0.2-SNAPSHOT") {
				excludes "selenium-htmlunit-driver"
				excludes "selenium-ie-driver"
			}
			compile("org.grails.plugins:shiro:1.1-SNAPSHOT") {
				excludes "shiro-quartz", "shiro-ehcache"
			}
		}
	}
}