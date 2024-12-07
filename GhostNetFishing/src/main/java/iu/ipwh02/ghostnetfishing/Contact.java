package iu.ipwh02.ghostnetfishing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * This class represents a user, stores basic data about the user and offers
 * methods for processing this data
 */
@Entity
public class Contact implements IDatabaseEntity {
	/**
	 * The identifier
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * The name
	 */
	@Column(nullable = false)
	private String name = "";

	/**
	 * The telephone number
	 */
	@Column(nullable = false)
	private String tel = "";

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Contact() {
		super();
	}

	/**
	 * @param name The name
	 * @param tel The telephone number
	 */
	public Contact(String name, String tel) {
		super();
		this.name = name;
		this.tel = tel;
	}

	/**
	 * @param id The identifier
	 * @param name The name
	 * @param tel The telephone number
	 */
	public Contact(int id, String name, String tel) {
		super();
		if(id > 0) {
			this.id = id;
		}
		this.name = name;
		this.tel = tel;
	}

	public Contact(Contact contact) {
		super();
		if(contact.id > 0) {
			this.id = contact.id;
		}
		this.name = contact.name;
		this.tel = contact.tel;
	}
}
