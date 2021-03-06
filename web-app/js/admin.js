/*
 * Copyright (c) 2010 Marcin Floryan. http://www.mmsquare.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

$(document).ready(function() {
    $('a.button').button();
    $('a.button-edit').button({icons: {primary: "ui-icon-pencil"}});
    $('a.button-delete').button({icons: {primary: "ui-icon-trash"}}).bind('click', handleDeleteClick);
    $('.button-create').button({icons: {primary: "ui-icon-circle-plus"}});
    $('.button-add').button({icons: {primary: "ui-icon-plus"}});
    $('.button-auth').button({icons: {primary: "ui-icon-locked"}});

    $('a.preview').fancybox({
        type:'image'
    });

    
});

function handleDeleteClick() {
    return confirm("Are you sure?");
}
