package com.aperture.community.message.component.nacos.api;



import com.aperture.community.message.component.nacos.api.config.ConfigFactory;
import com.aperture.community.message.component.nacos.api.config.ConfigService;
import com.aperture.community.message.component.nacos.api.exception.NacosException;
import com.aperture.community.message.component.nacos.api.naming.NamingFactory;
import com.aperture.community.message.component.nacos.api.naming.NamingMaintainFactory;
import com.aperture.community.message.component.nacos.api.naming.NamingMaintainService;
import com.aperture.community.message.component.nacos.api.naming.NamingService;

import java.util.Properties;

/**
 * Nacos Factory.
 *
 * @author Nacos
 */
public class NacosFactory {

  /**
   * Create config service.
   *
   * @param properties init param
   * @return config
   * @throws NacosException Exception
   */
  public static ConfigService createConfigService(Properties properties) throws NacosException {
    return ConfigFactory.createConfigService(properties);
  }

  /**
   * Create config service.
   *
   * @param serverAddr server list
   * @return config
   * @throws NacosException Exception
   */
  public static ConfigService createConfigService(String serverAddr) throws NacosException {
    return ConfigFactory.createConfigService(serverAddr);
  }

  /**
   * Create naming service.
   *
   * @param serverAddr server list
   * @return Naming
   * @throws NacosException Exception
   */
  public static NamingService createNamingService(String serverAddr) throws NacosException {
    return NamingFactory.createNamingService(serverAddr);
  }

  /**
   * Create naming service.
   *
   * @param properties init param
   * @return Naming
   * @throws NacosException Exception
   */
  public static NamingService createNamingService(Properties properties) throws NacosException {
    return NamingFactory.createNamingService(properties);
  }

  /**
   * Create maintain service.
   *
   * @param serverAddr server address
   * @return NamingMaintainService
   * @throws NacosException Exception
   */
  public static NamingMaintainService createMaintainService(String serverAddr) throws NacosException {
    return NamingMaintainFactory.createMaintainService(serverAddr);
  }

  /**
   * Create maintain service.
   *
   * @param properties server address
   * @return NamingMaintainService
   * @throws NacosException Exception
   */
  public static NamingMaintainService createMaintainService(Properties properties) throws NacosException {
    return NamingMaintainFactory.createMaintainService(properties);
  }
}
