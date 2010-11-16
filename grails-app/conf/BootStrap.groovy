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

import mmsquare.umbra.Entry
import grails.util.GrailsUtil
import mmsquare.umbra.Person
import mmsquare.umbra.Tag
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import mmsquare.umbra.User
import org.apache.shiro.crypto.hash.Sha256Hash

class BootStrap {

	def fixtureLoader

	def init = { servletContext ->

		File dir = ConfigurationHolder.config.umbra.image.base.dir
		if (!dir.isDirectory()) {
			dir.mkdirs()
		}

		if (!User.findByUsername("admin")) {
			def password = new Sha256Hash('florek').toHex()
			def user = new User(username: "admin", passwordHash: password)
			user.addToPermissions("*:*")
			user.save()
		}

		if (GrailsUtil.environment == "development") {
			if (!Entry.count()) fixtureLoader.load("bootstrap")
			if (!Person.count()) fixtureLoader.load("furniture/people")
			if (!Tag.count()) fixtureLoader.load("furniture/tags")
		}

	}

	def destroy = {
	}
}
