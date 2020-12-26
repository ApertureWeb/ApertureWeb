package com.aperture.community.member.manager.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.esotericsoftware.minlog.Log;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-15 19:27
 * @Description:
 */
public class GlobalBlockHandler {

    public static void handlerException(BlockException e) {
        Log.info("BlockException：{}", e.getMessage());
    }

}