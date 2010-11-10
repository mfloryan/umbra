<!DOCTYPE HTML>
<html>
    <head>
        <title><g:layoutTitle default="${grailsApplication.config.umbra.title}"/></title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'umbra.css')}"/>
        <g:layoutHead/>
    </head>
    <body>
        <div class="page">
            <header>
                <h1><a href="${grailsApplication.config.grails.serverURL}">3F</a></h1>
				photographic journey through lives of Zosia, Matylda and Franek
            </header>
            <div class="content">
                <g:layoutBody/>
            </div><div class="sidebar">
				<umbra:people person="${params?.person}"/>
            </div>
            <footer>
            &copy; 2010 <a href="http://marcin.floryan.pl" target="_blank">Marcin Floryan</a> & Małgorzata Floryan. Powered by ${grailsApplication.metadata['app.name']} ${grailsApplication.metadata['app.version']} on grails ${grailsApplication.metadata['app.grails.version']} <br/>
                <span class="license">
                    <a href="http://creativecommons.org/licenses/by-nc-sa/3.0/" rel="license">
                        <img src="http://i.creativecommons.org/l/by-nc-sa/3.0/80x15.png" alt="Creative Commons License">
                    </a>
                    All content of this site is licenced under a <a href="http://creativecommons.org/licenses/by-nc-sa/3.0/" rel="license">Creative Commons Licence</a>.
                </span>
            </footer>
        </div>
    </body>
</html>