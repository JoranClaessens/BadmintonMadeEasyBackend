package be.joran.bme.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Game {

	@EmbeddedId
	private GamePK gamePk;
	
	@ManyToOne
    @JoinColumn(name = "matchId", insertable = false, updatable = false)
	@JsonIgnore
	private BadmintonMatch badmintonMatch;
	
	private int pointsTeam1;
	
	private int pointsTeam2;
	
	public Game() {
		
	}
	
	public Game(GamePK gamePk, BadmintonMatch badmintonMatch, int pointsTeam1, int pointsTeam2) {
		this.gamePk = gamePk;
		this.badmintonMatch = badmintonMatch;
		this.pointsTeam1 = pointsTeam1;
		this.pointsTeam2 = pointsTeam2;
	}

	public BadmintonMatch getBadmintonMatch() {
		return badmintonMatch;
	}

	public void setBadmintonMatch(BadmintonMatch badmintonMatch) {
		this.badmintonMatch = badmintonMatch;
	}

	public GamePK getGamePk() {
		return gamePk;
	}

	public void setGamePk(GamePK gamePk) {
		this.gamePk = gamePk;
	}

	public int getPointsTeam1() {
		return pointsTeam1;
	}

	public void setPointsTeam1(int pointsTeam1) {
		this.pointsTeam1 = pointsTeam1;
	}

	public int getPointsTeam2() {
		return pointsTeam2;
	}

	public void setPointsTeam2(int pointsTeam2) {
		this.pointsTeam2 = pointsTeam2;
	}
}
