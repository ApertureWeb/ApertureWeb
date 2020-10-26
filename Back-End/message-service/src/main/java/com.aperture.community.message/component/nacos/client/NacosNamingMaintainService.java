package com.aperture.community.message.component.nacos.client;

import com.aperture.community.message.component.nacos.api.exception.NacosException;
import com.aperture.community.message.component.nacos.api.naming.NamingMaintainService;
import com.aperture.community.message.component.nacos.api.naming.pojo.Instance;
import com.aperture.community.message.component.nacos.api.naming.pojo.Service;
import com.aperture.community.message.component.nacos.api.selector.AbstractSelector;

import java.util.Map;

/**
 * @author HALOXIAO
 * @since 2020-10-25 19:36
 **/
public class NacosNamingMaintainService implements NamingMaintainService {
    @Override
    public void updateInstance(String serviceName, Instance instance) throws NacosException {

    }

    @Override
    public void updateInstance(String serviceName, String groupName, Instance instance) throws NacosException {

    }

    @Override
    public Service queryService(String serviceName) throws NacosException {
        return null;
    }

    @Override
    public Service queryService(String serviceName, String groupName) throws NacosException {
        return null;
    }

    @Override
    public void createService(String serviceName) throws NacosException {

    }

    @Override
    public void createService(String serviceName, String groupName) throws NacosException {

    }

    @Override
    public void createService(String serviceName, String groupName, float protectThreshold) throws NacosException {

    }

    @Override
    public void createService(String serviceName, String groupName, float protectThreshold, String expression) throws NacosException {

    }

    @Override
    public void createService(Service service, AbstractSelector selector) throws NacosException {

    }

    @Override
    public boolean deleteService(String serviceName) throws NacosException {
        return false;
    }

    @Override
    public boolean deleteService(String serviceName, String groupName) throws NacosException {
        return false;
    }

    @Override
    public void updateService(String serviceName, String groupName, float protectThreshold) throws NacosException {

    }

    @Override
    public void updateService(String serviceName, String groupName, float protectThreshold, Map<String, String> metadata) throws NacosException {

    }

    @Override
    public void updateService(Service service, AbstractSelector selector) throws NacosException {

    }
}
