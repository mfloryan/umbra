<%@ page import="mmsquare.umbra.Person; mmsquare.umbra.Picture" %>
<html>
<head>
    <meta name="layout" content="admin"/>
    <title>Edit Picture</title>
</head>
<body>
    <div class="body">
        <h1>Edit ${pictureInstance.originalFilename}</h1>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <g:hasErrors bean="${pictureInstance}">
            <div class="errors">
                <g:renderErrors bean="${pictureInstance}" as="list"/>
            </div>
        </g:hasErrors>
        <g:form method="post">
            <g:hiddenField name="id" value="${pictureInstance?.id}"/>
            <g:hiddenField name="version" value="${pictureInstance?.version}"/>
            <div class="dialog">
                <fieldset class="form">
                    <legend>Picture</legend>
                    <div style="text-align: center;">
                        <img src="<umbra:imageLink picture="${pictureInstance}" format="small"/>" alt="${pictureInstance.originalFilename}">
                    </div>
                    <ul>
                        <li>
                            <label for="title">Title</label>
                            <g:textField name="title" value="${pictureInstance.title}"/>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <label>People</label>
                            <div style="display: inline-block; vertical-align: top;">
                                <g:each in="${Person.listOrderByDisplayOrder()}" var="person">
                                    <input name="people" id="person${person.id}" type="checkbox" value="${person.id}" <g:if test="${pictureInstance.find { it.id == person.id} }">checked="checked"</g:if> /><label for="person${person.id}">${person.shortName}</label>
                                    <br/>
                                </g:each>
                            </div>
                        </li>
                    </ul>
                </fieldset>
            </div>
            <div class="buttons">
                <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}"/></span>
                <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
            </div>
        </g:form>
    </div>
</body>
</html>
