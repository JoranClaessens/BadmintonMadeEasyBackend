package be.joran.bme.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import be.joran.bme.models.BadmintonMatch;
import be.joran.bme.models.Tournament;
import be.joran.bme.models.TournamentPlayer;
import be.joran.bme.services.TournamentService;

@RestController
@CrossOrigin(origins="*")
public class TournamentController {
	
	@Autowired
	private TournamentService tournamentService;
	
	@GetMapping("api/tournaments")
	public List<Tournament> getTournaments() {
		return tournamentService.getTournaments();
	}
	
	@GetMapping("api/tournaments/{id}")
	public Tournament getTournamentById(@PathVariable int id) {
		return tournamentService.getTournamentById(id);
	}
	
	@GetMapping("api/tournaments/user/{id}")
	public List<Tournament> getTournamentsByUser(@PathVariable int id) {
		return tournamentService.getTournamentsByUser(id);
	}
	
	@PostMapping("api/tournaments/create/{userId}")
	public Tournament createTournament(@RequestBody Tournament tournament, @PathVariable int userId) {
		return tournamentService.createTournament(tournament, userId);
	}
	
	@PostMapping("api/tournaments/matches/{userId}/{tournamentId}")
	public BadmintonMatch createBadmintonMatch(@RequestBody BadmintonMatch badmintonMatch, @PathVariable int userId, @PathVariable int tournamentId) {
		return tournamentService.createBadmintonMatch(badmintonMatch, userId, tournamentId);
	}
	
	@PutMapping("api/tournaments")
	public Tournament updateTournament(@RequestBody Tournament tournament) {
		return tournamentService.updateTournament(tournament);
	}
	
	@DeleteMapping("api/tournaments/{id}")
	public Tournament deleteTournament(@PathVariable int id) {
		return tournamentService.deleteTournament(id);
	}
	
	@DeleteMapping("api/tournaments/players/{id}")
	public TournamentPlayer deleteTournamentPlayer(@PathVariable int id) {
		return tournamentService.deleteTournamentPlayer(id);
	}
}
