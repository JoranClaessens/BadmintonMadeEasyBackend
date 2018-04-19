package be.joran.bme.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class MD5 {

	public static String hashPassword(String wachtwoord, String salt) {
		return BCrypt.hashpw(wachtwoord, salt);
	}

	public static String generateSalt(int size) {
		return BCrypt.gensalt(size);
	}
}
