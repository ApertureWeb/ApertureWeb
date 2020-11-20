package com.aperture.community.member.manager;

import com.aperture.community.member.dao.WatchHistoryMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-17 20:57
 * @Description:
 */
@Service
@Getter
public class WatchHistoryManager {

    private WatchHistoryMapper watchHistoryMapper;

    @Autowired
    public WatchHistoryManager(WatchHistoryMapper watchHistoryMapper) {
        this.watchHistoryMapper = watchHistoryMapper;
    }

}