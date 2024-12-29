package iu.ipwa02.ghostnetfishing;
import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;


import jakarta.inject.Named;

@Named
@ViewScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	App app;

	@Inject
	CurrentUser currentUser;

	private static final String salt = "vXsia8c04PhBtnG3isvjlemj7Bm6rAhBR8JRkf2z";

	String user, password;

	public CurrentUser getCurrentUser() {
		return currentUser;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String logout() {
		currentUser.reset();
		return "index.xhtml?faces-redirect=true";
	}

	public String login() {
		app.validateUsernameAndPassword(currentUser, this.getUser(), this.getPassword(), salt);
		FacesContext context = FacesContext.getCurrentInstance();
		if (currentUser.isLoggedIn()) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolgreich angemeldet",
					"Sie haben sich erfolgreich angemeldet."));
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getFlash().setKeepMessages(true);
			return "ghostnets.xhtml?faces-redirect=true";
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler",
					"Passwort und Benutzername nicht erkannt."));
			return "";
		}
	}

	public static void NavigateTo(String uri) {
		FacesContext context = FacesContext.getCurrentInstance();
        NavigationHandler nh = context.getApplication().getNavigationHandler();
        nh.handleNavigation(context, null, uri);
	}

	public boolean isLoggedIn() {
		return currentUser.isLoggedIn();
	}
}