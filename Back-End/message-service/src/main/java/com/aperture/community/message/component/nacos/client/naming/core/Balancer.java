package com.aperture.community.message.component.nacos.client.naming.core;

import com.aperture.community.message.component.nacos.api.naming.pojo.Instance;
import com.aperture.community.message.component.nacos.api.naming.pojo.ServiceInfo;
import com.aperture.community.message.component.nacos.client.naming.utils.Chooser;
import com.aperture.community.message.component.nacos.client.naming.utils.CollectionUtils;
import com.aperture.community.message.component.nacos.client.naming.utils.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HALOXIAO
 * @since 2020-10-27 16:37
 **/
public class Balancer {
    private static final Logger logger = LoggerFactory.getLogger(Balancer.class);


    public static class RandomByWeight {

        /**
         * Select all instance.
         *
         * @param serviceInfo service information
         * @return all instance of services
         */
        public static List<Instance> selectAll(ServiceInfo serviceInfo) {
            List<Instance> hosts = serviceInfo.getHosts();

            if (CollectionUtils.isEmpty(hosts)) {
                throw new IllegalStateException("no host to srv for serviceInfo: " + serviceInfo.getName());
            }

            return hosts;
        }

        /**
         * Random select one instance from service.
         *
         * @param dom service
         * @return random instance
         */
        public static Instance selectHost(ServiceInfo dom) {

            List<Instance> hosts = selectAll(dom);

            if (CollectionUtils.isEmpty(hosts)) {
                throw new IllegalStateException("no host to srv for service: " + dom.getName());
            }

            return getHostByRandomWeight(hosts);
        }
    }

    /**
     * Return one host from the host list by random-weight.
     *
     * @param hosts The list of the host.
     * @return The random-weight result of the host
     */
    protected static Instance getHostByRandomWeight(List<Instance> hosts) {
        logger.debug("entry randomWithWeight");
        if (hosts == null || hosts.size() == 0) {
            logger.debug("hosts == null || hosts.size() == 0");
            return null;
        }
        logger.debug("new Chooser");
        List<Pair<Instance>> hostsWithWeight = new ArrayList<Pair<Instance>>();
        for (Instance host : hosts) {
            if (host.isHealthy()) {
                hostsWithWeight.add(new Pair<Instance>(host, host.getWeight()));
            }
        }
        logger.debug("for (Host host : hosts)");
        Chooser<String, Instance> vipChooser = new Chooser<String, Instance>("www.taobao.com");
        vipChooser.refresh(hostsWithWeight);
        logger.debug("vipChooser.refresh");
        return vipChooser.randomWithWeight();
    }
}
