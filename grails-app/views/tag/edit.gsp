<%@ page import="mmsquare.umbra.Tag" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="admin"/>
    <title>Edit Tag</title>
</head>
<body>
    <div class="body">
        <h1>Edit Tag</h1>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <g:hasErrors bean="${tagInstance}">
            <div class="errors">
                <g:renderErrors bean="${tagInstance}" as="list"/>
            </div>
        </g:hasErrors>
        <g:form method="post">
            <g:hiddenField name="id" value="${tagInstance?.id}"/>
            <g:hiddenField name="version" value="${tagInstance?.version}"/>
            <div class="dialog">
                <table>
                    <tbody>

                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="name">Name</label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: tagInstance, field: 'name', 'errors')}">
                                <g:textField name="name" value="${tagInstance?.name}"/>
                            </td>
                        </tr>

                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}"/></span>
                <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
            </div>
        </g:form>
    </div>
</body>
</html>
