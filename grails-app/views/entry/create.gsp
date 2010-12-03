<%@ page import="mmsquare.umbra.FormatType; mmsquare.umbra.Person; mmsquare.umbra.Tag; mmsquare.umbra.Entry" %>
<html>
<head>
    <meta name="layout" content="admin"/>
    <title>New Entry</title>
</head>
<body>
    <div class="body">
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <g:hasErrors bean="${entryInstance}">
            <div class="errors">
                <g:renderErrors bean="${entryInstance}" as="list"/>
            </div>
        </g:hasErrors>
        <g:form action="save">
            <div class="dialog"  style="width: 720px; margin: auto;">
                <fieldset class="form">
                    <legend>New Entry</legend>
                    <ul>
                        <li>
                            <label for="title">Title</label>
                            <span class="permalink-data"><g:textField name="title" value="${entryInstance?.title}"/></span>
                        </li>
                        <li>
                            <label for="content">Content</label>
                            <g:textArea name="content" value="${entryInstance?.content}"/>
                        </li>
                        <li>
                            <label for="permalink">Permalink</label>
                            <g:textField name="permalink" value="${entryInstance?.permalink}"/>
                        </li>
                        <li>
                            <label for="publishDate">Publish Date</label>
                            <span class="permalink-data"><joda:dateTimePicker name="publishDate" value="${entryInstance?.publishDate}"/></span>
                        </li>
                    </ul>
                </fieldset>
                <fieldset>
                    <legend>Pictures</legend>
                    <g:if test="${pictures}">
                        <ul class="pictures">
                            <g:each in="${pictures}" var="picture">
                                <li style="padding: 4px;">
                                    <div style="display:inline-block; vertical-align: top;">
                                        <a style="margin: 4px;" class="preview" href="${createLink(uri: picture.getFormatBy(FormatType.SMALL).url)}"><img src="${createLink(uri: picture.getFormatBy(FormatType.THUMBNAIL).url)}" width="48"></a>
                                    </div>
                                    <div style="display:inline-block; vertical-align: top; width: 240px">
                                        <input type="checkbox" id="picture${picture.id}" name="pictures" value="${picture.id}" checked="checked"/>
                                        <label for="picture${picture.id}"><b>${picture.originalFilename}</b></label><br/>
                                        Title:<br/>
                                        <input type="text" name="picture.${picture.id}.title">
                                    </div>
                                    <div style="display:inline-block; vertical-align: top;">
                                        <g:each in="${Person.listOrderByDisplayOrder()}" var="person">
                                            <input type="checkbox" name="picture.${picture.id}.people" value="${person.id}" id="p${picture.id}p${person.id}"> <label for="p${picture.id}p${person.id}">${person.shortName}</label><br/>
                                        </g:each>
                                    </div>
                                </li>
                            </g:each>
                        </ul>
                    </g:if><g:else>
                    <div style="padding: 10px;">
                        There are currently no unused pictures. You can <g:link controller="picture" action="upload">upload</g:link> some first.
                    </div>
                </g:else>
                </fieldset>
                <fieldset>
                    <legend>Tags</legend>
                    <ul class="tags">
                        <g:each in="${Tag.listOrderByName()}" var="tag">
                            <li><label>
                                <input type="checkbox" name="tags" value="${tag.id}"> ${tag.name}
                            </label></li>
                        </g:each>
                    </ul>
                    <div style="text-align: right; padding: 4px;">
                        <label for="name">New tag:</label> <input type="text" name="name" id="name" maxlength="80"> <button type="button" id="add-tag" class="button-add">add</button>
                    </div>
                </fieldset>
                <div class="buttons">
                    <button type="submit" class="button-create">Create</button>
                </div>
            </div>
        </g:form>
    </div>
    <script type="text/javascript">
        $(function() {
            $("#title, #publishDate_month, #publishDate_year").change(function() {
                //alert("New value");
                $.ajax({
                    url: "<g:createLink action="permalink" />",
                    data: $('.permalink-data input, select').serialize(),
                    success: function(data) {
                        $("#permalink").val(data);
                    }
                });
            });

            $("#add-tag").click(function() {
                if ($('#name').val()) {
                    $.ajax({
                        url: "<g:createLink action="ajaxCreate" controller="tag"/>",
                        data: $('#name').serialize(),
                        success: function(data) {
                            $('ul.tags').append('<li><input type="checkbox" name="tags" value="' + data.id + '" id="tag-' + data.id + '"> <label for="tag-' + data.id + '">' + data.name + '</label></li>');
                            $('#name').val('');
                        }
                    });
                }
            });
        });
    </script>
</body>
</html>