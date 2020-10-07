package com.aperture.community.core.service;


import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.CmsEventParam;
import com.aperture.community.core.module.vo.EventVO;

/**
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-05 20:51:34
 */
public interface CmsEventService {

    void like(Long id);


    void feed(Long id);

    void store(Long id);

    MessageDto<EventVO> getEventVO(CmsEventParam eventParam);


}

