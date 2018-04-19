package be.joran.bme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.joran.bme.models.Game;
import be.joran.bme.models.GamePK;

public interface GameRepository extends JpaRepository<Game, Integer> {
	Game findByGamePk(GamePK gamePK);
}
