<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="umbra"/>
</head>
<body>
<ul class="entries">
	<g:each in="${entries}" var="entry">
		<li class="entry"><g:render template="/shared/entry" model="[entry:entry, 'listMode':true]"/></li>
	</g:each>
</ul>
</body>
</html>