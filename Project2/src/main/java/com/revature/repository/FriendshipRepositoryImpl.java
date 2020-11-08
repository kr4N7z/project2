package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.Friendship;
import com.revature.models.User;
import com.revature.repository.UserRepositoryImpl;
import com.revature.utility.HibernateSessionFactory;
@Repository
public class FriendshipRepositoryImpl implements FriendshipRepository {

	@Override
	public void insertFriendship(Friendship fr) {
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			
			s.save(fr);
			tx.commit();
			System.out.println("MADE IT");
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			s.close();
		}
		
	}

	//this might not work correctly, idk how update works
	@Override
	public void update(Friendship fr) {
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			
			s.update(fr);
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			s.close();
		}
	}

	@Override
	public List<Friendship> viewAllFriendships() {
		Session s = null;
		Transaction tx = null; 
		List<Friendship> friends = new ArrayList<>();
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			
			friends = s.createQuery("FROM friends", Friendship.class).getResultList();
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			s.close();
		}
		
		System.out.println(friends.toString());
		return friends;
	}

	@Override
	public List<Friendship> viewMyFriendships(int senderId) {
		Session s = null;
		Transaction tx = null; 
		List<Friendship> friends = new ArrayList<>();
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Friendship> cq = cb.createQuery(Friendship.class);
			Root<Friendship> root = cq.from(Friendship.class);
			cq.select(root).where(cb.equal(root.get("senderId"), senderId));
			friends = s.createQuery(cq).getResultList();
			
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
	public Friendship getFriendship(int senderId, int receiverId) {
		Session s = null;
		Transaction tx = null; 
		Friendship friend = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Friendship> cq = cb.createQuery(Friendship.class);
			Root<Friendship> root = cq.from(Friendship.class);
			cq.select(root).where(cb.equal(root.get("senderId"), senderId));
			Query<Friendship> q = s.createQuery(cq);
			
			friend = q.getSingleResult();
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			s.close();
		}
		
		
		return friend;
	}

	@Override
	public void removeFriendship(Friendship fr) {
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			
			s.delete(fr);
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			s.close();
		}
	}

	@Override
	public List<User> getMyUnapproved(int currUserId) {
		Session s = null;
		Transaction tx = null; 
		List<Friendship> friendships = new ArrayList<>();
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Friendship> cq = cb.createQuery(Friendship.class);
			Root<Friendship> root = cq.from(Friendship.class);
			
			Predicate whereReceiver = cb.equal(root.get("receiverId"), currUserId);
			Predicate whereApproved = cb.equal(root.get("status"), false);
			
			Predicate finalQuery = cb.and(whereReceiver, whereApproved);
			cq.where(finalQuery);
			Query q = s.createQuery(cq);
			
			friendships = q.getResultList();
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			s.close();
		}
		
		List<User> friends = new ArrayList<>();
		UserRepositoryImpl urimpl = new UserRepositoryImpl();
		for(Friendship friend : friendships) {
			friends.add(urimpl.findOneByUserId(friend.getSenderId()));
		}
		
		return friends;
	}

}
