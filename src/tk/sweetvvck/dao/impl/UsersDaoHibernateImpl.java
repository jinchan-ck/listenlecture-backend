package tk.sweetvvck.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import tk.sweetvvck.dao.UsersDao;
import tk.sweetvvck.domain.Favorite;
import tk.sweetvvck.domain.Users;

@Component("usersDao")
@Transactional
public class UsersDaoHibernateImpl implements UsersDao {
	private static int stateFlag = UsersDao.ADD_USER_FAILEDLY;
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public int addUsers(Users users) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Users.class);
		criteria.add(Restrictions.eq("username", users.getUsername()));
		@SuppressWarnings("unchecked")
		List<Users> list = criteria.list();
		if (list.isEmpty()) {
			stateFlag = ADD_USER_SUCCESSFULLY;
			System.out.println("可以添加！");
          	Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateStr = sdf.format(date);
			users.setDate(dateStr);
		} else {
			stateFlag = ADD_USER_FAILEDLY;
		}
		System.out.println("stateFlag is :" + stateFlag);
		if (stateFlag == ADD_USER_SUCCESSFULLY) {
			try {
				session.save(users);
			} catch (Exception e) {
				e.printStackTrace();
				return 2;
			}
		}
		return stateFlag;
	}

	@Override
	public void deleteUsers(Users users) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(users);
	}

	@Override
	public boolean loginValidate(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Users.class);
		criteria.add(Restrictions.eq("username", username));
		@SuppressWarnings("unchecked")
		List<Users> list = criteria.list();
		if (list.isEmpty()) {
			return false;
		}
		if (list.get(0).getPassword().equalsIgnoreCase(password)) {
			return true;
		} else
			return false;
	}

	@Override
	public String getSpeakerByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Users.class);
		criteria.add(Restrictions.eq("username", username));
		@SuppressWarnings("unchecked")
		List<Users> list = criteria.list();
		if (list.isEmpty()) {
			return null;
		} else
			return list.get(0).getSpeaker();
	}

	@Override
	public String getDateByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Users.class);
		criteria.add(Restrictions.eq("username", username));
		@SuppressWarnings("unchecked")
		List<Users> list = criteria.list();
		if (list.isEmpty()) {
			return null;
		} else
			return list.get(0).getDate();
	}

	@Override
	public boolean enshrineLecture(String username, String speaker, String date) {
		Session session = sessionFactory.getCurrentSession();
      	Date fdate = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateStr = sdf.format(fdate);
		Favorite favorite = new Favorite();
		favorite.setDate(date);
		favorite.setSpeaker(speaker);
		favorite.setUsername(username);
      	favorite.setAddDate(dateStr);
		session.saveOrUpdate(favorite);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Favorite> getFavoriteByUsername(String username) {
		List<Favorite> list = new ArrayList<Favorite>();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Favorite.class);
		criteria.add(Restrictions.eq("username", username));
		list = criteria.list();
		return list;
	}

}
