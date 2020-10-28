package com.aperture.community.message.component.nacos.client.naming.utils;

/**
 * @author HALOXIAO
 * @since 2020-10-28 16:05
 **/
public class UtilAndComs {


//    public static final String VERSION = "Nacos-Java-Client:v" + VersionUtils.version;
public static final String VERSION = "Nacos-Java-Client:v";
    public static String webContext = "/nacos";

    public static String nacosUrlBase = webContext + "/v1/ns";

    public static String nacosUrlInstance = nacosUrlBase + "/instance";

    public static String nacosUrlService = nacosUrlBase + "/service";

    public static final String ENCODING = "UTF-8";

    public static final String ENV_LIST_KEY = "envList";

    public static final String ALL_IPS = "000--00-ALL_IPS--00--000";

    public static final String FAILOVER_SWITCH = "00-00---000-VIPSRV_FAILOVER_SWITCH-000---00-00";

    public static final String DEFAULT_NAMESPACE_ID = "public";

    public static final int REQUEST_DOMAIN_RETRY_COUNT = 3;

    public static final String NACOS_NAMING_LOG_NAME = "com.alibaba.nacos.naming.log.filename";

    public static final String NACOS_NAMING_LOG_LEVEL = "com.alibaba.nacos.naming.log.level";

    public static final String SERVER_ADDR_IP_SPLITER = ":";

    public static final int DEFAULT_CLIENT_BEAT_THREAD_COUNT =
            Runtime.getRuntime().availableProcessors() > 1 ? Runtime.getRuntime().availableProcessors() / 2 : 1;

    public static final int DEFAULT_POLLING_THREAD_COUNT =
            Runtime.getRuntime().availableProcessors() > 1 ? Runtime.getRuntime().availableProcessors() / 2 : 1;

    public static final String HTTP = "http://";

    public static final String HTTPS = "https://";

}
