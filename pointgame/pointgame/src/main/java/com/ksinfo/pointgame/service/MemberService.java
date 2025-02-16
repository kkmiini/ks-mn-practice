package com.ksinfo.pointgame.service;

import com.ksinfo.pointgame.dao.MemberDAO;
import com.ksinfo.pointgame.dto.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberDAO memberDAO;

    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public boolean authenticate(String memberId, String password) {
        try {
            MemberDTO user = memberDAO.findByMemberId(memberId);
            return user.getMemberPassword().equals(password);  // 수정된 필드명 반영
        } catch (Exception e) {
            return false; // DB에서 사용자를 찾지 못한 경우
        }
    }
}
