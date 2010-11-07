<%@ page import="mmsquare.umbra.Entry" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="admin" />
        <title>Umbra &raquo; Admin &raquo; New Entry</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/admin/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Entries</g:link></span>
        </div>
        <div class="body">
            <h1>New Entry</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${entryInstance}">
            <div class="errors">
                <g:renderErrors bean="${entryInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >

                <g:each in="${pictures}" var="picture">
                    ${file}                    
                </g:each>

                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="permalink"><g:message code="entry.permalink.label" default="Permalink" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'permalink', 'errors')}">
                                    <g:textArea name="permalink" cols="40" rows="5" value="${entryInstance?.permalink}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title"><g:message code="entry.title.label" default="Title" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'title', 'errors')}">
                                    <g:textField name="title" value="${entryInstance?.title}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="content"><g:message code="entry.content.label" default="Content" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'content', 'errors')}">
                                    <g:textArea name="content" cols="40" rows="5" value="${entryInstance?.content}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="publishDate"><g:message code="entry.publishDate.label" default="Publish Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'publishDate', 'errors')}">
                                    <g:textField name="publishDate" value="${entryInstance?.publishDate}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>