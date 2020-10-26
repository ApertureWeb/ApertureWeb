package com.aperture.community.message.component.nacos.api.naming;

import com.aperture.community.message.component.nacos.api.exception.NacosException;

import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * @author HALOXIAO
 * @since 2020-10-26 15:52
 **/
public class NamingMaintainFactory {

    /**
     * create a new maintain service.
     *
     * @param serverList server list
     * @return new maintain service
     * @throws NacosException nacos exception
     */
    public static NamingMaintainService createMaintainService(String serverList) throws NacosException {
        try {
            Class<?> driverImplClass = Class.forName("com.alibaba.nacos.client.naming.NacosNamingMaintainService");
            Constructor constructor = driverImplClass.getConstructor(String.class);
            NamingMaintainService vendorImpl = (NamingMaintainService) constructor.newInstance(serverList);
            return vendorImpl;
        } catch (Throwable e) {
            throw new NacosException(NacosException.CLIENT_INVALID_PARAM, e);
        }
    }

    /**
     * create a new maintain service.
     *
     * @param properties properties
     * @return new maintain service
     * @throws NacosException nacos exception
     */
    public static NamingMaintainService createMaintainService(Properties properties) throws NacosException {
        try {
            Class<?> driverImplClass = Class.forName("com.alibaba.nacos.client.naming.NacosNamingMaintainService");
            Constructor constructor = driverImplClass.getConstructor(Properties.class);
            NamingMaintainService vendorImpl = (NamingMaintainService) constructor.newInstance(properties);
            return vendorImpl;
        } catch (Throwable e) {
            throw new NacosException(NacosException.CLIENT_INVALID_PARAM, e);
        }
    }


}
