package tk.sweetvvck.dao;

import java.util.List;

import tk.sweetvvck.domain.Favorite;
import tk.sweetvvck.domain.Lecture;

public interface LectureDao {
	public static final int ADD_LECTURE_SUCCESSFULLY = 1;
	
	public static final int ADD_LECTURE_FAILEDLY = 2;
	
	public int addLecture(Lecture lecture);
	
	public void deleteLecture(Lecture lecture);
	
	public List<Lecture> findAllLecture();
	
	public List<Lecture> findLectureByDate(String date);
	
	public List<Lecture> findLectureByLecture(String lecture);
	
	public List<Lecture> findLectureByHost(String host);
	
	public List<Lecture> findLectureByUsername(String username);
	
	public List<Lecture> findLectureById(String speaker, String date);
	
	public List<Lecture> findLectureByFavorite(List<Favorite> favorite);
	
	public int deleteOneFavorite(String username, String speaker, String date);
	
	public int deleteAllFavorite(String username);
	
	public int getFavoriteNumber(String speaker, String date);
	
}
