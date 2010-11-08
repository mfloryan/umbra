<%@ page import="mmsquare.umbra.Entry" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="admin"/>
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
                    <g:renderErrors bean="${entryInstance}" as="list"/>
                </div>
            </g:hasErrors>

            <g:form action="save">

                <div class="dialog">
                    <fieldset>
                        <legend>Entry</legend>

                        <dl>
                            <dt><label for="title">Title</label></dt>
                            <dd><g:textField name="title" value="${entryInstance?.title}"/></dd>
                        </dl>

                       <dl>
                            <dt><label for="permalink">Permalink</label></dt>
                            <dd><g:textField name="permalink" value="${entryInstance?.permalink}"/></dd>
                        </dl>

                        <dl>
                            <dt><label for="publishDate">publishDate</label></dt>
                            <dd><g:textField name="publishDate" value="${entryInstance?.publishDate}"/></dd>
                        </dl>

                        <dl>
                            <dt><label for="content">content</label></dt>
                            <dd><g:textArea name="content" cols="60" rows="3" value="${entryInstance?.content}"/></dd>
                        </dl>
                    </fieldset>
                    <fieldset>
                        <legend>Pictures</legend>
                        <g:if test="${pictures}">
                            <ul class="pictures">
                                <g:each in="${pictures}" var="picture">
                                    <li>
                                        ${picture.originalFilename}
                                    </li>
                                </g:each>
                            </ul>
                        </g:if>
                    </fieldset>
                    <fieldset>
                        <legend>Tags</legend>
                            <ul class="tags">
                                <g:each in="${tags}" var="tag">
                                    <li>
                                        <input type="checkbox" name="tags" value="${tag.id}" id="${tag.name}">
                                        <label for="${tag.name}">${tag.name}</label>
                                    </li>
                                </g:each>
                                <li>  <g:textField name="newTag" value="${newTag}"></g:textField> </li>
                            </ul>
                    </fieldset>

                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
