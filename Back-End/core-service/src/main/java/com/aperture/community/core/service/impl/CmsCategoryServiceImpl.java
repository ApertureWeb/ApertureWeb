package com.aperture.community.core.service.impl;


import com.aperture.community.core.common.map.CmsCategoryMap;
import com.aperture.community.core.dao.CmsCategoryMapper;
import com.aperture.community.core.module.CmsCategoryEntity;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.CmsCategoryParam;
import com.aperture.community.core.module.vo.CmsCategoryVO;
import com.aperture.community.core.service.CmsCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class CmsCategoryServiceImpl implements CmsCategoryService {

    private CmsCategoryMapper cmsCategoryMapper;


    @Autowired
    public CmsCategoryServiceImpl(CmsCategoryMapper cmsCategoryMapper) {
        this.cmsCategoryMapper = cmsCategoryMapper;
    }

    @Override
    public MessageDto<Boolean> addCategory(CmsCategoryParam param) {
        return null;
    }

    @Override
    public MessageDto<Boolean> updateCategory(CmsCategoryParam param) {
        return null;
    }

    @Override
    public List<CmsCategoryVO> listPage() {
        List<CmsCategoryEntity> categoryEntities = cmsCategoryMapper.list(new QueryWrapper<CmsCategoryEntity>().select(
                CmsCategoryMap.ID.getValue(),
                CmsCategoryMap.CIRCLE_COUNT.getValue(),
                CmsCategoryMap.ICON.getValue(),
                CmsCategoryMap.LEVEL.getValue(),
                CmsCategoryMap.NAME.getValue(),
                CmsCategoryMap.PARENT_CID.getValue(),
                CmsCategoryMap.SHOW_STATUS.getValue(),
                CmsCategoryMap.SORT.getValue()
        ));
        categoryEntities.sort((o1, o2) -> {
            if (o1.getSort() > o2.getSort()) {
                return 1;
            } else {
                return 0;
            }
        });
        List<CmsCategoryVO> list = new LinkedList<>();
        categoryEntities.forEach(p -> {

        });

        return null;
    }
}