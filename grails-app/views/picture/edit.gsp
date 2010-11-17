<%@ page import="mmsquare.umbra.Picture" %>
<html>
<head>
	<meta name="layout" content="admin"/>
	<title>Edit Picture</title>
</head>
<body>
	<div class="body">
		<h1>Edit Picture</h1>
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
				<table>
					<tbody>

						<tr class="prop">
							<td valign="top" class="name">
								<label for="title"><g:message code="picture.title.label" default="Title"/></label>
							</td>
							<td valign="top" class="value ${hasErrors(bean: pictureInstance, field: 'title', 'errors')}">
								<g:textArea name="title" cols="40" rows="5" value="${pictureInstance?.title}"/>
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<label for="originalFilename"><g:message code="picture.originalFilename.label" default="Original Filename"/></label>
							</td>
							<td valign="top" class="value ${hasErrors(bean: pictureInstance, field: 'originalFilename', 'errors')}">
								<g:textArea name="originalFilename" cols="40" rows="5" value="${pictureInstance?.originalFilename}"/>
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<label for="location"><g:message code="picture.location.label" default="Location"/></label>
							</td>
							<td valign="top" class="value ${hasErrors(bean: pictureInstance, field: 'location', 'errors')}">

							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<label for="camera"><g:message code="picture.camera.label" default="Camera"/></label>
							</td>
							<td valign="top" class="value ${hasErrors(bean: pictureInstance, field: 'camera', 'errors')}">
								<g:textField name="camera" value="${pictureInstance?.camera}"/>
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<label for="dateTaken"><g:message code="picture.dateTaken.label" default="Date Taken"/></label>
							</td>
							<td valign="top" class="value ${hasErrors(bean: pictureInstance, field: 'dateTaken', 'errors')}">

							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<label for="formats"><g:message code="picture.formats.label" default="Formats"/></label>
							</td>
							<td valign="top" class="value ${hasErrors(bean: pictureInstance, field: 'formats', 'errors')}">

								<ul>
									<g:each in="${pictureInstance?.formats?}" var="f">
										<li><g:link controller="format" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></li>
									</g:each>
								</ul>
								<g:link controller="format" action="create" params="['picture.id': pictureInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'format.label', default: 'Format')])}</g:link>

							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<label for="people"><g:message code="picture.people.label" default="People"/></label>
							</td>
							<td valign="top" class="value ${hasErrors(bean: pictureInstance, field: 'people', 'errors')}">
								<g:select name="people" from="${mmsquare.umbra.Person.list()}" multiple="yes" optionKey="id" size="5" value="${pictureInstance?.people*.id}"/>
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
