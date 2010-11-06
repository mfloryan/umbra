<!DOCTYPE HTML>
<html>
    <head>
        <title><g:layoutTitle default="Umbra - Admin" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'admin.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css/smoothness',file:'jquery-ui-1.8.6.custom.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="jquery" plugin="jquery"/>
        <g:javascript library="jquery-ui-1.8.6.custom.min" />
        <g:javascript library="application" />
        <g:javascript library="admin" />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="grailsLogo"><a href="http://grails.org"><img src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails" border="0" /></a></div>
        <g:layoutBody />
    </body>
</html>