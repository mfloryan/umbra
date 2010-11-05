<%@ page import="mmsquare.umbra.Entry" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'entry.label', default: 'Entry')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${entryInstance}">
            <div class="errors">
                <g:renderErrors bean="${entryInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${entryInstance?.id}" />
                <g:hiddenField name="version" value="${entryInstance?.version}" />
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
                                  <label for="pictures"><g:message code="entry.pictures.label" default="Pictures" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'pictures', 'errors')}">
                                    
<ul>
<g:each in="${entryInstance?.pictures?}" var="p">
    <li><g:link controller="picture" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="picture" action="create" params="['entry.id': entryInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'picture.label', default: 'Picture')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="publishDate"><g:message code="entry.publishDate.label" default="Publish Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'publishDate', 'errors')}">
                                    
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="tags"><g:message code="entry.tags.label" default="Tags" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'tags', 'errors')}">
                                    <g:select name="tags" from="${mmsquare.umbra.Tag.list()}" multiple="yes" optionKey="id" size="5" value="${entryInstance?.tags*.id}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
