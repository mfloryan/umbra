dataSource {
	pooled = true
//	driverClassName = "org.hsqldb.jdbcDriver"
	username = "sa"
	password = ""
}
hibernate {
	cache.use_second_level_cache = false
	cache.use_query_cache = false
//	cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
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

// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:hsqldb:file:devDB;shutdown=true"
		}
	}
	developmentPg {
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop','update'
			driverClassName = "org.postgresql.Driver"
			dialect = "mmsquare.hibernate.dialect.PostgreSQL82Dialect"
			url = "jdbc:postgresql://localhost:5432/umbra"
			username = "umbra"
			password = "umbra"
		}
	}
	test {
		dataSource {
			dbCreate = "create-drop"
			url = "jdbc:hsqldb:mem:testDb"
			//loggingSql = true 
		}
	}
	production {
		dataSource {
			dbCreate = "validate"
			driverClassName = "org.postgresql.Driver"
			dialect = "mmsquare.hibernate.dialect.PostgreSQL82Dialect"
			host = "localhost"
			database = "umbra"
			url = "jdbc:postgresql://${host}:5432/${database}"
			username = "umbra"
			password = "umbra"
		}
	}
}