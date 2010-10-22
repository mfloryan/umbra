class UrlMappings {

  static mappings = {
//		"/$controller/$action?/$id?"{
//			constraints {
//				// apply constraints here
//			}
//		}

    "/$year/$month/$id"(controller: "entry", action: "show") {
      constraints {
        year(matches: /\d{4}/)
        month(matches: /\d{2}/)
      }
    }

    "/$lang/"(controller: "entry")

    "/"(controller: "entry")

    "500"(controller: 'entry', action: 'show')
  }
}
