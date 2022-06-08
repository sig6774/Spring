package com.spring.basic.service;

import java.util.List;

import com.spring.basic.model.ScoreVO;

public interface IScoreService {

	// 점수 등록 
	void insertScore(ScoreVO score);
	
	// 점수 전체 조회 
	List<ScoreVO> listScore();
	
	// 점수 삭제 
	void deleteScore(int num);
	
	// 점수 개별 조회 
	ScoreVO selectScore(int num);
	
}
