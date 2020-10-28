package com.aperture.community.message.component.nacos.client.naming.backups;

import com.aperture.community.message.component.nacos.client.naming.core.HostReactor;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author HALOXIAO
 * @since 2020-10-28 15:58
 **/
public class FailoverReactor implements Closeable {

    private final String failoverDir;

    private final HostReactor hostReactor;

    public FailoverReactor(HostReactor hostReactor, String cacheDir) {
        this.hostReactor = hostReactor;
        this.failoverDir = cacheDir + "/failover";

    }


    @Override
    public void close() throws IOException {

    }
}
