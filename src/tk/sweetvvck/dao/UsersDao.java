package tk.sweetvvck.dao;

import java.util.List;

import tk.sweetvvck.domain.Favorite;
import tk.sweetvvck.domain.Users;

public interface UsersDao {
public static final int ADD_USER_SUCCESSFULLY = 1;
	
	public static final int ADD_USER_FAILEDLY = 2;
	
	public int addUsers(Users users);
	
	public void deleteUsers(Users users);
	
	public boolean loginValidate(String username, String password);
	
	public String getSpeakerByUsername(String username);
	
	public String getDateByUsername(String username);

	public boolean enshrineLecture(String username, String speaker, String date);
	
	public List<Favorite> getFavoriteByUsername(String username);
	
}
