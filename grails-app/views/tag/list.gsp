<%@ page import="mmsquare.umbra.Tag" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="admin" />
        <title>Umbra &raquo; Admin &raquo; Tags</title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'tag.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'tag.name.label', default: 'Name')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${tagInstanceList}" status="i" var="tagInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${tagInstance.id}">${fieldValue(bean: tagInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: tagInstance, field: "name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${tagInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
