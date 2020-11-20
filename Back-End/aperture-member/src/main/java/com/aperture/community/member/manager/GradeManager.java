package com.aperture.community.member.manager;

import com.aperture.community.member.common.map.GradeMap;
import com.aperture.community.member.dao.GradeDao;
import com.aperture.community.member.dao.GradeMapper;
import com.aperture.community.member.dao.MemberCircleRelaMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: JayV
 * @Date: 2020-11-14 21:51
 * @Description:
 */
@Service
@Getter
public class GradeManager {

    private GradeMapper gradeMapper;
    private MemberCircleRelaMapper memberCircleRelaMapper;

    @Autowired
    public GradeManager(GradeMapper gradeMapper, MemberCircleRelaMapper memberCircleRelaMapper) {
        this.gradeMapper = gradeMapper;
        this.memberCircleRelaMapper = memberCircleRelaMapper;
    }

}