package tk.sweetvvck.domain;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	private static final long serialVersionUID = -6287359239543386673L;
	private String username;
	private String password;
	private String speaker;
	private String date;

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String username) {
		this.username = username;
	}

	/** full constructor */
	public Users(String username, String password, String speaker, String date) {
		this.username = username;
		this.password = password;
		this.speaker = speaker;
		this.date = date;
	}

	// Property accessors

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSpeaker() {
		return this.speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}