package com.revature.repository;

import com.revature.models.Messages;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MessagesRepositoryImpl implements MessagesRepository{

	@Override
	public Messages getMyMessages(int myId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSeen(int receiverId, boolean seen) {
		// TODO Auto-generated method stub
		
	}

}
