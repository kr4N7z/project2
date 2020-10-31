package com.revature.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Friendship;
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
	
//	public static void main(String[] args) {
//		
//		User u = new User("standard", "scotty@email.com", "secret", "Scotty", "Doe", 93.0f,
//				37.0f,"Georgia", Date.valueOf("2020-10-10"), Date.valueOf("2020-10-29"));
//		Friendship f = new Friendship(2,4,true);
//		
//		UserRepositoryImpl uri = new UserRepositoryImpl();
//		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
//		
//		uri.insert(u);
//	}
}
