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

package mmsquare.umbra

import grails.orm.PagedResultList
import org.joda.time.DateTime
import org.hibernate.Criteria
import org.hibernate.FetchMode

class EntryService {

	static transactional = true

	PagedResultList getEntries(EntryListCommand filterCommand) {
		Entry.createCriteria().list([max: filterCommand.ENTRIES_PER_PAGE, offset: filterCommand.offset]) {
			le("publishDate", new DateTime())
			if (filterCommand.person) {
				pictures {
					people {
						ilike("shortName", filterCommand.person)
					}
				}
				resultTransformer Criteria.DISTINCT_ROOT_ENTITY
			}
			order("publishDate", "desc")
		}
	}

	List<Entry> getRecent() {
		Entry.createCriteria().list() {
			between("publishDate", new DateTime().minusDays(30), new DateTime())			
			order("publishDate", "desc")
		}
	}
}