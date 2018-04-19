package be.joran.bme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.joran.bme.enums.GameType;
import be.joran.bme.models.BadmintonMatch;
import be.joran.bme.models.Game;
import be.joran.bme.models.GamePK;
import be.joran.bme.models.Tournament;
import be.joran.bme.models.TournamentPlayer;
import be.joran.bme.models.User;
import be.joran.bme.repositories.BadmintonMatchRepository;
import be.joran.bme.repositories.GameRepository;
import be.joran.bme.repositories.TournamentPlayerRepository;
import be.joran.bme.repositories.TournamentRepository;
import be.joran.bme.repositories.UserRepository;

@Service
public class BadmintonMatchService {

	@Autowired
	private BadmintonMatchRepository badmintonMatchRepository;

	@Autowired
	private TournamentRepository tournamentRepository;

	@Autowired
	private TournamentPlayerRepository tournamentPlayerRepository;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private UserRepository userRepository;

	public List<BadmintonMatch> getBadmintonMatches() {
		return badmintonMatchRepository.findAllByOrderByIdDesc();
	}

	public Game getGameByGamePK(int matchId, int gameId) {
		GamePK gamePk = new GamePK(matchId, gameId);
		return gameRepository.findByGamePk(gamePk);
	}

	public List<BadmintonMatch> getBadmintonMatchesByUser(int id) {
		User user = userRepository.findOne(id);
		return badmintonMatchRepository.findByUserOrderByIdDesc(user);
	}

	public BadmintonMatch getBadmintonMatchById(int id) {
		return badmintonMatchRepository.findOne(id);
	}

	public BadmintonMatch getBadmintonMatchByPlayerNames(String player1, String player2, int tournamentId) {
		Tournament tournament = tournamentRepository.findOne(tournamentId);
		return badmintonMatchRepository.findByPlayer1AndPlayer2AndTournament(player1, player2, tournament);
	}

	public BadmintonMatch createBadmintonMatch(BadmintonMatch badmintonMatch, int userId) {
		User user = userRepository.findOne(userId);

		if (user != null) {
			badmintonMatch.setUser(user);
			badmintonMatch = badmintonMatchRepository.save(badmintonMatch);

			Game game = new Game(new GamePK(badmintonMatch.getId(), 1), badmintonMatch, 0, 0);
			game = gameRepository.save(game);

			if (badmintonMatch != null && game != null) {
				return badmintonMatch;
			}
		}

		return null;
	}

	public Game createGame(Game game) {
		System.out.println(game.getGamePk().getGameId());
		return gameRepository.save(game);
	}

	public BadmintonMatch updateBadmintonMatch(BadmintonMatch badmintonMatch) {
		BadmintonMatch fullMatch = badmintonMatchRepository.findOne(badmintonMatch.getId());
		badmintonMatch.setUser(fullMatch.getUser());

		if (fullMatch.getTournament() != null) {
			badmintonMatch.setTournament(fullMatch.getTournament());
		}
		
		if (fullMatch.getCompetition() != null) {
			badmintonMatch.setCompetition(fullMatch.getCompetition());
		}

		if (badmintonMatch.getMatchFinished() != null && fullMatch.getTournament() != null) {
			int gamesTeam1 = 0;
			int gamesTeam2 = 0;

			for (int i = 0; i < badmintonMatch.getGames().size(); i++) {
				if (badmintonMatch.getGames().get(i).getPointsTeam1() > badmintonMatch.getGames().get(i)
						.getPointsTeam2()) {
					gamesTeam1++;
				} else {
					gamesTeam2++;
				}
			}

			List<TournamentPlayer> players;
			boolean winnerTeam1 = false;
			if (gamesTeam1 > gamesTeam2) {
				players = tournamentPlayerRepository.findByNameAndTournament(badmintonMatch.getPlayer1(),
						fullMatch.getTournament());
				winnerTeam1 = true;
			} else {
				players = tournamentPlayerRepository.findByNameAndTournament(badmintonMatch.getPlayer2(),
						fullMatch.getTournament());
			}

			int maxRound = 0;
			TournamentPlayer highestRoundplayer = new TournamentPlayer();
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getRound() > maxRound) {
					maxRound = players.get(i).getRound();
					highestRoundplayer = players.get(i);
				}
			}

			if (winnerTeam1) {
				if (fullMatch.getTournament().getType() == GameType.MEN_SINGLE || fullMatch.getTournament().getType() == GameType.WOMEN_SINGLE) {
					if (tournamentPlayerRepository.findByRoundAndPositionAndTournament(highestRoundplayer.getRound(),
							highestRoundplayer.getPosition() + 1, fullMatch.getTournament()) != null) {
						TournamentPlayer player = new TournamentPlayer();
						player.setName(highestRoundplayer.getName());
						player.setRound(highestRoundplayer.getRound() + 1);
						player.setPosition((int) Math.ceil((double) highestRoundplayer.getPosition() / 2.0));
						player.setTournament(fullMatch.getTournament());
						tournamentPlayerRepository.save(player);
					}
				} else {
					if (tournamentPlayerRepository.findByRoundAndPositionAndTournament(highestRoundplayer.getRound(),
							highestRoundplayer.getPosition() + 2, fullMatch.getTournament()) != null) {
						TournamentPlayer player1 = new TournamentPlayer();
						player1.setName(highestRoundplayer.getName());
						player1.setRound(highestRoundplayer.getRound() + 1);
						player1.setPosition((int) Math.ceil((double) highestRoundplayer.getPosition() / 2.0));
						player1.setTournament(fullMatch.getTournament());
						tournamentPlayerRepository.save(player1);
						
						TournamentPlayer player3 = new TournamentPlayer();
						player3.setName(badmintonMatch.getPlayer3());
						player3.setRound(highestRoundplayer.getRound() + 1);
						player3.setPosition((int) Math.ceil((double) highestRoundplayer.getPosition() / 2.0) + 1);
						player3.setTournament(fullMatch.getTournament());
						tournamentPlayerRepository.save(player3);
					}
				}
			} else {
				if (fullMatch.getTournament().getType() == GameType.MEN_SINGLE || fullMatch.getTournament().getType() == GameType.WOMEN_SINGLE) {
					if (tournamentPlayerRepository.findByRoundAndPositionAndTournament(highestRoundplayer.getRound(),
							highestRoundplayer.getPosition() - 1, fullMatch.getTournament()) != null) {
						TournamentPlayer player = new TournamentPlayer();
						player.setName(highestRoundplayer.getName());
						player.setRound(highestRoundplayer.getRound() + 1);
						player.setPosition((int) Math.ceil((double) highestRoundplayer.getPosition() / 2.0));
						player.setTournament(fullMatch.getTournament());
						tournamentPlayerRepository.save(player);
					}
				} else {
					if (tournamentPlayerRepository.findByRoundAndPositionAndTournament(highestRoundplayer.getRound(),
							highestRoundplayer.getPosition() - 2, fullMatch.getTournament()) != null) {
						TournamentPlayer player1 = new TournamentPlayer();
						player1.setName(highestRoundplayer.getName());
						player1.setRound(highestRoundplayer.getRound() + 1);
						player1.setPosition((int) Math.ceil((double) highestRoundplayer.getPosition() / 2.0) - 1);
						player1.setTournament(fullMatch.getTournament());
						tournamentPlayerRepository.save(player1);
						
						TournamentPlayer player3 = new TournamentPlayer();
						player3.setName(badmintonMatch.getPlayer4());
						player3.setRound(highestRoundplayer.getRound() + 1);
						player3.setPosition((int) Math.ceil((double) highestRoundplayer.getPosition() / 2.0));
						player3.setTournament(fullMatch.getTournament());
						tournamentPlayerRepository.save(player3);
					}
				}
			}
		}
		return badmintonMatchRepository.save(badmintonMatch);
	}

	public Game updateGame(Game game) {
		return gameRepository.save(game);
	}

	public BadmintonMatch deleteMatch(int matchId) {
		BadmintonMatch match = badmintonMatchRepository.findOne(matchId);
		if (match.getTournament() == null) {
			gameRepository.delete(match.getGames());
			badmintonMatchRepository.delete(match);
		}
		
		BadmintonMatch checkMatch = badmintonMatchRepository.findOne(matchId);
		if (checkMatch != null) {
			return checkMatch;
		}
		return null;
	}
}
