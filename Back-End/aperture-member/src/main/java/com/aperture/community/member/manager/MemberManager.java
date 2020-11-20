package com.aperture.community.member.manager;

import com.aperture.community.member.dao.MemberDao;
import com.aperture.community.member.dao.MemberMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: JayV
 * @Date: 2020-11-14 21:53
 * @Description:
 */
@Service
@Getter
public class MemberManager {

    private MemberMapper memberMapper;

    @Autowired
    public MemberManager(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

}