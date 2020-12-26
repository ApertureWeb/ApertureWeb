package com.aperture.community.member.manager;

import com.aperture.community.member.dao.GradeMapper;
import com.aperture.community.member.dao.LoginLogMapper;
import com.aperture.community.member.dao.MemberCircleRelaMapper;
import com.aperture.community.member.dao.MemberMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-17 19:06
 * @Description:
 */
@Getter
@Service
public class LoginLogManager {

    private LoginLogMapper loginLogMapper;
    private MemberManager memberManager;

    @Autowired
    public LoginLogManager(LoginLogMapper loginLogMapper,
                        MemberManager memberManager) {
        this.loginLogMapper = loginLogMapper;
        this.memberManager = memberManager;
    }

}