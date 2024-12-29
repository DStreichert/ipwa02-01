package iu.ipwa02.ghostnetfishing;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.RowEditEvent;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class GhostnetTableController implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Ghostnet> ghostnets;

	@Inject
	App app;

	@Inject
	CurrentUser currentUser;

	public CurrentUser getCurrentUser() {
		return currentUser;
	}

	@PostConstruct
	public void init() {
		if (!this.currentUser.isLoggedIn()) {
			LoginController.NavigateTo("login.xhtml?faces-redirect=true");
			return;
		}
		this.ghostnets = new ArrayList<>();
		for (Ghostnet g : app.getAllghostnets()) {
			this.ghostnets.add(new Ghostnet(g));
		}
		for (var gh : ghostnets) {
			if (gh.getContact() == null) {
				gh.setContact(new Contact());
			}
			if (gh.getRetrievingContact() == null) {
				gh.setRetrievingContact(new Contact());
			}
			if (gh.getState() != Ghostnet.State.Gemeldet && (gh.getRetrievingContact().getName().isEmpty()
					|| gh.getRetrievingContact().getTel().isEmpty())) {
				gh.setState(Ghostnet.State.Gemeldet);
			}
		}
	}

	public List<Ghostnet> getGhostnets() {
		return ghostnets;
	}

	public Ghostnet.State[] getGhostnetStateList() {
		return Ghostnet.State.values();
	}

	public void onRowEdit(RowEditEvent<Ghostnet> event) {
		try {
			if (!this.currentUser.isLoggedIn()) {
				LoginController.NavigateTo("login.xhtml?faces-redirect=true");
				return;
			}
			var editedGh = event.getObject();
			if (editedGh.getState() != Ghostnet.State.Gemeldet && (editedGh.getRetrievingContact().getName().isEmpty()
					|| editedGh.getRetrievingContact().getTel().isEmpty())) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kontaktdaten fehlen",
						"Die Angabe der Kontaktdaten der Kontaktperson ist erforderlich!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			try (var dao = new GhostnetDAO()) {
				dao.persist(editedGh);
			}
			var originGh = this.getOriginalGhostnet(editedGh);
			app.getAllghostnets().set(app.getAllghostnets().indexOf(originGh), new Ghostnet(editedGh));
			FacesMessage msg = new FacesMessage("Geisternetz bearbeitet",
					"Geisternetz " + String.valueOf(editedGh.getId()) + " erfolgreich bearbeitet");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Geisternetz bearbeiten fehlgeschlagen",
					"Geisternetz " + String.valueOf(event.getObject().getId()) + " bearbeiten fehlgeschlagen");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void onRowCancel(RowEditEvent<Ghostnet> event) {
		var gh = event.getObject();
		var originGhostnet = this.getOriginalGhostnet(gh);
		gh.setValues(originGhostnet.getId(), originGhostnet.getLatitude(), originGhostnet.getLongitude(),
				originGhostnet.getWidth(), originGhostnet.getHeight(), originGhostnet.getState(),
				originGhostnet.getRetrievingUser(), originGhostnet.getReportingUser(), originGhostnet.getContact(),
				originGhostnet.getRetrievingContact());
		FacesMessage msg = new FacesMessage("Bearbeiten abgebrochen",
				"Bearbeiten von Geisternetz " + String.valueOf(event.getObject().getId()) + " wurde abgebrochen");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private Ghostnet getOriginalGhostnet(Ghostnet gh) {
		for (var originalGH : app.getAllghostnets()) {
			if (originalGH.getId() == gh.getId()) {
				return originalGH;
			}
		}
		return null;
	}
}
