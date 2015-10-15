package com.mySSH.base.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;

public interface IBaseDao<T> {

	public void save(T entity) throws Exception;
	public int batchSave(final T[] array) throws Exception;
	
	public T saveOrUpdate(T entity) throws Exception;
	
	public void update(T entity) throws Exception;
	public void update(String hql) throws Exception;
	public void update(final String hql, final Object[] values) throws Exception;
		
	public void delete(T entity) throws Exception;
	public void deleteWithHql(final String hql) throws Exception;
	public void deleteWithHql(final String hql, final Object[] values) throws Exception;
	public void deleteAll(Collection entities) throws Exception;
	
	public T getOne(int id) throws Exception;
	public T loadOne(int id)  throws Exception;  
	
	public T findOne(final String hql);
	public T findOne(final String hql, final Object[] values);
	 
	public Object findOneWithAny(final String hql);
	public Object findOneWithAny(final String hql, final Object[] values);
	
	public List findMore(final int begin, final int size, final String hql);
	public List findMore(final int begin, final int size, final String hql, final Object[] values);
	
	public long findCount(final String hql);
	public long findCount(final String hql, final Object[] values);
	
	public List findList(String hql);
	public List findList(String hql, Object[] values);
	 
	public List findListWithDate(final String hql, final Object[] values);
}
