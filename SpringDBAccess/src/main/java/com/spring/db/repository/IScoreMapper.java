package com.spring.db.repository;

import java.util.List;

import com.spring.db.model.ScoreVO;

public interface IScoreMapper {

	// 점수 등록 
	void insertScore(ScoreVO score);
	
	// 점수 전체 조회 
	List<ScoreVO> listScore();
	
	// 점수 삭제 
	void deleteScore(int num);
	
	// 점수 개별 조회 
	ScoreVO selectScore(int num);
	
	// IScoreService의 내용과 같도록 작성 
	// 왜냐면 Service가 DAO를 호출하기 때문에 Service가 DB와 연동작업을 진행하기 위해 
	// 작성한 메서드는 DAO와 같게 작성해야함 왜냐면 DAO가 DB와 접근하기 때문
}
