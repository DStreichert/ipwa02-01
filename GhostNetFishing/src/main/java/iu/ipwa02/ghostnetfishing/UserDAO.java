package iu.ipwa02.ghostnetfishing;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class UserDAO extends AbstractDAO<User> {

	public UserDAO() {
		super(User.class);
	}

}
