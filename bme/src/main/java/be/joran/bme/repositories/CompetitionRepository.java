package be.joran.bme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.joran.bme.models.Competition;
import be.joran.bme.models.User;

public interface CompetitionRepository extends JpaRepository<Competition, Integer> {
	List<Competition> findAllByOrderByIdDesc();
	List<Competition> findByUserOrderByIdDesc(User user);
}
