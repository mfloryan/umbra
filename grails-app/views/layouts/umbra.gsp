<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:og="http://opengraphprotocol.org/schema/" xmlns:fb="http://www.facebook.com/2008/fbml">
<head>
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'umbra.css')}"/>
	<link rel="stylesheet" href="${resource(dir: 'css/fancybox', file: 'jquery.fancybox-1.3.3.css')}"/>
	<g:javascript library="jquery" plugin="jquery"/>
	<g:javascript library="fancybox/jquery.fancybox-1.3.3.pack"/>
	<g:javascript library="umbra"/>
	<script src="http://connect.facebook.net/en_US/all.js#xfbml=1" type="text/javascript"></script>
	<meta property="og:site_name" content="${grailsApplication.config.umbra.og.site_name}"/>
	<meta property="og:image" content="${resource(dir: 'images', file: 'logo-opengraph.png', absolute: true)}"/>
	<meta property="fb:admins" content="${grailsApplication.config.umbra.facebook.userId}"/>
	<g:layoutHead/>
	<title><g:layoutTitle default="${grailsApplication.config.umbra.title}"/></title>
</head>
<body>
	<div id="page">
		<div id="header">
			<h1><a href="${createLink(uri: "/")}">3F</a></h1>
			${grailsApplication.config.umbra.description}
		</div>
		<div id="main">
			<g:layoutBody/>
		</div><div id="sidebar">
		<umbra:people person="${params?.person}"/>
	</div>
		<div id="footer">
		&copy; 2010 <a href="http://marcin.floryan.pl" target="_blank">Marcin Floryan</a> &amp; Małgorzata Floryan. Powered by ${grailsApplication.metadata['app.name']} ${grailsApplication.metadata['app.version']} on <a href="http://www.grails.org/" target="_blank" title="GRAILS - opensource web development platform">grails ${grailsApplication.metadata['app.grails.version']}</a><br/>
			<span class="license">
				<a href="http://creativecommons.org/licenses/by-nc-sa/3.0/" rel="license">
					<img src="${resource(dir: 'images', file: 'cc-by-nc-sa-3-80x15.png')}" alt="Creative Commons License" title="Attribution-NonCommercial-ShareAlike 3.0" />
				</a>
				All content of this site is licenced under a <a href="http://creativecommons.org/licenses/by-nc-sa/3.0/" rel="license">Creative Commons Licence</a>.
			</span>
		</div>
	</div>
</body>
</html>