package tk.sweetvvck.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import tk.sweetvvck.dao.LectureDao;
import tk.sweetvvck.domain.Lecture;

@Component
public class MyTask extends TimerTask {

	@Resource
	private LectureDao lectureDao;
	
	public MyTask() {
	}

	public void run() {
		LectureFromSpider lectureFromSpider = LectureFromSpider.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(new Date());
		String end = df.format(new Date(new Date().getTime() + 3*24*60*60*1000));
		try {
			List<Lecture> lectures = lectureFromSpider
					.getLectureFromInput(MySpider
							.getInputs("http://gaoyong.v6.jspzg.com/userQuery.action?startDate=" + today + "&endDate=" + end));
			for (final Lecture lecture : lectures) {
				lectureDao.addLecture(lecture);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
