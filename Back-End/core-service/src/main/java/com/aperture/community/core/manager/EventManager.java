package com.aperture.community.core.manager;

import com.aperture.community.core.common.map.CmsEventMap;
import com.aperture.community.core.common.status.EventStatus;
import com.aperture.community.core.dao.CmsArticleMapper;
import com.aperture.community.core.dao.CmsEventMapper;
import com.aperture.community.core.dao.CmsVideoMapper;
import com.aperture.community.core.module.CmsEventEntity;
import com.aperture.community.core.module.converter.CmsEventConverter;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.vo.EventVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EventManager {

    private CmsEventMapper cmsEventMapper;
    private CmsArticleMapper cmsArticleMapper;
    private CmsVideoMapper cmsVideoMapper;

    @Autowired
    public EventManager(CmsEventMapper cmsEventMapper) {
        this.cmsEventMapper = cmsEventMapper;
    }


    public MessageDto<EventVO> getEventVO(Long id, EventStatus status) {
        CmsEventEntity eventEntity = cmsEventMapper.getOne(new QueryWrapper<CmsEventEntity>().select(
                CmsEventMap.LIKE.getValue(),
                CmsEventMap.DONUT.getValue(),
                CmsEventMap.STORE.getValue()).
                eq(CmsEventMap.CONTENT_ID.getValue(), id));
        if (eventEntity == null) {
            if (status.equals(EventStatus.ARTICLE)) {
                return new MessageDto<>("文章不存在", null, false);
            } else if (status.equals(EventStatus.VIDEO)) {
                return new MessageDto<>("视频不存在", null, false);
            }
        }
        EventVO result = CmsEventConverter.INSTANCE.toCmsEventEntity(eventEntity);
        return new MessageDto<>("success", result, true);
    }

    public MessageDto<Boolean> deleteEvent(Long id, EventStatus status) {
        if (!cmsEventMapper.removeById(id)) {
            return new MessageDto<>("", null, false);
        }
        //TODO do sth
        return null;
    }

    //TODO ids
    public MessageDto<Map<Long, EventVO>> getEventVOMap(List<Long> ids, EventStatus status) {
        Map<Long, EventVO> result = cmsEventMapper.list(new QueryWrapper<CmsEventEntity>().select(
                CmsEventMap.CONTENT_ID.getValue(),
                CmsEventMap.LIKE.getValue(),
                CmsEventMap.STORE.getValue(),
                CmsEventMap.DONUT.getValue()
        ).eq(CmsEventMap.TYPE.getValue(), status.getValue())).stream().collect(Collectors.toMap(CmsEventEntity::getContentId, CmsEventConverter.INSTANCE::toCmsEventEntity));
        return new MessageDto<>("success", result, true);
    }


}
