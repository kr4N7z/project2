package com.revature.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Friendship;
import com.revature.models.Messages;
import com.revature.models.User;
import com.revature.utility.HibernateSessionFactory;

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
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			CriteriaBuilder builder = s.getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(User.class);
			Root<User> rootUser = criteria.from(User.class);
			Root<Friendship> rootFriendship = criteria.from(Friendship.class);
			criteria.select(rootUser);
			Predicate statusTrue = builder.equal(rootFriendship.get("status"), true);
			
			Predicate whereSender = builder.equal(rootFriendship.get("senderId"), senderId);
			
			Predicate whereUserId = builder.equal(rootUser.get("userID"),rootFriendship.get("receiverId"));
			
			Predicate finalQuery = builder.and(statusTrue, whereSender, whereUserId);
			criteria.where(finalQuery);
			List<User> users = s.createQuery(criteria).getResultList();
			tx.commit();
			return users;
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			s.close();
		}
		return friends;
	}
	public List<User> getAllUsers() {
		Session s = null;
		Transaction tx = null;
		List<User> users = new ArrayList<>();

		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			
			users = s.createQuery("FROM User", User.class).getResultList();
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			s.close();
		}
		return users;
	}

}
