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

import org.joda.time.DateTime
/* Created 06-Nov-2010 22:07:12 by mfloryan */

testDataConfig {
    sampleData {
        'mmsquare.umbra.Entry' {
            title = "Test entry"
	        permalink = "/test/test-entry"
	        publishDate = new DateTime().minusDays(2)
        }
	    'mmsquare.umbra.Picture' {
		    originalFilename = "IMG_1234.JPG"
	    }
    }
}