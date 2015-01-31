package tk.sweetvvck.dao;

import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
	private static LectureDao lectureDao = null;
	private static UsersDao usersDao = null;
	private static TalksDao talksDao = null;
	private static DaoFactory instance = new DaoFactory();

	private DaoFactory() {
		try {
			Properties prop = new Properties();
			InputStream inStream = DaoFactory.class.getClassLoader()
					.getResourceAsStream("daoconfig.properties");
			prop.load(inStream);
			String lectureDaoClass = prop.getProperty("lectureDaoClass");
			String usersDaoClass = prop.getProperty("usersDaoClass");
			String talksDaoClass = prop.getProperty("talksDaoClass");

			lectureDao = (LectureDao) Class.forName(lectureDaoClass)
					.newInstance();
			usersDao = (UsersDao) Class.forName(usersDaoClass).newInstance();
			talksDao = (TalksDao) Class.forName(talksDaoClass).newInstance();
		} catch (Throwable e) {
			System.out.println("instance:" + instance);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static DaoFactory getInstance() {
		return instance;
	}

	public LectureDao getLectureDao() {
		return lectureDao;
	}
	
	public UsersDao getUsersDao(){
		return usersDao;
	}
	
	public TalksDao getTalksDao(){
		return talksDao;
	}
}
