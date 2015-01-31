package tk.sweetvvck.dao;

import java.util.List;

import tk.sweetvvck.domain.Talks;

public interface TalksDao {
	
	public static final String ADD_TALKS_SUCCESSFULLY = "1";
	
	public static final String ADD_TALKS_FAILEDLY = "2";
	
	public List<Talks> getTalks(String speaker, String date);
	
	public String addTalks(Talks talks);
	
}
