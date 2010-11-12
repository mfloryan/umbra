<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta name="layout" content="umbra"/>
    <title>${grailsApplication.config.umbra.title} &raquo; ${entry.title}</title>
  </head>
  <body>
    <g:render template="/shared/entry" model="[entry:entry, 'listMode':false]"/>
  </body>
</html>