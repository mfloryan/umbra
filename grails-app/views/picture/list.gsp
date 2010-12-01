<%@ page import="mmsquare.umbra.FormatType" %>
<html>
<head>
    <meta name="layout" content="admin"/>
    <title>Pictures</title>
</head>
<body>
    <div class="body">
        <h1>Pictures</h1>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <div class="list">
            <table>
                <thead>
                    <tr>
                        <g:sortableColumn property="id" title="Id"/>
                        <g:sortableColumn property="originalFilename" title="Original filename"/>
                        <g:sortableColumn property="title" title="Title"/>
                        <th>People</th>
                        <th>Formats</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${pictureInstanceList}" status="i" var="pictureInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td><g:link action="show" id="${pictureInstance.id}">
                                ${fieldValue(bean: pictureInstance, field: "id")}
                                <img src="${umbra.imageLink(picture:pictureInstance, format:'thumbnail')}" class="picture thumbnail" />
                            </g:link></td>
                            <td>${fieldValue(bean: pictureInstance, field: "originalFilename")}</td>
                            <td>${fieldValue(bean: pictureInstance, field: "title")}</td>
                            <td>${pictureInstance.people.shortName.join(', ')}</td>
                            <td>
                                <g:each in="${pictureInstance.formats.sort() {it.width}}" var="format">
                                    <span style="width: 100px; text-align: left; display: inline-block;">${format.type}</span>
                                    <span style="width: 80px; text-align: right; display: inline-block;"><umbra:formattedFileSize size="${format.fileSize}"/></span><br/>
                                </g:each>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>
        <div class="paginateButtons">
            <g:paginate total="${pictureInstanceTotal}"/>
        </div>
        <br />
        <g:link action="upload" class="button">upload pictures</g:link>
    </div>
</body>
</html>
