<%@ page import="mmsquare.umbra.FormatType" %>
<div class="entry">
    <div class="date">
        <b>${entry.publishDate.dayOfMonth}</b><br/>
        <small>${entry.publishDate.monthOfYear().getAsShortText(Locale.ENGLISH).toUpperCase()}</small>
    </div>
    <h2><a href="${createLink(uri: entry.permalink)}">${entry.title}</a></h2>
    <div class="entry-content">
        <g:if test="${entry.pictures}">
            <ul class="pictures">
                <g:each in="${entry.pictures}" var="picture">
                    <li>
                        <div class="picture-box">
                            <div class="picture">
                                <umbra:showPicture picture="${picture}"/>
                                <g:if test="${picture.title}">
                                    <br/>
                                    <span class="title">${picture.title}</span>
                                </g:if>
                            </div>
                            <g:if test="${!listMode}">
                                <div class="download">
                                    <a href="${umbra.imageLink([picture: picture, format: "original", isDownload: true])}">download</a> (<umbra:formattedFileSize size="${picture.getFormatBy(FormatType.ORIGINAL).fileSize}"/>)
                                </div>
                            </g:if>
                        </div>
                    </li>
                </g:each>
            </ul>
            <br clear="both"/>
        </g:if>
        <g:if test="${entry.content}">
            <div class="entry-content">${entry.content}</div>
        </g:if>
        <g:if test="${!listMode}">
            <umbra:tags tags="${entry.tags}"/>
        </g:if>
    <fb:like layout="button_count" href="${createLink(uri: entry.permalink, absolute: true)}"></fb:like>
    </div>
</div>