package com.ksinfo.pointgame.dao;

import com.ksinfo.pointgame.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDAO {
    int chkMember(@Param("memberId") String memberId, @Param("memberPassword") String memberPassword);
}