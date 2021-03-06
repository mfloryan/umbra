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

package mmsquare.umbra

/* Created 16-Nov-2010 09:11:17 by mfloryan */

class EntryListCommand {

	static final int ENTRIES_PER_PAGE = 3
	int page = 1
	String person

	int getOffset() {
		(page - 1) * ENTRIES_PER_PAGE
	}

	int getTotalPages(long itemCount) {
		if (!itemCount)
			1
		else
			Math.ceil(itemCount / ENTRIES_PER_PAGE)
	}
}