package tk.sweetvvck.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import tk.sweetvvck.dao.TalksDao;
import tk.sweetvvck.domain.Talks;

@Component("talksDao")
@Transactional
public class TalksDaoHibernateImpl implements TalksDao {
	private static String stateFlag = TalksDao.ADD_TALKS_FAILEDLY;
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public List<Talks> getTalks(String speaker, String date) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Talks.class);
		criteria.add(Restrictions.eq("speaker", speaker));
		criteria.add(Restrictions.eq("date", date));
		@SuppressWarnings("unchecked")
		List<Talks> list = criteria.list();

		return list;
	}

	@Override
	public String addTalks(Talks talks) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Talks.class);
		criteria.add(Restrictions.eq("username", talks.getUsername()));
		criteria.add(Restrictions.eq("date", talks.getDate()));
		criteria.add(Restrictions.eq("notes", talks.getNotes()));
		criteria.add(Restrictions.eq("speaker", talks.getSpeaker()));
		@SuppressWarnings("unchecked")
		List<Talks> list = criteria.list();
		if (list.isEmpty()) {
			stateFlag = ADD_TALKS_SUCCESSFULLY;
			System.out.println("可以添加！");
		} else {
			stateFlag = ADD_TALKS_FAILEDLY;
		}
		System.out.println("stateFlag is :" + stateFlag);
		if (stateFlag == ADD_TALKS_SUCCESSFULLY)
			session.save(talks);

		return stateFlag;
	}
}
