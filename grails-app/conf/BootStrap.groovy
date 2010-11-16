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

		createUser('mfloryan','be8e0faf356b21ae6b7c5a4bfaf2c2bc19a45287c65b87bcb2e0bf6a48bc6470')
		createUser('magga','a81066f036f7693b59146f1492c3f15d1d5822b31d0c45acf97360b15b3a184a')

		if (GrailsUtil.environment == "development") {
			createUser('admin', new Sha256Hash('admin').toHex())
			if (!Entry.count()) fixtureLoader.load("bootstrap")
			if (!Person.count()) fixtureLoader.load("furniture/people")
			if (!Tag.count()) fixtureLoader.load("furniture/tags")
		}
	}

	def destroy = {
	}

	private createUser(String userName, String passwordHash) {
		if (!User.findByUsername(userName)) {
			def user = new User(username: userName, passwordHash: passwordHash)
			user.addToPermissions("*:*")
			user.save()
		}
	}
}
