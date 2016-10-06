package lib;

public class GameInfo {
	
	private int gameInfoID;
	private int score;
	public GameInfo(int gameInfoID, int score) {
		super();
		this.gameInfoID = gameInfoID;
		this.score = score;
	}
	public int getGameInfoID() {
		return gameInfoID;
	}
	public void setGameInfoID(int gameInfoID) {
		this.gameInfoID = gameInfoID;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
