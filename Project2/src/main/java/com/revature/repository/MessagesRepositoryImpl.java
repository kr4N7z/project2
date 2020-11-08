package com.revature.repository;

import com.revature.models.Messages;
import com.revature.models.User;
import com.revature.utility.HibernateSessionFactory;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class MessagesRepositoryImpl implements MessagesRepository{

	@Override
	public List<Messages> getMyMessages(int myId) {
		Session s = null;
		Transaction tx = null;
		List<Messages> messages  = null;

		try {
			s = HibernateSessionFactory.getSession();
			tx=s.beginTransaction();
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Messages> cq = cb.createQuery(Messages.class);
			Root<Messages> root = cq.from(Messages.class);
			
			Predicate whereSender = cb.equal(root.get("senderId"), myId);
			
			Predicate whereReceiver = cb.equal(root.get("receivedId"), myId);
			
			Predicate finalQuery = cb.or(whereSender,whereReceiver);
			
			cq.where(finalQuery);
			
			Query q = s.createQuery(cq);
			
			messages = q.getResultList();
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	@Override
	public void sendMessage(Messages newMessage) {
		Session s = null;
		Transaction tx = null;

		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();

			s.save(newMessage);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
		
	}
	
	@Override
	public void updateSeen(int receiverId, boolean seen) {
		// TODO Auto-generated method stub
		
	}

	

}
