package iu.ipwa02.ghostnetfishing;
import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class CurrentUser implements Serializable {

	private static final long serialVersionUID = 3396067367106596473L;

	private boolean loggedIn;

    private User user = new User();

    public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
    	this.loggedIn = loggedIn;
    }

	public void reset() {
        this.loggedIn = false;
        this.user = new User();
    }

}
