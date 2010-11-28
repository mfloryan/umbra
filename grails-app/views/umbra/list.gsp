<html>
<head>
	<meta name="layout" content="umbra"/>
	<meta property="og:type" content="blog"/>
	<meta property="og:url" content="${createLink(uri: "/", absolute: true)}"/>
	<meta property="og:description" content="${grailsApplication.config.umbra.description}"/>
</head>
<body>
	<umbra:pagination
			direction="prev"
			page="${listCommand.page}"
			person="${listCommand.person}"
			totalPages="${listCommand.getTotalPages(entries.totalCount)}"/>
	<g:if test="${person}">
		<h3>Showing pictures of ${person.shortName}</h3>
	</g:if>
	<ul class="entries">
		<g:each in="${entries}" var="entry">
			<li><g:render template="/shared/entry" model="[entry:entry, 'listMode':true]"/></li>
		</g:each>
	</ul>
	<umbra:pagination
			direction="next"
			page="${listCommand.page}"
			person="${listCommand.person}"
			totalPages="${listCommand.getTotalPages(entries.totalCount)}"/>
</body>
</html>