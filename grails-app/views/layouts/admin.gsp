<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Umbra &raquo; Admin<g:ifPageProperty name="title"> &raquo; <g:pageProperty name="title"/></g:ifPageProperty></title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'admin.css')}"/>
        <link rel="stylesheet" href="${resource(dir: 'css/smoothness', file: 'jquery-ui-1.8.6.custom.css')}"/>
        <link rel="stylesheet" href="${resource(dir: 'css/fancybox', file: 'jquery.fancybox-1.3.3.css')}"/>
        <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>
        <g:layoutHead/>
        <g:javascript library="jquery" plugin="jquery"/>
        <g:javascript library="jquery-ui-1.8.6.custom.min"/>
        <g:javascript library="fancybox/jquery.fancybox-1.3.3.pack"/>
        <g:javascript library="application"/>
        <g:javascript library="admin"/>
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir: 'images', file: 'spinner.gif')}" alt="${message(code: 'spinner.alt', default: 'Loading...')}"/>
        </div>
        <header>
            <div id="umbraLogo"><a href="${createLink(uri: '/admin/')}"><img src="${resource(dir: 'images', file: 'umbra-logo.png')}" alt="Umbra" border="0"/></a><br />
            <h3>Family Photo Blog</h3></div>
            <nav>
                <div class="nav">
                    <span class="menuButton"><a class="home" href="${createLink(uri: '/admin/')}">Home</a></span>
                    <span class="menuButton"><g:link class="list" controller="picture" action="list">Pictures</g:link></span>
                    <span class="menuButton"><g:link class="list" controller="person" action="list">People</g:link></span>
                    <span class="menuButton"><g:link class="list" controller="tag" action="list">Tags</g:link></span>
                </div>
            </nav>
        </header>
        <g:layoutBody/>
    </body>
</html>