package com.aperture.community.member.service;

import com.aperture.community.member.model.MemberCircleRelaEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.vo.MemberCircleRelaVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;

import java.util.List;
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

    MessageDto<MemberCircleRelaEntity> getMemberCircleInfo(Long memberId);

    MessageDto<MemberCircleRelaVo> getMemberCircleRela(Long memberId);
}

