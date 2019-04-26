package com.MBAssignment.dao.impl;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.MBAssignment.dao.AbstractDao;
import com.MBAssignment.dao.ObjectDao;
import com.MBAssignment.model.Manager;

@Transactional
@Repository("objectDao")
public class ObjectDaoImpl extends AbstractDao implements ObjectDao{

	public void saveObject(Object entity) throws Exception {
		persist(entity);
	}

	public void deleteObject(Object entity) {
		delete(entity);
	}

	public void updateObject(Object entity) {
		saveOrUpdate(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObjectById(Class<T> entity, Serializable id) {
		return (T) getSession().get(entity, id);
	}

	@SuppressWarnings("unchecked")
	public <T> T listObject(Class<T> entity, String id) {
		Criteria criteria = getSession().createCriteria(entity).addOrder(Order.asc(id));
		return (T) criteria.list();
	}
	
	@Override
	public Manager managerLogin(Manager user) {
		Criteria criteria = getSession().createCriteria(Manager.class);
		Criterion name = Restrictions.eq("email", user.getEmail());
		Criterion pwd = Restrictions.eq("password", user.getPassword());
		LogicalExpression andExp = Restrictions.and(name, pwd);
		criteria.add(andExp);
		return (Manager) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getObjectByParam(Class<T> entity, String param, Object paramValue) {
		Criteria criteria = getSession().createCriteria(entity).add(Restrictions.eq(param, paramValue));
		return (T) criteria.uniqueResult();
	}

}
