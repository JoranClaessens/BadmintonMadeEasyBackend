package be.joran.bme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.joran.bme.models.BadmintonMatch;
import be.joran.bme.models.Tournament;
import be.joran.bme.models.TournamentPlayer;
import be.joran.bme.models.User;
import be.joran.bme.repositories.TournamentPlayerRepository;
import be.joran.bme.repositories.TournamentRepository;
import be.joran.bme.repositories.UserRepository;

@Service
public class TournamentService {

	@Autowired
	private TournamentRepository tournamentRepository;
	
	@Autowired
	private TournamentPlayerRepository tournamentPlayerRepository;
	
	@Autowired
	private BadmintonMatchService badmintonMatchService;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Tournament> getTournaments() {
		return tournamentRepository.findAllByOrderByIdDesc();
	}
	
	public Tournament getTournamentById(int tournamentId) {
		return tournamentRepository.findOne(tournamentId);
	}
	
	public List<Tournament> getTournamentsByUser(int id) {
		User user = userRepository.findOne(id);
		return tournamentRepository.findByUserOrderByIdDesc(user);
	}
	
	public Tournament createTournament(Tournament tournament, int userId) {
		User user = userRepository.findOne(userId);
		
		if (user != null) {
			tournament.setUser(user);
			tournament = tournamentRepository.save(tournament);
			
			if (tournament != null) {
				return tournament;
			}
		}
		
		return null;
	}
	
	public BadmintonMatch createBadmintonMatch(BadmintonMatch badmintonMatch, int userId, int tournamentId) {
		BadmintonMatch createdMatch = badmintonMatchService.createBadmintonMatch(badmintonMatch, userId);
		if (createdMatch != null) {
			createdMatch.setTournament(tournamentRepository.findOne(tournamentId));
			return badmintonMatchService.updateBadmintonMatch(createdMatch);
		}
		return createdMatch;
	}
	
	public Tournament updateTournament(Tournament tournament) {
		Tournament fullTournament = tournamentRepository.findOne(tournament.getId());
		tournament.setUser(fullTournament.getUser());
		for (TournamentPlayer player : tournament.getPlayers()) {
			player.setTournament(tournament);
		}
		return tournamentRepository.save(tournament);
	}
	
	public Tournament deleteTournament(int tournamentId) {
		Tournament tournament = tournamentRepository.findOne(tournamentId);
		
		if (tournament.getMatches().size() > 0) {
			for (BadmintonMatch match : tournament.getMatches()) {
				badmintonMatchService.deleteMatch(match.getId());
			}
		}
		
		if (tournament.getPlayers().size() > 0) {
			for (TournamentPlayer player : tournament.getPlayers()) {
				deleteTournamentPlayer(player.getId());
			}
		}
		
		tournamentRepository.delete(tournament);
		Tournament checkTournament = tournamentRepository.findOne(tournamentId);
		if (checkTournament != null) {
			return checkTournament;
		}
		return null;
	}
	
	public TournamentPlayer deleteTournamentPlayer(int tournamentPlayerId) {
		TournamentPlayer player = tournamentPlayerRepository.findOne(tournamentPlayerId);
		tournamentPlayerRepository.delete(player);
		TournamentPlayer checkTournamentPlayer = tournamentPlayerRepository.findOne(tournamentPlayerId);
		if (checkTournamentPlayer != null) {
			return checkTournamentPlayer;
		}
		return null;
	}
}
