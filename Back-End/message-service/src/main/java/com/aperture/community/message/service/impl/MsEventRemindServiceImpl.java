package com.aperture.community.message.service.impl;

import com.aperture.community.message.dao.MsEventRemindMapper;
import com.aperture.community.message.service.MsEventRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsEventRemindServiceImpl implements MsEventRemindService {

    private MsEventRemindMapper msEventRemindMapper;

    @Autowired
    public MsEventRemindServiceImpl(MsEventRemindMapper msEventRemindMapper) {
        this.msEventRemindMapper = msEventRemindMapper;
    }


}