package iu.ipwa02.ghostnetfishing;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class GhostnetDAO extends AbstractDAO<Ghostnet> {

	public GhostnetDAO() {
		super(Ghostnet.class);
	}
}
