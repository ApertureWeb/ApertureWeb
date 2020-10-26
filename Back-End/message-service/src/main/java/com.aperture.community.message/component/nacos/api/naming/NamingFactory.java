package com.aperture.community.message.component.nacos.api.naming;

import com.aperture.community.message.component.nacos.api.NamingService;
import com.aperture.community.message.component.nacos.api.exception.NacosException;

import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * @author HALOXIAO
 * @since 2020-10-26 15:51
 **/
public class NamingFactory {

    /**
     * Create a new naming service.
     *
     * @param serverList server list
     * @return new naming service
     * @throws NacosException nacos exception
     */
    public static NamingService createNamingService(String serverList) throws NacosException {
        try {
            Class<?> driverImplClass = Class.forName("com.alibaba.nacos.client.naming.NacosNamingService");
            Constructor constructor = driverImplClass.getConstructor(String.class);
            NamingService vendorImpl = (NamingService) constructor.newInstance(serverList);
            return vendorImpl;
        } catch (Throwable e) {
            throw new NacosException(NacosException.CLIENT_INVALID_PARAM, e);
        }
    }

    /**
     * Create a new naming service.
     *
     * @param properties naming service properties
     * @return new naming service
     * @throws NacosException nacos exception
     */
    public static NamingService createNamingService(Properties properties) throws NacosException {
        try {
            Class<?> driverImplClass = Class.forName("com.alibaba.nacos.client.naming.NacosNamingService");
            Constructor constructor = driverImplClass.getConstructor(Properties.class);
            NamingService vendorImpl = (NamingService) constructor.newInstance(properties);
            return vendorImpl;
        } catch (Throwable e) {
            throw new NacosException(NacosException.CLIENT_INVALID_PARAM, e);
        }
    }


}
