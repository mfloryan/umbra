<div class="date"><b>${entry.publishDate.dayOfMonth}</b>${entry.publishDate.monthOfYear}/${entry.publishDate.year}</div>
<h2><a href="${grailsApplication.config.grails.serverURL}${entry.permalink}">${entry.title}</a></h2>

<g:if test="${entry.pictures}">
	<ul class="pictures">
	<g:each in="${entry.pictures}" var="picture">
		<li><umbra:showPicture picture="${picture}"/></li>
	</g:each>
	</ul>
</g:if>

<g:if test="${entry.content}">
	<div class="content">${entry.content}</div>
</g:if>

<g:if test="${!listMode}">

</g:if>
