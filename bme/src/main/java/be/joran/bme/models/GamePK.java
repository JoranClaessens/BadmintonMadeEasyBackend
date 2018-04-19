package be.joran.bme.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class GamePK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int matchId;
	
	private int gameId;
	
	public GamePK() {
		
	}
	
	public GamePK(int matchId, int gameId) {
		this.matchId = matchId;
		this.gameId = gameId;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
}
