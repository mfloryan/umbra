<!DOCTYPE HTML>
<html>
    <head>
        <title><g:layoutTitle default="${grailsApplication.config.umbra.title}"/></title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'umbra.css')}"/>
        <link rel="stylesheet" href="${resource(dir:'css/fancybox',file:'jquery.fancybox-1.3.3.css')}" />
        <g:javascript library="jquery" plugin="jquery"/>
        <g:javascript library="fancybox/jquery.fancybox-1.3.3.pack" />
        <g:javascript library="umbra" />
        <g:layoutHead/>
    </head>
    <body>
        <div class="page">
            <header>
                <h1><a href="${grailsApplication.config.grails.serverURL}">3F</a></h1>
				photographic journey through lives of Zosia, Matylda and Franek
            </header>
            <section title="Content" id="main">
                <g:layoutBody/>
            </section><section id="sidebar">
				<umbra:people person="${params?.person}"/>
            </section>
            <footer>
            &copy; 2010 <a href="http://marcin.floryan.pl" target="_blank">Marcin Floryan</a> & Małgorzata Floryan. Powered by ${grailsApplication.metadata['app.name']} ${grailsApplication.metadata['app.version']} on <a href="http://www.grails.org/" target="_blank" title="GRAILS - opensource web development platform">grails ${grailsApplication.metadata['app.grails.version']}</a><br/>
                <span class="license">
                    <a href="http://creativecommons.org/licenses/by-nc-sa/3.0/" rel="license">
                        <img src="${resource(dir:'images',file:'cc-by-nc-sa-3-80x15.png')}" alt="Creative Commons License" title="Attribution-NonCommercial-ShareAlike 3.0">
                    </a>
                    All content of this site is licenced under a <a href="http://creativecommons.org/licenses/by-nc-sa/3.0/" rel="license">Creative Commons Licence</a>.
                </span>
            </footer>
        </div>
    </body>
</html>