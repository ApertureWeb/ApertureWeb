/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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

package com.aperture.community.message.component.nacos.common.http.param;

/**
 * Http Media type.
 *
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 */
public  enum  MediaType {

    
    APPLICATION_ATOM_XML ( "application/atom+xml"),
    
    APPLICATION_FORM_URLENCODED ( "application/x-www-form-urlencoded"),
    
    APPLICATION_OCTET_STREAM ( "application/octet-stream"),
    
    APPLICATION_SVG_XML ( "application/svg+xml"),
    
    APPLICATION_XHTML_XML ( "application/xhtml+xml"),
    
    APPLICATION_XML ( "application/xml"),
    
    APPLICATION_JSON ( "application/json"),
    
    MULTIPART_FORM_DATA ( "multipart/form-data"),
    
    TEXT_HTML ( "text/html"),
    
    TEXT_PLAIN ( "text/plain"),
    ;
    String value;
    MediaType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
