package be.joran.bme.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String email;
	
	private String password;
	
	private String salt;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<BadmintonMatch> matches;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Tournament> tournaments;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Competition> competitions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public List<BadmintonMatch> getMatches() {
		return matches;
	}

	public void setMatches(List<BadmintonMatch> matches) {
		this.matches = matches;
	}

	public List<Tournament> getTournaments() {
		return tournaments;
	}

	public void setTournaments(List<Tournament> tournaments) {
		this.tournaments = tournaments;
	}

	public List<Competition> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(List<Competition> competitions) {
		this.competitions = competitions;
	}
}
