package tk.sweetvvck.domain;

/**
 * Talks entity. @author MyEclipse Persistence Tools
 */

public class Talks implements java.io.Serializable {

	private static final long serialVersionUID = 8994275534835666767L;
	private String speaker;
	private String date;
	private String username;
	private String notes;

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}