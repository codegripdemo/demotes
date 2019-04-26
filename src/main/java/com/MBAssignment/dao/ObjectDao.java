package com.MBAssignment.dao;

import java.io.Serializable;

import com.MBAssignment.model.Manager;

public interface ObjectDao {

	public void saveObject(Object entity) throws Exception;
	public void deleteObject(Object entity);
	public void updateObject(Object entity);
	public <T> T getObjectById(Class<T> entity, Serializable id);
	public <T> T listObject(Class<T> entity, String id);
	public <T> T getObjectByParam(Class<T> entity, String param, Object obj);
	public Manager managerLogin(Manager manager);
}
