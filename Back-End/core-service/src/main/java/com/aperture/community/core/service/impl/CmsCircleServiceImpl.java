package com.aperture.community.core.service.impl;

import com.aperture.community.core.common.map.CmsCategoryMap;
import com.aperture.community.core.common.status.CircleStatus;
import com.aperture.community.core.dao.CmsCategoryMapper;
import com.aperture.community.core.dao.CmsCircleMapper;
import com.aperture.community.core.manager.PrimaryIdManager;
import com.aperture.community.core.module.CmsCategoryEntity;
import com.aperture.community.core.module.CmsCircleEntity;
import com.aperture.community.core.module.converter.CmsCircleConverter;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.CmsCircleParam;
import com.aperture.community.core.service.CmsCircleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;

@Service
public class CmsCircleServiceImpl implements CmsCircleService {

    private CmsCircleMapper cmsCircleMapper;
    private PrimaryIdManager primaryIdManager;
    private CmsCategoryMapper categoryMapper;


    @Autowired
    public CmsCircleServiceImpl(CmsCircleMapper cmsCircleMapper) {
        this.cmsCircleMapper = cmsCircleMapper;
    }

    public MessageDto addCircle(CmsCircleParam circleParam) {
        Long id = primaryIdManager.getPrimaryId();
        CmsCircleEntity circleEntity = CmsCircleConverter.INSTANCE.toCircleEntity(circleParam);
        CmsCategoryEntity cmsCategoryEntity = categoryMapper.getOne(new QueryWrapper<CmsCategoryEntity>().select("1")
                .eq(CmsCategoryMap.ID.getValue(), circleEntity.getCategoryId())
                .eq(CmsCategoryMap.LEVEL.getValue(), 2)
        );
        if (cmsCategoryEntity == null) {
            return new MessageDto("二级目录不存在", false);
        }
        circleEntity.setHot(0);
        circleEntity.setArticleNum(0);
        circleEntity.setVideoNum(0);
        circleEntity.setMenberNum(0);
        circleEntity.setStatus(CircleStatus.REVIEW.getValue());
        circleEntity.setGmtCreate(Date.valueOf(LocalDateTime.now().toLocalDate()));
        circleEntity.setId(id);
//   TODO    发送到审核模块
        if (!cmsCircleMapper.save(circleEntity)) {
            return new MessageDto("添加圈子失败", false);
        }
//       TODO 设置URL
        return new MessageDto("success", true);
    }

    @Override
    public MessageDto addCricle(CmsCircleParam circleParam) {
        return null;
    }


}