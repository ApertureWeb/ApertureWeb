package com.aperture.community.message.component.nacos.api.config.listener;

import java.util.concurrent.Executor;

/**
 * Listener for watch config.
 *
 * @author Nacos
 */
public interface Listener {

    /**
     * Get executor for execute this receive.
     *
     * @return Executor
     */
    Executor getExecutor();

    /**
     * Receive config info.
     *
     * @param configInfo config info
     */
    void receiveConfigInfo(final String configInfo);
}
