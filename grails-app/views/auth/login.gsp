<html>
<head>
    <meta name="layout" content="admin"/>
    <title>Login</title>
</head>
<body id="login">
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
    <fieldset class="form" id="login">
        <legend>Admin</legend>
        <g:form action="signIn">
            <input type="hidden" name="targetUri" value="${targetUri}"/>
            <ul>
                <li>
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" value="${username}"/>
                </li>
                <li>
                    <label>Password:</label>
                    <input type="password" name="password" value=""/>
                </li>
            </ul>
            <div class="buttons">
                <button type="submit" class="button-auth">Sign in</button>
            </div>
            </fieldset>
        </g:form>
    </div>
</body>
</html>
