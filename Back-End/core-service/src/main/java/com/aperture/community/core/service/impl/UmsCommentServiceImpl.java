package com.aperture.community.core.service.impl;

import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsCommentParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.UmsCommentVO;
import com.aperture.community.core.service.IUmsCommentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论功能 服务实现类
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-27
 */
@Service
public class UmsCommentServiceImpl  implements IUmsCommentService {
    @Override
    public UmsCommentVO select(UmsCommentParam UmsCommentParam) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public PageVO<UmsCommentVO> listPage(PageParam pageParam) {
        return null;
    }

    @Override
    public boolean update(UmsCommentParam UmsCommentParam) {
        return false;
    }

    @Override
    public boolean save(UmsCommentParam UmsCommentParam) {
        return false;
    }
}
