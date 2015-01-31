package tk.sweetvvck.actions;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import tk.sweetvvck.dao.LectureDao;
import tk.sweetvvck.dao.TalksDao;
import tk.sweetvvck.dao.UsersDao;
import tk.sweetvvck.domain.Favorite;
import tk.sweetvvck.domain.Lecture;
import tk.sweetvvck.domain.Talks;
import tk.sweetvvck.domain.Users;
import java.text.SimpleDateFormat;
import java.util.Date;

import tk.sweetvvck.utils.LectureFromSpider;
import tk.sweetvvck.utils.MySpider;

import com.opensymphony.xwork2.ActionSupport;

@Component("jsonAction")
@Scope("prototype")
public class JsonAction extends ActionSupport {
	@Resource private LectureDao lectureDao;
	private static final long serialVersionUID = 359317357804951345L;

	public int addLectureState = LectureDao.ADD_LECTURE_SUCCESSFULLY;

	private int registerState = UsersDao.ADD_USER_FAILEDLY;

	public int getAddLectureState() {
		return addLectureState;
	}

	public void setAddLectureState(int addLectureState) {
		this.addLectureState = addLectureState;
	}

	public String addTalksState = TalksDao.ADD_TALKS_SUCCESSFULLY;

	public String getAddTalksState() {
		return addTalksState;
	}

	public void setAddTalksState(String addTalksState) {
		this.addTalksState = addTalksState;
	}

	public List<Talks> talksList = null;

	public List<Talks> getTalksList() {
		return talksList;
	}

	public void setTalksList(List<Talks> talksList) {
		this.talksList = talksList;
	}

	public Lecture lectureInfo = null;

	public String date = "";

	public String address = "";

	public String content = "";

	public String speaker = "";

  	private String baseUrl = "http://gaoyong.v6.jspzg.com/userQuery.action";
      
    public String startDate;
  	
  	public String endDate;
  
	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public List<Lecture> getList() {
		return list;
	}

	public Lecture getLectureInfo() {
		return lectureInfo;
	}

	public void setLectureInfo(Lecture lectureInfo) {
		this.lectureInfo = lectureInfo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setList(List<Lecture> list) {
		this.list = list;
	}

	private String host = "";

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	private List<Lecture> list = new ArrayList<Lecture>();

	public String getLecture() {
		return lecture;
	}

	public void setLecture(String lecture) {
		this.lecture = lecture;
	}

	private String lecture = "";

	private String username = "";

	private String password = "";

	private String notes = "";
	@Resource private UsersDao usersDao;
	@Resource private TalksDao talksDao;

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String execute() throws Exception {
		list = lectureDao.findAllLecture();
		return SUCCESS;
	}

	public String getLectureByDate() throws Exception {
		list = lectureDao.findLectureByDate("2012-7-23");
		return SUCCESS;
	}

	public String getLectureByHost() {
		list = lectureDao.findLectureByHost(host);
		return SUCCESS;
	}

	public String getLectureByLecture() {
		list = lectureDao
				.findLectureByLecture(lecture);
		return SUCCESS;
	}

	public String getLectureByUsername() {
		list = lectureDao
				.findLectureByUsername(username);
		return SUCCESS;
	}

	public String addLectureInfo() {
		if (address == "" || content == "" || date == "" || host == ""
				|| lecture == "" || speaker == "")
			addLectureState = LectureDao.ADD_LECTURE_FAILEDLY;
		else {
			lectureInfo = new Lecture();
			lectureInfo.setAddress(address);
			lectureInfo.setContent(content);
			lectureInfo.setDate(date);
			lectureInfo.setHost(host);
			lectureInfo.setLecture(lecture);
			lectureInfo.setSpeaker(speaker);
			addLectureState = lectureDao
					.addLecture(lectureInfo);
		}
		Lecture lecture = new Lecture();
		lecture.setSpeaker((addLectureState + ""));
		list.add(lecture);
		return SUCCESS;
	}

	public String register() {
		if (username == "" || password == "")
			registerState = UsersDao.ADD_USER_FAILEDLY;
		else {
			Users user = new Users();
			user.setUsername(username);
			user.setPassword(password);
			registerState = usersDao
					.addUsers(user);
		}
		Lecture lecture = new Lecture();
		lecture.setSpeaker((registerState + ""));
		list.add(lecture);
		return SUCCESS;
	}

	public String login() {
		if (!username.isEmpty())
			if (usersDao
					.loginValidate(username, password))
				registerState = UsersDao.ADD_USER_SUCCESSFULLY;
			else
				registerState = UsersDao.ADD_USER_FAILEDLY;
		else
			registerState = UsersDao.ADD_USER_FAILEDLY;
		Lecture lecture = new Lecture();
		lecture.setSpeaker((registerState + ""));
		list.add(lecture);
		return SUCCESS;
	}

	public String getFavoreateLecture() {
		/*
		 * speaker = usersDao
		 * .getSpeakerByUsername(username); date =
		 * usersDao .getDateByUsername(username);
		 */
		List<Favorite> favoriteList = usersDao
				.getFavoriteByUsername(username);
		list = lectureDao
				.findLectureByFavorite(favoriteList);
		return SUCCESS;
	}

	public String favoreate() {
		if (usersDao
				.enshrineLecture(username, speaker, date)) {

			registerState = UsersDao.ADD_USER_SUCCESSFULLY;
		} else
			registerState = UsersDao.ADD_USER_FAILEDLY;
		Lecture lecture = new Lecture();
		lecture.setSpeaker((registerState + ""));
		list.add(lecture);
		return SUCCESS;
	}

	public String getTalks() {
		talksList = talksDao
				.getTalks(speaker, date);
		return SUCCESS;
	}

	public String addTalks() {
		if (username == "" || date == "" || notes == "" || speaker == "")
			addTalksState = TalksDao.ADD_TALKS_FAILEDLY;
		else {
			Talks talks = new Talks();
			talks.setDate(date);
			talks.setSpeaker(speaker);
			talks.setNotes(notes);
			talks.setUsername(username);
			addTalksState = talksDao
					.addTalks(talks);
		}
		Lecture lecture = new Lecture();
		lecture.setSpeaker((addLectureState + ""));
		list.add(lecture);
		return SUCCESS;
	}

	public String deleteOneFavorite() {
		if (lectureDao
				.deleteOneFavorite(username, speaker, date) > 0){

			registerState = UsersDao.ADD_USER_SUCCESSFULLY;
		} else
			registerState = UsersDao.ADD_USER_FAILEDLY;
		Lecture lecture = new Lecture();
		lecture.setSpeaker((registerState + ""));
		list.add(lecture);
		return SUCCESS;
	}
	
	public String deleteAllFavorite() {
		if (lectureDao
				.deleteAllFavorite(username) > 0){

			registerState = UsersDao.ADD_USER_SUCCESSFULLY;
		} else
			registerState = UsersDao.ADD_USER_FAILEDLY;
		Lecture lecture = new Lecture();
		lecture.setSpeaker((registerState + ""));
		list.add(lecture);
		return SUCCESS;
	}
	
	public String getFavoriteNumber() {
		String favoriteNumber = lectureDao.getFavoriteNumber(speaker, date) + "";
		Lecture lecture = new Lecture();
		lecture.setDate(favoriteNumber);
		list.add(lecture);
		return SUCCESS;
	}
  public List<Lecture> lectures;
  	public String addLectures(){
      	LectureFromSpider lectureFromSpider = LectureFromSpider.getInstance();
      	String url = baseUrl + "?startDate=" + startDate + "&endDate=" + endDate;
    	try {
      		lectures = lectureFromSpider.getLectureFromInput(MySpider.getInputs(url));
          	lectureInfo = lectures.get(0);
          	list.addAll(lectures);
          	/*address =lectures.get(0).getAddress();
          	content =lectures.get(0).getContent();
          	date = lectures.get(0).getDate();
          	host = lectures.get(0).getHost();
			lecture = lectures.get(0).getLecture();
          	speaker = lectures.get(0).getSpeaker();
          	addLectureInfo();
          	lectureDao.addLecture(lectures.get(0));*/
      		for (Lecture lecture : lectures) {
        		lectureDao.addLecture(lecture);
      		}
      		return SUCCESS;
      	} catch (Exception e1) {
          	startDate = url;
          	endDate = e1.getMessage();
			e1.printStackTrace();
      		return ERROR;
		}
  	}
  	
  	public String autoAddLectures(){
      	Date today = new Date(System.currentTimeMillis() + 4 * 24 * 3600 *1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(today);
      	LectureFromSpider lectureFromSpider = LectureFromSpider.getInstance();
      	String url = baseUrl + "?startDate=" + date + "&endDate=" + date;
    	try {
      		lectures = lectureFromSpider.getLectureFromInput(MySpider.getInputs(url));
          	lectureInfo = lectures.get(0);
          	list.addAll(lectures);
      		for (Lecture lecture : lectures) {
        		lectureDao.addLecture(lecture);
      		}
      		return SUCCESS;
      	} catch (Exception e1) {
          	startDate = url;
          	endDate = e1.getMessage();
			e1.printStackTrace();
      		return ERROR;
		}
  	}
  
}
