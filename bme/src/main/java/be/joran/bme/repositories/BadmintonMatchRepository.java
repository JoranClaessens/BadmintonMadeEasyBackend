package be.joran.bme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.joran.bme.models.BadmintonMatch;
import be.joran.bme.models.Tournament;
import be.joran.bme.models.User;

public interface BadmintonMatchRepository extends JpaRepository<BadmintonMatch, Integer> {
	List<BadmintonMatch> findAllByOrderByIdDesc();
	List<BadmintonMatch> findByUserOrderByIdDesc(User user);
	BadmintonMatch findByPlayer1AndPlayer2AndTournament(String player1, String player2, Tournament tournament);
}
