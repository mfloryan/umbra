<%@ page import="mmsquare.umbra.Person" %>
<html>
<head>
    <meta name="layout" content="admin"/>
    <title>New person</title>
</head>
<body>
    <div class="body">
        <h1>New person</h1>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <g:hasErrors bean="${personInstance}">
            <div class="errors">
                <g:renderErrors bean="${personInstance}" as="list"/>
            </div>
        </g:hasErrors>
        <g:form action="save">
            <div class="dialog">
                <table>
                    <tbody>

                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="shortName"><g:message code="person.shortName.label" default="Short Name"/></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: personInstance, field: 'shortName', 'errors')}">
                                <g:textField name="shortName" value="${personInstance?.shortName}"/>
                            </td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="fullName"><g:message code="person.fullName.label" default="Full Name"/></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: personInstance, field: 'fullName', 'errors')}">
                                <g:textField name="fullName" value="${personInstance?.fullName}"/>
                            </td>
                        </tr>


                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="displayOrder"><g:message code="person.displayOrder.label" default="Display Order"/></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: personInstance, field: 'displayOrder', 'errors')}">
                                <g:textField name="displayOrder" value="${fieldValue(bean: personInstance, field: 'displayOrder')}"/>
                            </td>
                        </tr>

                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
            </div>
        </g:form>
    </div>
</body>
</html>