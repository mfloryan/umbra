<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="admin"/>
        <title>Umbra &raquo; Admin &raquo; Upload new Photos</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/admin/')}">Home</a></span>
        </div>
        <div class="body">
            <h1>Photo upload</h1>
            <g:if test="${flash.message}">
                <div class="ui-widget">
				    <div style="padding: 0pt 0.7em;" class="ui-state-error ui-corner-all">
					    <p style="margin: 5px;"><span style="float: left; margin-right: 0.3em;" class="ui-icon ui-icon-alert"></span>
					<strong>Alert:</strong> ${flash.message}</p>
				</div>
			</div>
            </g:if>
            <g:form action="uploaded" method="post" enctype="multipart/form-data">
                <div class="dialog">
                    <ul>
                        <li><input type="file" name="picture1"/></li>
                        <li><input type="file" name="picture2"/></li>
                        <li><input type="file" name="picture3"/></li>
                    </ul>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit name="upload" action="uploaded" class="save" value="Upload"/></span>
                    <span class="button"><g:actionSubmit name="upload" action="uploadedForEntry" class="save" value="Upload and publish"/></span>
                </div
            </g:form>
        </div>
    </body>
</html>
