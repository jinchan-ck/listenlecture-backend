package tk.sweetvvck.domain;



/**
 * Favorite entity. @author MyEclipse Persistence Tools
 */

public class Favorite  implements java.io.Serializable {
	private static final long serialVersionUID = 4292792349745059075L;

    // Fields    

     private String username;
     private String date;
     private String speaker;
  	private String addDate;
   
    // Property accessors

  	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
  
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }

    public String getSpeaker() {
        return this.speaker;
    }
    
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
   

}