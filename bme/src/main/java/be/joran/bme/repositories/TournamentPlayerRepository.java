package be.joran.bme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.joran.bme.models.Tournament;
import be.joran.bme.models.TournamentPlayer;

@Repository
public interface TournamentPlayerRepository extends JpaRepository<TournamentPlayer, Integer> {
	List<TournamentPlayer> findByNameAndTournament(String name, Tournament tournament);
	TournamentPlayer findByRoundAndPositionAndTournament(int round, int position, Tournament tournament);
}
