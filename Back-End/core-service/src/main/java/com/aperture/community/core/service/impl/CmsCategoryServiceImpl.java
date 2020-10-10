package com.aperture.community.core.service.impl;

import com.aperture.community.core.common.map.CmsCategoryMap;
import com.aperture.community.core.common.map.cache.RedisCategoryMap;
import com.aperture.community.core.common.status.CategoryStatus;
import com.aperture.community.core.dao.CmsCategoryMapper;
import com.aperture.community.core.manager.PrimaryIdManager;
import com.aperture.community.core.module.CmsCategoryEntity;
import com.aperture.community.core.module.converter.CmsCategoryConverter;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.CmsCategoryParam;
import com.aperture.community.core.module.vo.ChildCategoryVO;
import com.aperture.community.core.module.vo.CmsCategoryVO;
import com.aperture.community.core.service.CmsCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CmsCategoryServiceImpl implements CmsCategoryService {

    private CmsCategoryMapper cmsCategoryMapper;
    private PrimaryIdManager primaryIdManager;


    @Autowired
    public CmsCategoryServiceImpl(CmsCategoryMapper cmsCategoryMapper,PrimaryIdManager primaryIdManager) {
        this.cmsCategoryMapper = cmsCategoryMapper;
        this.primaryIdManager = primaryIdManager;
    }


    @Override
    public MessageDto<Boolean> addCategory(CmsCategoryParam param) {
        Long id = primaryIdManager.getPrimaryId();
        param.setId(id);
        CmsCategoryEntity cmsCategoryEntity = CmsCategoryConverter.INSTANCE.toCmsCategoryEntity(param);
        cmsCategoryEntity.setCircleCount(0);
        if (!cmsCategoryMapper.save(cmsCategoryEntity)) {
            return new MessageDto<>("添加分类失败", false);
        }
        return new MessageDto<>("success", true);
    }


    @Override
    public MessageDto<Boolean> updateCategory(CmsCategoryParam param) {
        CmsCategoryEntity cmsCategoryEntity = CmsCategoryConverter.INSTANCE.toCmsCategoryEntity(param);
        if (!cmsCategoryMapper.updateById(cmsCategoryEntity)) {
            return new MessageDto<>("更新分类失败", false);
        }
        return new MessageDto<>("success", true);
    }



    @Cacheable(value = "CategoryCache", key = RedisCategoryMap.CATEGORY_CACHE)
    @Override
    public List<CmsCategoryVO> list() {
        List<CmsCategoryEntity> categoryEntities = cmsCategoryMapper.list(new QueryWrapper<CmsCategoryEntity>().select(
                CmsCategoryMap.ID.getValue(),
                CmsCategoryMap.CIRCLE_COUNT.getValue(),
                CmsCategoryMap.ICON.getValue(),
                CmsCategoryMap.LEVEL.getValue(),
                CmsCategoryMap.NAME.getValue(),
                CmsCategoryMap.PARENT_CID.getValue(),
                CmsCategoryMap.SHOW_STATUS.getValue(),
                CmsCategoryMap.SORT.getValue()
        ).eq(CmsCategoryMap.SHOW_STATUS.getValue(), CategoryStatus.DISPLAY.getValue()));
        categoryEntities.sort((o1, o2) -> {
            if (o1.getSort() > o2.getSort()) {
                return 1;
            } else {
                return 0;
            }
        });
        Queue<CmsCategoryEntity> categoryQueue = new LinkedList<>();
        LinkedHashMap<Long, CmsCategoryVO> map = new LinkedHashMap<>((int) (categoryEntities.size() / 0.75f));
        categoryEntities.forEach(p -> {
            if (p.getLevel().equals(CategoryStatus.FIRST_LEVEL.getValue())) {
                map.put(p.getId(), CmsCategoryConverter.INSTANCE.toCmsCategoryVO(p));
            } else if (p.getLevel().equals(CategoryStatus.SECOND_LEVEL.getValue())) {
                categoryQueue.add(p);
            }
        });
        categoryQueue.forEach(p -> {
            CmsCategoryVO categoryVO = map.get(p.getParentCid());
            List<ChildCategoryVO> childCategoryVOs = categoryVO.getChildCategoryVO();
            if (childCategoryVOs == null) {
                childCategoryVOs = new LinkedList<>();
            }
            childCategoryVOs.add(CmsCategoryConverter.INSTANCE.toChildCategoryVO(p));
        });
        return new ArrayList<>(map.values());
    }

    //    TODO:圈子外键
    @Override
    public MessageDto<Boolean> deleteCategory(List<Long> ids) {
        if (!cmsCategoryMapper.removeByIds(ids)) {
            return new MessageDto<>("删除分类失败", false);
        }
        return new MessageDto<>("success", true);
    }


}