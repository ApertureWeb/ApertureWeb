package com.aperture.community.member.manager;

import com.aperture.community.member.dao.MemberCircleRelaDao;
import com.aperture.community.member.dao.MemberCircleRelaMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: JayV
 * @Date: 2020-11-14 21:51
 * @Description:
 */
@Service
@Getter
public class MemberCircleManager {

    private MemberCircleRelaMapper memberCircleRelaMapper;
    private MemberManager memberManager;

    @Autowired
    public MemberCircleManager(MemberCircleRelaMapper memberCircleRelaMapper,
                               MemberManager memberManager) {
        this.memberCircleRelaMapper = memberCircleRelaMapper;
        this.memberManager = memberManager;

    }

}