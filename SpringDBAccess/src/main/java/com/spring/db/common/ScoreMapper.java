package com.spring.db.common;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.db.model.ScoreVO;

// JDBCTemplate에서 SELECT 쿼리를 위한 ResultSet 사용을 편하게 하기 위한 클래스 생성 
// RowMapper 인터페이스 구현 
public class ScoreMapper implements RowMapper<ScoreVO>{

	@Override
	public ScoreVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("mapRow 메서드 발동");
		System.out.println("rowNum: " + rowNum);
		
		ScoreVO score = new ScoreVO();
		// 조회된 결과를 ScoreVO 객체에 넣어줌 
		// ScoreVO 객체의 setter 메서드와 db의 컬럼 명을 활용 
		score.setStuId(rs.getInt("stu_id"));
		score.setStuName(rs.getString("stu_name"));
		score.setKor(rs.getInt("kor"));
		score.setEng(rs.getInt("eng"));
		score.setMath(rs.getInt("math"));
		score.setTotal(rs.getInt("total"));
		score.setAverage(rs.getDouble("average"));
		return score;
	}
}
