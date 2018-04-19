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
import be.joran.bme.models.Competition;
import be.joran.bme.models.CompetitionPlayer;
import be.joran.bme.services.CompetitionService;

@RestController
@CrossOrigin(origins="*")
public class CompetitionController {

	@Autowired
	private CompetitionService competitionService;
	
	@GetMapping("api/competitions")
	public List<Competition> getCompetitions() {
		return competitionService.getCompetitions();
	}
	
	@GetMapping("api/competitions/{id}")
	public Competition getCompetitionById(@PathVariable int id) {
		return competitionService.getCompetitionById(id);
	}
	
	@GetMapping("api/competitions/user/{id}")
	public List<Competition> getCompetitionsByUser(@PathVariable int id) {
		return competitionService.getCompetitionsByUser(id);
	}
	
	@PostMapping("api/competitions/create/{userId}")
	public Competition createCompetition(@RequestBody Competition competition, @PathVariable int userId) {
		return competitionService.createCompetition(competition, userId);
	}
	
	@PostMapping("api/competitions/matches/{userId}/{competitionId}")
	public BadmintonMatch createBadmintonMatch(@RequestBody BadmintonMatch badmintonMatch, @PathVariable int userId, @PathVariable int competitionId) {
		return competitionService.createBadmintonMatch(badmintonMatch, userId, competitionId);
	}
	
	@PutMapping("api/competitions")
	public Competition updateCompetition(@RequestBody Competition competition) {
		return competitionService.updateCompetition(competition);
	}
	
	@DeleteMapping("api/competitions/{id}")
	public Competition deleteCompetition(@PathVariable int id) {
		return competitionService.deleteCompetition(id);
	}
	
	@DeleteMapping("api/competitions/players/{id}")
	public CompetitionPlayer deleteCompetitionPlayer(@PathVariable int id) {
		return competitionService.deleteCompetitionPlayer(id);
	}
}
