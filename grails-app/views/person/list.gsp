<%@ page import="mmsquare.umbra.Person" %>
<html>
    <head>
        <meta name="layout" content="admin" />
        <title>People</title>
    </head>
    <body>
        <div class="body">
            <h1>People</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="${message(code: 'person.id.label', default: 'Id')}" />
                            <g:sortableColumn property="shortName" title="${message(code: 'person.shortName.label', default: 'Short Name')}" />
                            <g:sortableColumn property="fullName" title="${message(code: 'person.fullName.label', default: 'Full Name')}" />
                            <g:sortableColumn property="displayOrder" title="${message(code: 'person.displayOrder.label', default: 'Display Order')}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${personInstanceList}" status="i" var="personInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td><g:link action="show" id="${personInstance.id}">${fieldValue(bean: personInstance, field: "id")}</g:link></td>
                            <td>${fieldValue(bean: personInstance, field: "shortName")}</td>
                            <td>${fieldValue(bean: personInstance, field: "fullName")}</td>
                            <td>${fieldValue(bean: personInstance, field: "displayOrder")}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${personInstanceTotal}" />
            </div>
            <br />
            <g:link action="create" class="button">add person</g:link>
        </div>
    </body>
</html>