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
import be.joran.bme.models.Game;
import be.joran.bme.services.BadmintonMatchService;

@RestController
@CrossOrigin(origins="*")
public class BadmintonMatchController {

	@Autowired
	private BadmintonMatchService badmintonMatchService;
	
	@GetMapping("api/matches")
	public List<BadmintonMatch> getBadmintonMatches() {
		return badmintonMatchService.getBadmintonMatches();
	}
	
	@GetMapping("api/matches/game/{matchId}/{gameId}")
	public Game getBadmintonMatchesByGamePK(@PathVariable int matchId,@PathVariable int gameId) {
		return badmintonMatchService.getGameByGamePK(matchId, gameId);
	}
	
	@GetMapping("api/matches/user/{id}")
	public List<BadmintonMatch> getBadmintonMatchesByUser(@PathVariable int id) {
		return badmintonMatchService.getBadmintonMatchesByUser(id);
	}
	
	@GetMapping("api/matches/{id}")
	public BadmintonMatch getBadmintonMatchesById(@PathVariable int id) {
		return badmintonMatchService.getBadmintonMatchById(id);
	}
	
	@GetMapping("api/matches/{player1}/{player2}/{tournamentId}")
	public BadmintonMatch getBadmintonMatchesByPlayerNames(@PathVariable String player1, @PathVariable String player2, @PathVariable int tournamentId) {
		return badmintonMatchService.getBadmintonMatchByPlayerNames(player1, player2, tournamentId);
	}
	
	@PostMapping("api/matches/create/{userId}")
	public BadmintonMatch createBadmintonMatch(@RequestBody BadmintonMatch badmintonMatch, @PathVariable int userId) {
		return badmintonMatchService.createBadmintonMatch(badmintonMatch, userId);
	}
	
	@PostMapping("api/matches/games/create")
	public Game createGame(@RequestBody Game game) {
		return badmintonMatchService.createGame(game);
	}
	
	@PutMapping("api/matches/game")
	public Game updateGame(@RequestBody Game game) {
		return badmintonMatchService.updateGame(game);
	}
	
	@PutMapping("api/matches")
	public BadmintonMatch updateBadmintonMatch(@RequestBody BadmintonMatch badmintonMatch) {
		return badmintonMatchService.updateBadmintonMatch(badmintonMatch);
	}
	
	@DeleteMapping("api/matches/{id}")
	public BadmintonMatch deleteBadmintonMatch(@PathVariable int id) {
		return badmintonMatchService.deleteMatch(id);
	}
}
