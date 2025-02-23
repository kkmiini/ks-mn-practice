package com.ksinfo.pointgame.service;

import com.ksinfo.pointgame.dao.MemberDAO;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberDAO memberDAO;

    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public boolean login(String memberId, String password) {
        int count = memberDAO.chkMember(memberId, password);
        return count > 0; // 존재하면 true, 없으면 false
    }
}