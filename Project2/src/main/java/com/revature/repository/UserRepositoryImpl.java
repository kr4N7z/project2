package com.revature.repository;

import java.sql.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.postgresql.geometric.PGpoint;

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
		}finally {
			s.close();
		}
		
		
		
		
		return user;
		// TODO Auto-generated method stub
		
	}
}
