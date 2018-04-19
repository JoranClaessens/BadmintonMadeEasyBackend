package be.joran.bme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.joran.bme.models.BadmintonMatch;
import be.joran.bme.models.Competition;
import be.joran.bme.models.CompetitionPlayer;
import be.joran.bme.models.User;
import be.joran.bme.repositories.CompetitionPlayerRepository;
import be.joran.bme.repositories.CompetitionRepository;
import be.joran.bme.repositories.UserRepository;

@Service
public class CompetitionService {

	@Autowired
	private CompetitionRepository competitionRepository;
	
	@Autowired
	private CompetitionPlayerRepository competitionPlayerRepository;
	
	@Autowired
	private BadmintonMatchService badmintonMatchService;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Competition> getCompetitions() {
		return competitionRepository.findAllByOrderByIdDesc();
	}
	
	public Competition getCompetitionById(int competitionId) {
		return competitionRepository.findOne(competitionId);
	}
	
	public List<Competition> getCompetitionsByUser(int id) {
		User user = userRepository.findOne(id);
		return competitionRepository.findByUserOrderByIdDesc(user);
	}
	
	public Competition createCompetition(Competition competition, int userId) {
		User user = userRepository.findOne(userId);
		
		if (user != null) {
			competition.setUser(user);
			competition = competitionRepository.save(competition);
			
			if (competition != null) {
				return competition;
			}
		}
		
		return null;
	}
	
	public BadmintonMatch createBadmintonMatch(BadmintonMatch badmintonMatch, int userId, int competitionId) {
		BadmintonMatch createdMatch = badmintonMatchService.createBadmintonMatch(badmintonMatch, userId);
		if (createdMatch != null) {
			createdMatch.setCompetition(competitionRepository.findOne(competitionId));
			return badmintonMatchService.updateBadmintonMatch(createdMatch);
		}
		return createdMatch;
	}
	
	public Competition updateCompetition(Competition competition) {
		Competition fullCompetition = competitionRepository.findOne(competition.getId());
		competition.setUser(fullCompetition.getUser());
		for (CompetitionPlayer player : competition.getPlayers()) {
			player.setCompetition(competition);
		}
		competition.setMatches(fullCompetition.getMatches());
		return competitionRepository.save(competition);
	}
	
	public Competition deleteCompetition(int competitionId) {
		Competition competition = competitionRepository.findOne(competitionId);
		
		if (competition.getMatches().size() > 0) {
			for (BadmintonMatch match : competition.getMatches()) {
				badmintonMatchService.deleteMatch(match.getId());
			}
		}
		
		if (competition.getPlayers().size() > 0) {
			for (CompetitionPlayer player : competition.getPlayers()) {
				deleteCompetitionPlayer(player.getId());
			}
		}
		
		competitionRepository.delete(competition);
		Competition checkCompetition = competitionRepository.findOne(competitionId);
		if (checkCompetition != null) {
			return checkCompetition;
		}
		return null;
	}
	
	public CompetitionPlayer deleteCompetitionPlayer(int competitionPlayerId) {
		CompetitionPlayer player = competitionPlayerRepository.findOne(competitionPlayerId);
		competitionPlayerRepository.delete(player);
		CompetitionPlayer checkCompetitionPlayer = competitionPlayerRepository.findOne(competitionPlayerId);
		if (checkCompetitionPlayer != null) {
			return checkCompetitionPlayer;
		}
		return null;
	}
}
