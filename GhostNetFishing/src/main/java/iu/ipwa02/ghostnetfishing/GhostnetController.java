package iu.ipwa02.ghostnetfishing;
import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class GhostnetController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	App app;

	@Inject
	CurrentUser currentUser;

	public CurrentUser getCurrentUser() {
		return currentUser;
	}

	private Ghostnet newGhostnet = new Ghostnet();

	public Ghostnet getNewghostnet() {
		return this.newGhostnet;
	}

	@PostConstruct
	public void init() {
		setNewghostnetNameTel();
	}

	private void setNewghostnetNameTel() {
		if (this.currentUser.isLoggedIn()) {
			if (!StringUtils.isEmpty(this.currentUser.getUser().getContact().getName())
					|| !StringUtils.isEmpty(this.currentUser.getUser().getContact().getTel())) {
				this.getNewghostnet().getContact().setName(this.currentUser.getUser().getContact().getName());
				this.getNewghostnet().getContact().setTel(this.currentUser.getUser().getContact().getTel());
			}
		}
	}

	public void report() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			var gh = this.getNewghostnet();
			if (this.currentUser.isLoggedIn()) {
				gh.setReportingUser(this.currentUser.getUser());
			}
			if (StringUtils.isEmpty(gh.getContact().getName()) && StringUtils.isEmpty(gh.getContact().getTel())) {
				gh.setContact(null);
			}
			try (var dao = new GhostnetDAO()) {
				dao.persist(gh);
			}
			context.addMessage(null, new FacesMessage("Erfolgreich gemeldet",
					"Geisternetz " + String.valueOf(gh.getId()) + " wurde erfolgreich gemeldet"));
			if (gh.getContact() == null) {
				gh.setContact(new Contact());
			}
			app.getAllghostnets().add(gh);
			this.newGhostnet = new Ghostnet();
			this.setNewghostnetNameTel();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Melden fehlgeschlagen",
					e.getLocalizedMessage() + "<br />" + e.getStackTrace().toString()));
		}
	}
}
