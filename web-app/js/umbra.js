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
    $('a.fancyboxImage').fancybox({
        'type':'image',
        'onComplete': function() {
            if (typeof _gaq != 'undefined') {
                var src = $('#fancybox-content img').attr('src');
                src = src.substring(src.indexOf('picture'), src.lastIndexOf('/'));
                _gaq.push(['_trackEvent', 'Pictures', 'Fancybox', src]);
            }
        }
    });

    $('.download a').click(function() {
        if (typeof _gaq != 'undefined') {
            var href = $(this).attr('href');
            _gaq.push(['_trackEvent', 'Pictures', 'Download',href.substring(1,href.indexOf('original')-1)]);
        }
        return true;
    });
});