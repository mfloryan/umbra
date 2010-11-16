<div class="entry">
	<div class="date"><b>${entry.publishDate.dayOfMonth}</b><br/>${entry.publishDate.monthOfYear}/${entry.publishDate.year}</div>
	<h2><a href="${createLink(uri:entry.permalink)}">${entry.title}</a></h2>
	<div class="entry-content">
		<g:if test="${entry.pictures}">
			<ul class="pictures">
				<g:each in="${entry.pictures}" var="picture">
					<li><umbra:showPicture picture="${picture}"/></li>
				</g:each>
			</ul>
		</g:if>
		<g:if test="${entry.content}">
			<div class="entry-content">${entry.content}</div>
		</g:if>
		<g:if test="${!listMode}">
			<g:if test="${entry.pictures}">
				Download original pictures
				<ul class="downloads">
					<g:each in="${entry.pictures}" var="picture">
						<li><a href="${umbra.imageLink([picture:picture, format:"original" ,isDownload:true])}">${picture.originalFilename}</a></li>
					</g:each>
				</ul>
			</g:if>
		</g:if>
	</div>
</div>