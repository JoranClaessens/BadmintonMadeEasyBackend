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

import be.joran.bme.enums.CompetitionType;

@Entity
public class Competition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	@Enumerated(EnumType.STRING)
	private CompetitionType type;

	private String team1;
	
	private String team2;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	private String street;
	
	private String city;
	
	@OneToMany(mappedBy="competition", cascade = CascadeType.ALL)
	private List<BadmintonMatch> matches;
	
	@OneToMany(mappedBy="competition", cascade = CascadeType.ALL)
	private List<CompetitionPlayer> players;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnore
	private User user;

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

	public CompetitionType getType() {
		return type;
	}

	public void setType(CompetitionType type) {
		this.type = type;
	}

	public String getTeam1() {
		return team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public String getTeam2() {
		return team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

	public List<BadmintonMatch> getMatches() {
		return matches;
	}

	public void setMatches(List<BadmintonMatch> matches) {
		this.matches = matches;
	}

	public List<CompetitionPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(List<CompetitionPlayer> players) {
		this.players = players;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
