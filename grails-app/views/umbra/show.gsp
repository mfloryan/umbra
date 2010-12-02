<html>
<head>
	<meta name="layout" content="umbra"/>
	<meta property="og:type" content="article"/>
	<meta property="og:title" content="${entry.publishDate.dayOfMonth}/${entry.publishDate.monthOfYear}/${entry.publishDate.year} ${entry.title}"/>
	<meta property="og:url" content="${createLink(uri: entry.permalink, absolute: true)}"/>
	<title>${grailsApplication.config.umbra.title} &raquo; ${entry.title}</title>
</head>
<body>
	<g:render template="/shared/entry" model="[entry:entry, 'listMode':false]"/>
</body>
</html>