<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="admin" />
        <title>Umbra &raquo; Admin &raquo; Pictures</title>
    </head>
    <body>
        <div class="body">
            <h1>Pictures</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
            </div>
            <div class="paginateButtons">
                <g:paginate total="${pictureInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
