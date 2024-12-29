package iu.ipwa02.ghostnetfishing;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * This class represents a user, stores basic data about the user and offers
 * methods for processing this data
 */
@Entity
public class User implements IDatabaseEntity {
	/**
	 * The identifier
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * The username
	 */
	private String username;

	/**
	 * The password
	 */
	private String password;

	/**
	 * The contact details
	 */
	@ManyToOne
	private Contact contact = new Contact();

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passwd) {
		this.password = passwd;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public User() {
		super();
	}

	/**
	 * @param username The username
	 * @param passwd The password
	 * @param name The name
	 * @param tel The telephone number
	 */
	public User(String username, String passwd, String name, String tel) {
		super();
		this.username = username;
		this.password = passwd;
		this.contact = new Contact(name, tel);
	}

	/**
	 * @param id The identifier
	 * @param username The username
	 * @param passwd The password
	 * @param cid The id from the contact object
	 * @param name The name
	 * @param tel The telephone number
	 */
	public User(int id, String username, String passwd, int cid, String name, String tel) {
		super();
		this.id = id;
		this.username = username;
		this.password = passwd;
		this.contact = new Contact(cid, name, tel);
	}
}
