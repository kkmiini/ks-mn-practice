package com.ksinfo.pointgame.dto;

import java.time.LocalDate;

public class GameDTO {
	private String memberId;      // member_id
    private int gameCount;        // game_count
    private int point;            // point
    private LocalDate createDate;  // create_date
    private int secretNumber;     // secret_number
    private int gameOver;     // game_over
    
 

	private int resultNumber; // result_number
    private String resultContent; // result_content
	
	public GameDTO() {}
	
	public GameDTO(
			String memberId, 
			int gameCount, 
			int point, 
			LocalDate createDate, 
			int secretNumber,
			int gameOver,
			
			int resultNumber,
			String resultContent) {
		this.memberId = memberId;
		this.gameCount = gameCount;
		this.point = point;
		this.createDate = createDate;
		this.secretNumber = secretNumber;
		this.gameOver = gameOver;
		
		this.resultNumber = resultNumber;
		this.resultContent = resultContent;
	}
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getGameCount() {
		return gameCount;
	}

	public void setGameCount(int gameCount) {
		this.gameCount = gameCount;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public int getSecretNumber() {
		return secretNumber;
	}

	public void setSecretNumber(int secretNumber) {
		this.secretNumber = secretNumber;
	}

	public int isGameOver() {
		return gameOver;
	}

	public void setGameOver(int gameOver) {
		this.gameOver = gameOver;
	}
	
	 public int getResultNumber() {
		return resultNumber;
	}

	public void setResultNumber(int resultNumber) {
		this.resultNumber = resultNumber;
	}

	public String getResultContent() {
		return resultContent;
	}

	public void setResultContent(String resultContent) {
		this.resultContent = resultContent;
	}


	

}
