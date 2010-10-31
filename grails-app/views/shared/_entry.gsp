<h2><a href="${grailsApplication.config.grails.serverURL}${entry.permalink}">${entry.title}</a></h2>

<g:if test="${entry.content}">
	<div class="content">${entry.content}</div>
</g:if>

<g:if test="${!listMode}">

</g:if>
