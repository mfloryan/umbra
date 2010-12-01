<%@ page import="mmsquare.umbra.Entry" %>
<html>
<head>
    <meta name="layout" content="admin"/>
    <title>Entry</title>
</head>
<body>
    <div class="body">
        <h1>Entry</h1>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <div class="dialog">
            <table>
                <tbody>

                    <tr class="prop">
                        <td valign="top" class="name">Title</td>
                        <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "id")}</td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">Title</td>
                        <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "title")}</td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">Publish Date</td>
                        <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "publishDate")}</td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">Permalink</td>
                        <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "permalink")}</td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">Content</td>
                        <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "content")}</td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">Pictures</td>
                        <td valign="top" style="text-align: left;" class="value">
                            <g:each in="${entryInstance.pictures}" var="picture">
                                <g:link controller="picture" action="show" id="${picture.id}">
                                    <img src="<umbra:imageLink picture="${picture}" format="thumbnail"/>" alt="${picture.originalFilename}" class="picture thumbnail">
                                </g:link>
                            </g:each>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">Tags</td>

                        <td valign="top" style="text-align: left;" class="value">
                            <ul>
                                <g:each in="${entryInstance.tags}" var="t">
                                    <li><g:link controller="tag" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
                                </g:each>
                            </ul>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">Date Created</td>
                        <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "dateCreated")}</td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">Last Updated</td>
                        <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "lastUpdated")}</td>
                    </tr>

                </tbody>
            </table>
            <div class="buttons">
                <g:link class="button-edit" action="edit" id="${entryInstance?.id}">edit</g:link>
                <g:link class="button-delete" action="delete" id="${entryInstance?.id}">delete</g:link>
            </div>
        </div>
    </div>
</body>
</html>
