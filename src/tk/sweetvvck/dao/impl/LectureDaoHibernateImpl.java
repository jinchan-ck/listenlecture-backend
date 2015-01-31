package tk.sweetvvck.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import tk.sweetvvck.dao.LectureDao;
import tk.sweetvvck.domain.Favorite;
import tk.sweetvvck.domain.Lecture;

@Component("lectureDao")
@Transactional
public class LectureDaoHibernateImpl implements LectureDao {

	private SessionFactory sessionFactory;

	@Override
	public int addLecture(Lecture lecture) {
		int stateFlag = ADD_LECTURE_FAILEDLY;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Lecture.class);
		criteria.add(Restrictions.eq("speaker", lecture.getSpeaker()));
		criteria.add(Restrictions.eq("date", lecture.getDate()));
		@SuppressWarnings("unchecked")
		List<Lecture> list = criteria.list();
		if (list.isEmpty()) {
			stateFlag = ADD_LECTURE_SUCCESSFULLY;
			System.out.println("可以添加！");
		} else {
			for (Lecture result : list) {
				System.out.println("date:" + result.getDate());
				if (!result.getDate().equalsIgnoreCase(lecture.getDate())) {
					System.out.println("result is " + result.getDate());
					System.out.println("lecture is " + lecture.getDate());
					stateFlag = ADD_LECTURE_SUCCESSFULLY;
				}

				else {
					stateFlag = ADD_LECTURE_FAILEDLY;
					break;
				}
			}
		}
		System.out.println("stateFlag is :" + stateFlag);
		if (stateFlag == ADD_LECTURE_SUCCESSFULLY)
			session.save(lecture);
		return stateFlag;
	}

	@Override
	public void deleteLecture(Lecture lecture) {

	}

	@Override
	public List<Lecture> findAllLecture() {
		Session session = sessionFactory.getCurrentSession();
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss E");
		String d = dateformat.format(date);
		String[] m = d.substring(0, d.indexOf(' ')).split("-");
		int mYear = Integer.parseInt(m[0]);
		int mMonth = Integer.parseInt(m[1]);
		int mDay = Integer.parseInt(m[2]);
		//Query query = session.createQuery("from Lecture");
      	Criteria criteria = session.createCriteria(Lecture.class);
		criteria.add(Restrictions.gt("date", d));
		@SuppressWarnings("unchecked")
		List<Lecture> list = criteria.list();
		return list;
	}

	@Override
	public List<Lecture> findLectureByDate(String date) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Lecture.class);
		criteria.add(Restrictions.like("date", date + "%"));
		@SuppressWarnings("unchecked")
		List<Lecture> list = criteria.list();

		return list;
	}

	@Override
	public List<Lecture> findLectureByLecture(String lecture) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Lecture.class);
		criteria.add(Restrictions.like("lecture", "%" + lecture + "%"));
		@SuppressWarnings("unchecked")
		List<Lecture> list = criteria.list();

		return list;
	}

	@Override
	public List<Lecture> findLectureByHost(String host) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Lecture.class);
		criteria.add(Restrictions.like("host", host + "%"));
		@SuppressWarnings("unchecked")
		List<Lecture> list = criteria.list();

		return list;
	}

	@Override
	public List<Lecture> findLectureByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Lecture.class);
		criteria.add(Restrictions.eq("username", username));
		@SuppressWarnings("unchecked")
		List<Lecture> list = criteria.list();

		return list;
	}

	@Override
	public List<Lecture> findLectureById(String speaker, String date) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Lecture.class);
		criteria.add(Restrictions.eq("speaker", speaker));
		criteria.add(Restrictions.eq("date", date));
		@SuppressWarnings("unchecked")
		List<Lecture> list = criteria.list();

		return list;
	}

	@Override
	public List<Lecture> findLectureByFavorite(List<Favorite> favorites) {
		Session session = sessionFactory.getCurrentSession();

		List<Lecture> list = new ArrayList<Lecture>();
		for (Favorite favorite : favorites) {
			String speaker = favorite.getSpeaker();
			String date = favorite.getDate();
			Criteria criteria = session.createCriteria(Lecture.class);
			criteria.add(Restrictions.eq("speaker", speaker));
			criteria.add(Restrictions.eq("date", date));
			@SuppressWarnings("unchecked")
			List<Lecture> eachList = criteria.list();
			if (!eachList.isEmpty())
				list.add(eachList.get(0));
		}

		return list;
	}

	@Override
	public int deleteOneFavorite(String username, String speaker, String date) {
		Session session = sessionFactory.getCurrentSession();

		String hql = "delete Favorite f where f.username='" + username
				+ "' and f.speaker='" + speaker + "' and f.date='" + date + "'";
		Query query = session.createQuery(hql);
		int deleteResult = query.executeUpdate();
		System.out.println(deleteResult);

		return deleteResult;
	}

	@Override
	public int deleteAllFavorite(String username) {
		Session session = sessionFactory.getCurrentSession();

		String hql = "delete Favorite f where f.username='" + username + "'";
		Query query = session.createQuery(hql);
		int deleteResult = query.executeUpdate();
		System.out.println(deleteResult);

		return deleteResult;
	}

	@Override
	public int getFavoriteNumber(String speaker, String date) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Favorite.class);
		criteria.add(Restrictions.eq("speaker", speaker));
		criteria.add(Restrictions.eq("date", date));
		@SuppressWarnings("unchecked")
		List<Lecture> list = criteria.list();

		return list.size();
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		System.out.println("在LectureDaoHibernateImpl里注入SessionFactory 》》》》"
				+ sessionFactory);
		this.sessionFactory = sessionFactory;
	}

}
