package be.joran.bme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.joran.bme.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
