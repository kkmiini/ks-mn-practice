package com.ksinfo.pointgame.dto;

import java.time.LocalDateTime;

public class GameDTO {
	private String memberId;      // member_id
    private int gameCount;        // game_count
    private int point;            // point
    private LocalDateTime createDate;  // create_date
    private int secretNumber;     // secret_number
    private boolean gameOver;     // game_over
	
	public GameDTO() {}
	
	public GameDTO(String memberId, int gameCount, int point, LocalDateTime createDate, int secretNumber,
			boolean gameOver) {
		this.memberId = memberId;
		this.gameCount = gameCount;
		this.point = point;
		this.createDate = createDate;
		this.secretNumber = secretNumber;
		this.gameOver = gameOver;
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

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public int getSecretNumber() {
		return secretNumber;
	}

	public void setSecretNumber(int secretNumber) {
		this.secretNumber = secretNumber;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}


	

}
