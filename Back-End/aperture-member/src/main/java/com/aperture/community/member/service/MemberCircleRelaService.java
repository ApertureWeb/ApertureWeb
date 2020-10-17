package com.aperture.community.member.service;

import com.aperture.community.member.vo.MemberCircleVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;
import com.aperture.community.member.entity.MemberCircleRelaEntity;

import java.util.Map;

/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-11 19:26:10
 */
public interface MemberCircleRelaService extends IService<MemberCircleRelaEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void createCircle(MemberCircleVo memberCircleVo);

    void joinCircle(MemberCircleRelaEntity memberCircleRela);

    void removeCircle(Long circleId);

    void exitCircle(Long memberId, Long circleId);

    void updatePositioon(MemberCircleRelaEntity memberCircleRela);
}

