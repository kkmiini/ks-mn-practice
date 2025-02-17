package com.ksinfo.pointgame.dto;

public class MemberDTO {
    private String memberId;
    private String memberPassword;  // 컬럼명 맞춤

    public MemberDTO() {}

    public MemberDTO(String memberId, String memberPassword) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
    }

    public String getMemberId() { 
    	return memberId; 
    }
    
    public void setMemberId(String memberId) { 
    	this.memberId = memberId; 
    }
    public String getMemberPassword() { return memberPassword; }
    public void setMemberPassword(String memberPassword) { this.memberPassword = memberPassword; }
}
