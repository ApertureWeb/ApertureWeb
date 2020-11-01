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

package com.aperture.community.message.component.nacos.client.naming.net;


import com.aperture.community.message.component.nacos.api.exception.NacosException;
import com.aperture.community.message.component.nacos.common.lifecycle.Closeable;
import org.slf4j.Logger;


/**
 * http Manager.
 *
 * @author mai.jh
 */
public class NamingHttpClientManager {

    //TODO change it
    private static final int READ_TIME_OUT_MILLIS = Integer
            .getInteger("com.alibaba.nacos.client.naming.rtimeout", 50000);
    
    private static final int CON_TIME_OUT_MILLIS = Integer.getInteger("com.alibaba.nacos.client.naming.ctimeout", 3000);
    
    private static final boolean ENABLE_HTTPS = Boolean.getBoolean("com.alibaba.nacos.client.naming.tls.enable");
    
    private static final int MAX_REDIRECTS = 5;
    

    private static class NamingHttpClientManagerInstance {
        
        private static final NamingHttpClientManager INSTANCE = new NamingHttpClientManager();
    }
    
    public static NamingHttpClientManager getInstance() {
        return NamingHttpClientManagerInstance.INSTANCE;
    }
    
    public String getPrefix() {
        if (ENABLE_HTTPS) {
            return "https://";
        }
        return "http://";
    }



}
