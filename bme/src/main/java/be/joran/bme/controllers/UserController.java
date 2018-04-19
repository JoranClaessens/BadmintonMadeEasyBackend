package be.joran.bme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import be.joran.bme.models.User;
import be.joran.bme.services.UserService;

@RestController
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("api/users/email/{email}")
	public User getUserByEmail(@PathVariable byte[] email){
		email = Base64Utils.decode(email);
		return userService.getUserByEmail(new String(email));
	}

	@PostMapping("api/users/create")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping("api/users/login/{email}/{password}")
	public User login(@PathVariable byte[] email, @PathVariable String password) {
		email = Base64Utils.decode(email);
		return userService.login(new String(email), password);
	}
}
