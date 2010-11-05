<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="admin" />
        <title>Umbra &raquo; Admin</title>
    </head>
    <body>
        <div class="body">
            <h1>Umbra Admin Panel</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <ul class="menu">
                <li><g:link controller="picture" action="upload" class="button">Upload new photos</g:link> </li>
                <li><g:link controller="entry" action="list" class="button">Entries</g:link></li>
                <li><g:link controller="tag" action="list" class="button">Tags</g:link></li>
            </ul>
        </div>
    </body>
</html>
