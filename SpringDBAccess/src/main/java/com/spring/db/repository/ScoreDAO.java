package com.spring.db.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.db.common.ScoreMapper;
import com.spring.db.model.ScoreVO;

@Repository
// 자동 bean 등록 annotation (@Repository)
public class ScoreDAO implements IScoreDAO {
	
	// 내부 (중첩) 클래스 
	class ScoreMapper implements RowMapper<ScoreVO>{

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
	
	// 학생들의 점수 정보를 저장할 리스트를 생성 (DB 대용)
//	private List<ScoreVO> scoreList = new ArrayList<>();
	
	// root-context.xml에서 jdbc설정 끝나고 진행해야함 
	// Spring-jdbc 방식의 처리 : JdbcTemplate 활용
	
	@Autowired 
	// container에 저장되어 있는 bean을 타입을 통해 자동으로 찾아줌
	private JdbcTemplate template;
	
	@Override
	public void insertScore(ScoreVO score) {
//		scoreList.add(score);
		// db에 넣어주는 작업을 현재에는 list로 작성하고 있으므로 간단하게 끄읏 
		// service에서 가져온 score를 받아서 값을 넣음
		
		String sql = "INSERT INTO scores VALUES(id_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		template.update(sql, 
						score.getStuName(),
						score.getKor(),
						score.getEng(),
						score.getMath(),
						score.getTotal(),
						score.getAverage());	
		// 전역 변수로 지정한 template을 활용하여 sql 작업 진행
		// JdbcTemplate이 Connection이나 부가적인 다른 것들은 자동으로 진행 됨
		// 엄청 간단하게 진행 가능 
	}

	@Override
	public List<ScoreVO> listScore() {
		String sql = "SELECT * FROM scores ORDER BY stu_id ASC";
		
		return template.query(sql, new ScoreMapper());
		// sql문과 생성한 ScoreMapper 객체를 생성 
		// ScoreMapper객체에는 select 결과를 score객체에 넣어주며 score객체를 리턴
		// jdbctemplate이 sql을 실행하고 조회 결과를 template가 가지고 있으며 
		// jdbctemplate이 mapRow메서드를 불러서 resultset을 전달
		// 이후 객체에 값을 넣어주고 jdbctemplate에 리턴하면 jdbctemplate은 리스트를 만들어 놓고 거기에 넣어줌
		
		// 즉 jsp에서 했던 select를 query함수를 통해서 실행 
		
		
//		return scoreList;
//		// service에게 return
//		// 지금은 DB 연결하지 않아서 간단
	}

	@Override
	public void deleteScore(int num) {
		
		String sql = "DELETE FROM scores WHERE stu_id = ?";
		template.update(sql, num);
		
//		scoreList.remove(num-1);
//		// 인덱스 값으로 삭제하기 때문에 -1해줌
	}

	@Override
	public ScoreVO selectScore(int num) {
		// 저장되어 있지 않은 정보를 조회할 수 있으므로 조건문 삽입
		
//		if (num <= scoreList.size()) {
//			return scoreList.get(num-1);
//		} else {
//			// 현재 데이터의 사이즈보다 크다는 것은 잘못된 번호를 입력한 것  
//			return null;
//		}
		
		String sql = "SELECT * FROM scores WHERE stu_id = ?";
		// queryForObject는 예외가 발생하면 에러가 터지므로 예외가 발생시 처리할 수 있도록 지정 
		try {
			ScoreVO score = template.queryForObject(sql, new ScoreMapper(), num);
			return score;
		} catch (Exception e) {
			return null;
			// null일 때 값을 보여주는 로직을 구현했음
		}
		
		
	}
}
