package com.aperture.community.core.service.impl;


import com.aperture.community.core.common.map.CmsCategoryMap;
import com.aperture.community.core.common.status.CategoryStatus;
import com.aperture.community.core.dao.CmsCategoryMapper;
import com.aperture.community.core.module.CmsCategoryEntity;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.CmsCategoryParam;
import com.aperture.community.core.module.vo.CmsCategoryVO;
import com.aperture.community.core.service.CmsCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    /**
     * 较少发生变更，可以直接放进缓存层并且较长expire time；
     * 需要进行延迟双删
     */
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
        HashMap<Long, CmsCategoryVO> map = new HashMap<>((int) (categoryEntities.size() / 0.75f));
        categoryEntities.forEach(p -> {
            if (p.getLevel().equals(CategoryStatus.FIRST_LEVEL.getValue())) {
//                map.put(p.getId(),)
            }
        });

        return null;
    }
}