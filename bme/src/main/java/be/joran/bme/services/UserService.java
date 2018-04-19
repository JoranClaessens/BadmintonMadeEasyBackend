package be.joran.bme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.joran.bme.models.User;
import be.joran.bme.repositories.UserRepository;
import be.joran.bme.utils.MD5;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User createUser(User user) {
		String randomSalt = MD5.generateSalt(12);
		String hashedPassword = MD5.hashPassword(user.getPassword(), randomSalt);

		user.setPassword(hashedPassword);
		user.setSalt(randomSalt);
		return userRepository.save(user);
	}
	
	public User login(String email, String password) {
		User user = userRepository.findByEmail(email);
		String hashedPassword = MD5.hashPassword(password, user.getSalt());
		
		if (user.getPassword().equals(hashedPassword)) {
			return user;
		}
		return null;
	}
}
