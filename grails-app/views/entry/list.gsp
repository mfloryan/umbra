<%@ page import="mmsquare.umbra.Entry" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="admin" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create">Create entry</g:link></span>
        </div>
        <div class="body">
            <h1>Entries</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="id" />
                            <g:sortableColumn property="publishDate" title="Publish Date" />
                            <g:sortableColumn property="permalink" title="Permalink" />
                            <g:sortableColumn property="title" title="Title" />
                            <g:sortableColumn property="dateCreated" title="Date Created" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${entryInstanceList}" status="i" var="entryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td><g:link action="show" id="${entryInstance.id}">${fieldValue(bean: entryInstance, field: "id")}</g:link></td>
                            <td><g:fieldValue bean="${entryInstance}" field="publishDate" /></td>
                            <td>${fieldValue(bean: entryInstance, field: "permalink")}</td>
                            <td>${fieldValue(bean: entryInstance, field: "title")}</td>
                            <td>${fieldValue(bean: entryInstance, field: "dateCreated")}</td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${entryInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
