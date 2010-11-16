dataSource {
	pooled = true
	driverClassName = "org.hsqldb.jdbcDriver"
	username = "sa"
	password = ""
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = true
	cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
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
			pooled = true
			driverClassName = "org.postgresql.Driver"
			dialect = net.sf.hibernate.dialect.PostgreSQLDialect
			url="jdbc:postgresql://localhost:5432/umbra"
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
			dbCreate = "update"
			url = "jdbc:hsqldb:file:prodDB;shutdown=true"
		}
	}
}
