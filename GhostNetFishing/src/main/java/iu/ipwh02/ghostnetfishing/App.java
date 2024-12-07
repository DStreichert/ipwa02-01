package iu.ipwh02.ghostnetfishing;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Named
@ApplicationScoped
public class App implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Ghostnet> allghostnets = new ArrayList<>();

	static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("ghostnetfishingPersistenceUnit");

	private List<User> users;

	public App() {
		try (var dao = new GhostnetDAO()) {
			this.allghostnets = dao.findAll();
		}
		try (var dao = new UserDAO()) {
			this.users = dao.findAll();
		}
	}

	public List<Ghostnet> getAllghostnets() {
		return allghostnets;
	}

	static String hashPassword(String name, String pass, String salt) {
		try {
			MessageDigest digester = MessageDigest.getInstance("SHA-512");
			byte[] hashBytes = digester.digest((name + pass + salt).getBytes(StandardCharsets.UTF_8));
			return new String(Base64.getEncoder().encode(hashBytes));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	void validateUsernameAndPassword(CurrentUser currentUser, String name, String pass, String salt) {
		String passHash = hashPassword(name, pass, salt);
		currentUser.reset();
		for (User user : users) {
			if (user.getUsername().equals(name)) {
				if (user.getPassword().equals(passHash)) {
					currentUser.setUser(user);
					currentUser.setLoggedIn(true);
					return;
				}
			}
		}
	}

}
