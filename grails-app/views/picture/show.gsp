<%@ page import="mmsquare.umbra.Picture" %>
<html>
<head>
    <meta name="layout" content="admin"/>
    <title>Picture</title>
</head>
<body>
    <div class="body">
        <h1>${pictureInstance.originalFilename}</h1>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <div style="display: inline-block;">
            <img src="<umbra:imageLink picture="${pictureInstance}" format="small"/>" alt="${pictureInstance.originalFilename}">
        </div>

        <table style="display: inline-block;">
            <tbody>
                <tr class="prop">
                    <td valign="top" class="name">Id</td>
                    <td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "id")}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name">Title</td>
                    <td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "title")}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name">Original Filename</td>
                    <td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "originalFilename")}</td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">Date Taken</td>
                    <td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "dateTaken")}</td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">Camera</td>
                    <td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "camera")}</td>

                </tr>


                <tr class="prop">
                    <td valign="top" class="name">Location</td>
                    <td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "location")}</td>

                </tr>




                <tr class="prop">
                    <td valign="top" class="name">Formats</td>
                    <td valign="top" style="text-align: left;" class="value">
                        <ul>
                            <g:each in="${pictureInstance.formats.sort { it.width }}" var="f">
                                <li>${f.type} : ${f.width} x ${f.height}, ${f.fileSize} B</li>
                            </g:each>
                        </ul>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">People</td>
                    <td valign="top" style="text-align: left;" class="value">
                        <ul>
                            <g:each in="${pictureInstance.people}" var="p">
                                <li><g:link controller="person" action="show" id="${p.id}">${p.shortName}</g:link></li>
                            </g:each>
                        </ul>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">Date Created</td>
                    <td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "dateCreated")}</td>

                </tr>


                <tr class="prop">
                    <td valign="top" class="name">Last Updated</td>
                    <td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "lastUpdated")}</td>
                </tr>

                <tr><td colspan="2">
                    <g:link class="button-edit" action="edit" id="${pictureInstance?.id}">edit</g:link>
                    <g:link class="button-delete" action="delete" id="${pictureInstance?.id}">delete</g:link>
                </td></tr>

            </tbody>
        </table>
    </div>
</body>
</html>
