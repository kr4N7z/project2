package com.revature.repository;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.postgresql.geometric.PGpoint;

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
}
