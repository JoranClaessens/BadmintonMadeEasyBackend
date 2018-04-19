package be.joran.bme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.joran.bme.models.Tournament;
import be.joran.bme.models.User;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
	List<Tournament> findAllByOrderByIdDesc();
	List<Tournament> findByUserOrderByIdDesc(User user);
}
