/*
 * Copyright (c) 2010 Marcin Floryan. http://www.mmsquare.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mmsquare.umbra

class Format {

  int width
  int height
  int size
  String path
  FormatType type = FormatType.ORIGINAL

    static constraints = {
      width(notEqual:0)
      height(notEqual:0)
      size(nullable:true)
      path(blank:false, unique:true)
    }

  void setType(FormatType type) {
    this.type = type
    if (type.formatTypeWidth && !width) width = type.formatTypeWidth
  }

  FormatType getType() {
    this.type    
  }
}

enum FormatType {
  ORIGINAL, LARGE(1200), SMALL(640), THUMBNAIL(100)
  public final int formatTypeWidth
  FormatType(int width) {
    this.formatTypeWidth = width
  }
  FormatType() {

  }
}