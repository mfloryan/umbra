<%@ page import="mmsquare.umbra.Person" %>
<head>
	<meta name="layout" content="admin"/>
	<title>Person</title>
</head>
<body>
	<div class="body">
		<h1>People</h1>
		<g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
		</g:if>
		<div class="dialog">
			<table>
				<tbody>

					<tr class="prop">
						<td valign="top" class="name">Id</td>
						<td valign="top" class="value">${fieldValue(bean: personInstance, field: "id")}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">Short Name</td>
						<td valign="top" class="value">${fieldValue(bean: personInstance, field: "shortName")}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">Display Order</td>
						<td valign="top" class="value">${fieldValue(bean: personInstance, field: "displayOrder")}</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">Full Name</td>
						<td valign="top" class="value">${fieldValue(bean: personInstance, field: "fullName")}</td>
					</tr>

				</tbody>
			</table>
		</div>
		<div class="buttons">
			<g:form>
				<g:hiddenField name="id" value="${personInstance?.id}"/>
				<span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
				<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
			</g:form>
		</div>
	</div>
</body>
</html>