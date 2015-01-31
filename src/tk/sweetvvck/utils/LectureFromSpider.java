package tk.sweetvvck.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import tk.sweetvvck.domain.Lecture;
import java.io.UnsupportedEncodingException;
public class LectureFromSpider {

	private static LectureFromSpider instance = null;
	private static Logger logger = Logger. getLogger("name");
	public  LectureFromSpider() {
	}

	public static LectureFromSpider getInstance() {
		if (instance == null)
			synchronized (LectureFromSpider.class) {
				if (instance == null)
					instance = new LectureFromSpider();
			}
		return instance;
	}

	private List<File> getLectureFileList() {
		File filePath = new File("D:\\spider\\gaoyong.v6.jspzg.com");
		File[] files = filePath.listFiles();
		List<File> fileList = new ArrayList<File>();
		for (int i = 0; i < files.length; i++) {
			System.out.println(files[i].getName());
			if (files[i].getName().startsWith("lectureInfo")) {
				fileList.add(files[i]);
			}
		}
		return fileList;
	}

	public List<Lecture> getLectureFromFiles() {
		List<File> fileList = getLectureFileList();
		List<Lecture> lectures = new ArrayList<Lecture>();
		for (File file : fileList) {
			List<String> list = getUsefulContent(file);
			lectures.add(getSingleLecture(list));
		}
		return lectures;
	}
	
	public List<Lecture> getLectureFromInput(List<InputStream> ins) throws UnsupportedEncodingException, IOException {
      	logger.log(Level.INFO, " 来这里了吗？ ");
		List<Lecture> lectures = new ArrayList<Lecture>();
		for (InputStream in : ins) {
          	logger.log(Level.INFO, " 遍历inputstream里，这里呢？ ");
			List<String> list = getUsefulContent(in);
			lectures.add(getSingleLecture(list));
		}
		return lectures;
	}

	private Lecture getSingleLecture(List<String> list) {
		Lecture lecture = new Lecture();
		for (int i = 0; i < list.size(); i++) {
          logger.log(Level.INFO, " 遍历list<string>里，这里呢？ " + list.get(i));
			System.out.println(i);
			if (list.get(i).equalsIgnoreCase("讲座名称")
					&& (!list.get(i + 1).equalsIgnoreCase("讲座地点"))) {
				lecture.setLecture(list.get(i + 1));
				System.out.println(lecture.getLecture());
			} else if (list.get(i).equalsIgnoreCase("讲座地点")
					&& (!list.get(i + 1).equalsIgnoreCase("讲座简介"))) {
				if (list.get(i + 1).contains("大学")) {
					lecture.setHost(list.get(i + 1).substring(0,
							list.get(i + 1).indexOf("大学")+2));
					lecture.setAddress(list.get(i + 1).substring(
							list.get(i + 1).indexOf("大学") + 2,
							list.get(i + 1).length()));
				} else {
					lecture.setHost(list.get(i + 1));
					lecture.setAddress(list.get(i + 1));
				}
			} else if (list.get(i).equalsIgnoreCase("讲座简介")
					&& (!list.get(i + 1).equalsIgnoreCase("开始时间"))) {
				lecture.setContent(list.get(i + 1));
			} else if (list.get(i).equalsIgnoreCase("讲座简介")
					&& list.get(i + 1).equalsIgnoreCase("开始时间")) {
				lecture.setContent(list.get(i - 3));
			} else if (list.get(i).equalsIgnoreCase("开始时间")
					&& (!list.get(i + 3).equalsIgnoreCase("主讲人"))) {
				if (!list.get(i + 3).equalsIgnoreCase("-")) {
					lecture.setDate(list.get(i + 1).replace(" ", ",")
							+ list.get(i + 3).replace(
									list.get(i + 3).substring(0,
											list.get(i + 3).indexOf(" ")+1), "-"));
				} else
					lecture.setDate(list.get(i + 1).replace(" ", ","));
			} else if (list.get(i).equalsIgnoreCase("主讲人")
					&& (!list.get(i + 1).equalsIgnoreCase("主讲人介绍"))) {
				System.out.println("主讲人：--------->" + list.get(i + 1));
				if (list.get(i + 1) != null)
					lecture.setSpeaker(list.get(i + 1));
				else
					lecture.setSpeaker("暂无讲座人信息");
			} else if (list.get(i).equalsIgnoreCase("主讲人")
					&& list.get(i + 1).equalsIgnoreCase("主讲人介绍")) {
				lecture.setSpeaker("暂无讲座人信息");
			}
		}
		return lecture;
	}
	
	public List<String> getUsefulContent(File file) {
		BufferedReader buffer;
		String result = "";
		String line = "";
		try {
			buffer = new BufferedReader(new FileReader(file));

			while ((line = buffer.readLine()) != null) {
				result += line;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] a = result.split("\\p{Space}*<[^>]*>\\p{Space}*");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < a.length; i++) {
			if (!a[i].isEmpty()) {
				list.add(a[i]);
			}
		}
		return list;
	}
	
	public List<String> getUsefulContent(InputStream in) throws UnsupportedEncodingException, IOException  {
      	byte[] buffer = new byte[2048];
        int readBytes = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while((readBytes = in.read(buffer)) > 0){
            stringBuilder.append(new String(buffer, 0, readBytes, "UTF-8"));
        }
		String result = stringBuilder.toString();
		String[] a = result.split("\\p{Space}*<[^>]*>\\p{Space}*");
      	logger.log(Level.INFO, " a= " + a[20]);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < a.length; i++) {
			if (!a[i].isEmpty()) {
				list.add(a[i]);
			}
		}
		return list;
	}

}
