package com.spring.basic.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.basic.model.ScoreVO;

@Repository
// 자동 bean 등록 annotation (@Repository)

public class ScoreDAO implements IScoreDAO {

	// 학생들의 점수 정보를 저장할 리스트를 생성 (DB 대용)
	private List<ScoreVO> scoreList = new ArrayList<>();
	
	
	@Override
	public void insertScore(ScoreVO score) {
		scoreList.add(score);
		// db에 넣어주는 작업을 현재에는 list로 작성하고 있으므로 간단하게 끄읏 
		// service에서 가져온 score를 받아서 값을 넣음
		
	}

	@Override
	public List<ScoreVO> listScore() {
		
		return scoreList;
		// service에게 return
		// 지금은 DB 연결하지 않아서 간단
	}

	@Override
	public void deleteScore(int num) {
		scoreList.remove(num-1);
		// 인덱스 값으로 삭제하기 때문에 -1해줌
	}

	@Override
	public ScoreVO selectScore(int num) {
		// 저장되어 있지 않은 정보를 조회할 수 있으므로 조건문 삽입
		
		if (num <= scoreList.size()) {
			return scoreList.get(num-1);
		} else {
			// 현재 데이터의 사이즈보다 크다는 것은 잘못된 번호를 입력한 것  
			return null;
		}
	}

}
