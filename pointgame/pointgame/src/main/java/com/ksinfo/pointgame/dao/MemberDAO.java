package com.ksinfo.pointgame.dao;

import com.ksinfo.pointgame.dto.MemberDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
    private final JdbcTemplate jdbcTemplate;

    public MemberDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 회원 조회 (memberId로 찾기) - 테이블명 & 컬럼명 변경
    public MemberDTO findByMemberId(String memberId) {
        String sql = "SELECT member_id, member_password FROM memberinfo WHERE member_id = ?";
        RowMapper<MemberDTO> rowMapper = (rs, rowNum) ->
            new MemberDTO(rs.getString("member_id"), rs.getString("member_password"));

        return jdbcTemplate.queryForObject(sql, rowMapper, memberId);
    }
}
