package be.joran.bme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.joran.bme.models.CompetitionPlayer;

@Repository
public interface CompetitionPlayerRepository extends JpaRepository<CompetitionPlayer, Integer> {

}
