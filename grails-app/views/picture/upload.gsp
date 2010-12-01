<html>
<head>
    <meta name="layout" content="admin"/>
    <title>New pictures upload</title>
</head>
<body>
    <div class="body">
        <h1>Pictures upload</h1>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <g:form action="uploaded" method="post" enctype="multipart/form-data">
            <div class="dialog">
                <fieldset class="form">
                    <legend>Files</legend>
                <ul>
                    <li><input type="file" name="picture1"/></li>
                    <li><input type="file" name="picture2"/></li>
                    <li><input type="file" name="picture3"/></li>
                </ul>
                    </fieldset>
                <div class="buttons">
                    <span class="button"><g:actionSubmit name="upload" action="uploaded" class="save" value="Upload"/></span>
                    <span class="button"><g:actionSubmit name="upload" action="uploadedForEntry" class="save" value="Upload and publish"/></span>
                </div>
            </div>
        </g:form>
    </div>
</body>
</html>
