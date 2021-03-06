<%@ page import="mmsquare.umbra.Entry" %>
<html>
<head>
    <meta name="layout" content="admin"/>
</head>
<body>
    <div class="body">
        <g:link controller="picture" action="upload" class="button">Upload pictures</g:link>
        <g:link controller="entry" action="create" class="button">Create entry</g:link>
        <h1>Entries</h1>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <div class="list">
            <table>
                <thead>
                    <tr>
                        <g:sortableColumn property="id" title="id"/>
                        <g:sortableColumn property="publishDate" title="Publish Date"/>
                        <g:sortableColumn property="permalink" title="Permalink"/>
                        <g:sortableColumn property="title" title="Title"/>
                        <th>Pictures</th>
                        <th>Tags</th>
                        <g:sortableColumn property="dateCreated" title="Date Created"/>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${entryInstanceList}" status="i" var="entryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td><g:link action="show" id="${entryInstance.id}">${fieldValue(bean: entryInstance, field: "id")}</g:link></td>
                            <td><g:fieldValue bean="${entryInstance}" field="publishDate"/></td>
                            <td>${fieldValue(bean: entryInstance, field: "permalink")}</td>
                            <td>${fieldValue(bean: entryInstance, field: "title")}</td>
                            <td>
                                <g:each in="${entryInstance.pictures}" var="picture">
                                    <g:link controller="picture" action="show" id="${picture.id}">
                                        <img src="<umbra:imageLink picture="${picture}" format="thumbnail"/>" alt="${picture.originalFilename}" class="picture thumbnail">
                                    </g:link>
                                </g:each>
                            </td>
                            <td>${entryInstance.tags.name.join(', ')}</td>
                            <td>${fieldValue(bean: entryInstance, field: "dateCreated")}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>
        <div class="paginateButtons">
            <g:paginate total="${entryInstanceTotal}"/>
        </div>
        <div class="buttons">
            <g:link action="signOut" controller="auth" class="button-auth">Sign out</g:link>
        </div>
    </div>
</body>
</html>