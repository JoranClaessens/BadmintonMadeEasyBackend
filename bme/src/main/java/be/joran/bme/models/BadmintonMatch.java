package be.joran.bme.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.joran.bme.enums.GameType;

@Entity
public class BadmintonMatch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	@Enumerated(EnumType.STRING)
	private GameType type;
	
	private String player1;
	
	private String player2;
	
	private String player3;
	
	private String player4;
	
	private boolean player1Left;
	
	private boolean player2Left;
	
	private boolean serviceTeam1;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date matchCreated;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date matchFinished;
	
	@OneToMany(mappedBy="badmintonMatch", cascade = CascadeType.ALL)
	private List<Game> games;
	
	@ManyToOne
    @JoinColumn(name = "tournamentId")
	@JsonIgnore
	private Tournament tournament;
	
	@ManyToOne
    @JoinColumn(name = "competitionId")
	@JsonIgnore
	private Competition competition;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnore
	private User user;
	
	private String street;
	
	private String city;
	
	private String terrainNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public GameType getType() {
		return type;
	}

	public void setType(GameType type) {
		this.type = type;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public String getPlayer3() {
		return player3;
	}

	public void setPlayer3(String player3) {
		this.player3 = player3;
	}

	public String getPlayer4() {
		return player4;
	}

	public void setPlayer4(String player4) {
		this.player4 = player4;
	}

	public boolean isPlayer1Left() {
		return player1Left;
	}

	public void setPlayer1Left(boolean player1Left) {
		this.player1Left = player1Left;
	}

	public boolean isPlayer2Left() {
		return player2Left;
	}

	public void setPlayer2Left(boolean player2Left) {
		this.player2Left = player2Left;
	}

	public boolean isServiceTeam1() {
		return serviceTeam1;
	}

	public void setServiceTeam1(boolean serviceTeam1) {
		this.serviceTeam1 = serviceTeam1;
	}

	public Date getMatchCreated() {
		return matchCreated;
	}

	public void setMatchCreated(Date matchCreated) {
		this.matchCreated = matchCreated;
	}

	public Date getMatchFinished() {
		return matchFinished;
	}

	public void setMatchFinished(Date matchFinished) {
		this.matchFinished = matchFinished;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTerrainNumber() {
		return terrainNumber;
	}

	public void setTerrainNumber(String terrainNumber) {
		this.terrainNumber = terrainNumber;
	}
}
