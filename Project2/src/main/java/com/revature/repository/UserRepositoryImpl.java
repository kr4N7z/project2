package com.revature.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.models.Friendship;
import com.revature.models.Messages;
import com.revature.models.User;
import com.revature.utility.HibernateSessionFactory;
@Repository
public class UserRepositoryImpl implements UserRepository {

	@Override
	public void insert(User user) {
		Session s = null;
		Transaction tx = null;

		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();

			s.save(user);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
	}

	@Override
	public User findOneByEmail(String email) {
		Session s = null;
		Transaction tx = null;
		User user = null;

		try {
			s = HibernateSessionFactory.getSession();
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> root = cq.from(User.class);
			cq.select(root).where(cb.equal(root.get("email"), email));
			Query<User> q = s.createQuery(cq);

			user = q.getSingleResult();
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public List<User> getFreinds(int senderId) {
		Session s = null;
		Transaction tx = null;
		List<User> friends = new ArrayList<>();

		try {
			Query query = s.createQuery("from Friendship f join f.receiverId where Friendship.senderId = :senderId");
			query.setParameter("senderId", senderId);
			friends = query.getResultList();

			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			s.close();
		}





		return friends;
	}

	@Override
	public void updateLocation(int userId, float latitude, float longitude, String state) {
		Session s = null;
		Transaction tx = null;

		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaUpdate<User> cu = cb.createCriteriaUpdate(User.class);
			Root<User> root = cu.from(User.class);
			
			Predicate whereUser = cb.equal(root.get("user_id"), userId);
			
			
			cu.set("last_state", state);
			cu.set("last_latitude", latitude);
			cu.set("last_longitude", longitude);

			cu.where(whereUser);
			
			s.createQuery(cu).executeUpdate();
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
				
	}

}
