<%@ page import="mmsquare.umbra.Picture" %>
<html>
<head>
	<meta name="layout" content="admin"/>
	<title>Picture</title>
</head>
<body>
	<div class="body">
		<h1>Picture</h1>
		<g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
		</g:if>
		<div class="dialog">
			<table>
				<tbody>

					<tr class="prop">
						<td valign="top" class="name"><g:message code="picture.id.label" default="Id"/></td>
						<td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "id")}</td>

					</tr>

					<tr class="prop">
						<td valign="top" class="name"><g:message code="picture.title.label" default="Title"/></td>
						<td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "title")}</td>

					</tr>

					<tr class="prop">
						<td valign="top" class="name"><g:message code="picture.originalFilename.label" default="Original Filename"/></td>
						<td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "originalFilename")}</td>

					</tr>

					<tr class="prop">
						<td valign="top" class="name"><g:message code="picture.location.label" default="Location"/></td>
						<td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "location")}</td>

					</tr>

					<tr class="prop">
						<td valign="top" class="name"><g:message code="picture.camera.label" default="Camera"/></td>
						<td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "camera")}</td>

					</tr>

					<tr class="prop">
						<td valign="top" class="name"><g:message code="picture.dateCreated.label" default="Date Created"/></td>
						<td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "dateCreated")}</td>

					</tr>

					<tr class="prop">
						<td valign="top" class="name"><g:message code="picture.dateTaken.label" default="Date Taken"/></td>
						<td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "dateTaken")}</td>

					</tr>

					<tr class="prop">
						<td valign="top" class="name"><g:message code="picture.formats.label" default="Formats"/></td>
						<td valign="top" style="text-align: left;" class="value">
							<ul>
								<g:each in="${pictureInstance.formats}" var="f">
									<li><g:link controller="format" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></li>
								</g:each>
							</ul>
						</td>

					</tr>

					<tr class="prop">
						<td valign="top" class="name"><g:message code="picture.lastUpdated.label" default="Last Updated"/></td>
						<td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "lastUpdated")}</td>

					</tr>

					<tr class="prop">
						<td valign="top" class="name"><g:message code="picture.people.label" default="People"/></td>
						<td valign="top" style="text-align: left;" class="value">
							<ul>
								<g:each in="${pictureInstance.people}" var="p">
									<li><g:link controller="person" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
								</g:each>
							</ul>
						</td>

					</tr>

				</tbody>
			</table>
		</div>
		<div class="buttons">
            <g:link class="button-edit" action="edit" id="${pictureInstance?.id}">edit</g:link>
            <g:link class="button-delete" action="delete" id="${pictureInstance?.id}">delete</g:link>
		</div>
	</div>
</body>
</html>
