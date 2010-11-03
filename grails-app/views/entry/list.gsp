<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="umbra"/>
</head>
<body>
<g:if test="${listCommand.page > 1}">
    <div class="page-next">
        <g:link action="list" params="[page:listCommand.page-1]">Prev</g:link>
    </div>
</g:if>
<ul class="entries">
	<g:each in="${entries}" var="entry">
		<li class="entry"><g:render template="/shared/entry" model="[entry:entry, 'listMode':true]"/></li>
	</g:each>
</ul>
<g:if test="${listCommand.totalOnCurrentPage < entries.totalCount}">
    <div class="page-next">
        <g:link action="list" params="[page:2]">Next</g:link>
    </div>
</g:if>
</body>
</html>