package com.aperture.community.message.manager;

import com.aperture.community.message.dao.MsEventRemindMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author HALOXIAO
 * @since 2020-10-11 18:59
 **/
public class EventRemindManager {

    private MsEventRemindMapper eventRemindMapper;

    @Autowired
    EventRemindManager() {

    }


}
