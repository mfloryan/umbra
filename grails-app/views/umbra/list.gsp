<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="umbra"/>
    </head>
    <body>
        <umbra:pagination
                direction="prev"
                page="${listCommand.page}"
                person="${listCommand.person}"
                totalPages="${listCommand.getTotalPages(entries.totalCount)}"/>
        <ul class="entries">
            <g:each in="${entries}" var="entry">
                <li class="entry"><g:render template="/shared/entry" model="[entry:entry, 'listMode':true]"/></li>
            </g:each>
        </ul>
        <umbra:pagination
                direction="next"
                page="${listCommand.page}"
                person="${listCommand.person}"
                totalPages="${listCommand.getTotalPages(entries.totalCount)}"/>
    </body>
</html>