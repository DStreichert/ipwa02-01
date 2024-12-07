package iu.ipwh02.ghostnetfishing;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * This class represents a ghost net, stores basic data about the net and offers
 * methods for processing this data
 */
@Entity
public class Ghostnet implements IDatabaseEntity {
	/**
	 * The identifier
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * The latitude of the GPS coordinates
	 */
	private float latitude = 0.0f;

	/**
	 * The longitude of the GPS coordinates
	 */
	private float longitude = 0.0f;

	/**
	 * The width
	 */
	private float width = 0.0f;

	/**
	 * The height
	 */
	private float height = 0.0f;

	/**
	 * The state
	 */
	private State state = State.Gemeldet;

	/**
	 * the The retrieving User
	 */
	@ManyToOne
	private User retrievingUser;

	/**
	 * the The reporting User
	 */
	@ManyToOne
	private User reportingUser;

	/**
	 * The contact details of reporting user
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	private Contact contact = new Contact();

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	/**
	 * The contact details of retrieving User
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	private Contact retrievingContact = new Contact();

	public Contact getRetrievingContact() {
		return retrievingContact;
	}

	public void setRetrievingContact(Contact retrievingContact) {
		this.retrievingContact = retrievingContact;
	}

	public User getRetrievingUser() {
		return retrievingUser;
	}

	public void setRetrievingUser(User retrievingUser) {
		this.retrievingUser = retrievingUser;
	}

	public User getReportingUser() {
		return reportingUser;
	}

	public void setReportingUser(User reportingUser) {
		this.reportingUser = reportingUser;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public Ghostnet() {
		super();
	}

	public Ghostnet(int id, float latitude, float longitude, float width, float height, Ghostnet.State state, User retrievingUser,
			User reportingUser, Contact contact, Contact retrievingContact) {
		if(id > 0) {
			this.id = id;
		}
		this.latitude = latitude;
		this.longitude = longitude;
		this.width = width;
		this.height = height;
		this.state = state;
		this.retrievingUser = retrievingUser;
		this.reportingUser = reportingUser;
		this.contact = contact;
		this.retrievingContact = retrievingContact;
	}

	public Ghostnet(Ghostnet ghostnet) {
		super();
		if(ghostnet.id > 0) {
			this.id = ghostnet.id;
		}
		this.latitude = ghostnet.latitude;
		this.longitude = ghostnet.longitude;
		this.width = ghostnet.width;
		this.height = ghostnet.height;
		this.state = ghostnet.state;
		this.retrievingUser = ghostnet.retrievingUser;
		this.reportingUser = ghostnet.reportingUser;
		this.contact = new Contact(ghostnet.contact);
		this.retrievingContact = new Contact(ghostnet.retrievingContact);
	}

	public void setValues(int id, float latitude, float longitude, float width, float height, Ghostnet.State state, User retrievingUser,
			User reportingUser, Contact contact, Contact retrievingContact) {
		if(id > 0) {
			this.id = id;
		}
		this.latitude = latitude;
		this.longitude = longitude;
		this.width = width;
		this.height = height;
		this.state = state;
		this.retrievingUser = retrievingUser;
		this.reportingUser = reportingUser;
		this.contact = contact;
		this.retrievingContact = retrievingContact;
	}

	public enum State {
		Gemeldet("Gemeldet"), BergungBevorstehend("Bergung bevorstehend"), Geborgen("Geborgen"), Verschollen("Verschollen");

		private final String name;

		public String getName() {
			return name;
		}

		State(String name) {
			this.name = name;
		}

	    public boolean equalsName(String otherName) {
	        return name.equals(otherName);
	    }

	    @Override
		public String toString() {
	       return this.name;
	    }
	}
}
