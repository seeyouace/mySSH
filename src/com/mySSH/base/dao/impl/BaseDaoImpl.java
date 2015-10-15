/**
 * BaseDaoImpl   基本DAO�?
 * @author Away Luo
 * @QQ 271353951
 * @date 2014-1-6 20:35:12	
 */

package com.mySSH.base.dao.impl;


import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session; 
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Component;

import com.mySSH.base.dao.IBaseDao;


@Component("baseDao")
public class BaseDaoImpl<T> implements IBaseDao<T>{

	private HibernateTemplate hibernateTemplate;  // Spring提供的hibernateTemplate�?
	private Class<T> entityClass;   //提供DAO类级别的泛型支持
	
	private final int BATCH_MAX_ROW = 10;
	
	/**
	 *  构�?方法获得子类Dao对应的泛型实体类
	 */
	public BaseDaoImpl(Class<T> entityClass){
        this.entityClass = entityClass;
    }

	public BaseDaoImpl() {		 
	}	
	
	//--------------------------------以下是CRUD常用方法-----------------------------
	/**
	 * 保存
	 */
	public void save(T entity) throws Exception{
		hibernateTemplate.save(entity);
		 
	}
	
	/**
	 * 批量保存
	 * @param array
	 * @return
	 */
	public int batchSave(final T[] array) throws Exception {
		 int affectedRow = (Integer) hibernateTemplate.executeWithNativeSession(
	        new HibernateCallback() {
	            public Object doInHibernate(Session session)
	                   throws HibernateException, SQLException {
	                for (int i = 0; i < array.length; ++i) {
	                    session.save(array[i]);
	                    if (i % BATCH_MAX_ROW == 0) {
	                        session.flush();
	                        session.clear();
	                    }
	                }
	                session.flush();
	                session.clear();
	                return array.length;
	            }
	    });
	    return affectedRow;
	}
	
	
	/**
	 * 保存或更�?
	 */
	public T saveOrUpdate(T entity) throws Exception{
		hibernateTemplate.saveOrUpdate(entity);
		return entity;
	}
	
	
	/**
     * 更新整个对象
     */
	public void update(T entity) throws Exception{
		hibernateTemplate.update(entity);		 
	}	
	
    /**
     * 部分更新
     * 两个方法，一个参数为hql�?另一个方法参数为hql与其问号对应的变�?
     */
	public void update(final String hql) throws Exception{
		update(hql, null);	 
	}
	public void update(final String hql, final Object[] values) throws Exception{
		hibernateTemplate.executeWithNativeSession(new HibernateCallback() {			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
				Query query = session.createQuery(hql);	
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				query.executeUpdate();					
				return null;
			}
		});		 
	}
	
	
	/**
	 * 删除对象
	 */
	public void delete(T entity) throws Exception{
		hibernateTemplate.delete(entity);
	}
	
	/**
	 * hql删除
	 * 两个方法，一个参数为hql�?另一个方法参数为hql与其问号对应的变�?
	 */
	public void deleteWithHql(final String hql) throws Exception{
		deleteWithHql(hql, null);  
	}
	public void deleteWithHql(final String hql, final Object[] values) throws Exception{
		hibernateTemplate.executeWithNativeSession(new HibernateCallback() {			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
				Query query = session.createQuery(hql);	
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				query.executeUpdate();					
				return null;
			}
		});	  
	}
	
	/**
	 * 删除�?��集合
	 */
	public void deleteAll(Collection entities) throws Exception{
		hibernateTemplate.deleteAll(entities);
	}
	
	//----------------------------以下是返回一个实体类的方�?-----------------------
	/**
	 * 根据id查询
	 * load()方法会使用二级缓存，而get()方法在一级缓存没有找到会直接查询数据库，不会去二级缓存中查找�?
	 * load只拿id，根据后续要求再查对象全部属性； get�?��性加载全部属�?
	 */
	public T getOne(int id) throws Exception{
		System.out.println(entityClass);
		return (T)hibernateTemplate.get(entityClass, id);
	}
	public T loadOne(int id){ 
		return  (T) hibernateTemplate.load(entityClass, id);
	}
	
	
	/**
	 * findOne()返回泛型指定的对象；
	 * 两个方法，一个参数为hql�?另一个方法参数为hql与其问号对应的变�?
	 */
	public T findOne(final String hql){		
		return findOne(hql,null);
	} 
	public T findOne(final String hql, final Object[] values){
		@SuppressWarnings("unchecked")
		T entity = (T)hibernateTemplate.executeWithNativeSession(new HibernateCallback() {			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
			SQLException {	
				Query query = session.createQuery(hql);	
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}	
				return  query.setMaxResults(1).uniqueResult(); 	
			}
		});
		return entity;
	} 
	
	/**
	 * findOneWithAny()返回Object对象，可用hql中指定的VO来转�?
	 * 两个方法，一个参数为hql�?另一个方法参数为hql与其问号对应的变�?
	 */
	public Object findOneWithAny(final String hql){				
		return findOneWithAny(hql,null);
	} 
	public Object findOneWithAny(final String hql, final Object[] values){		
		Object entity = (Object)hibernateTemplate.executeWithNativeSession(new HibernateCallback() {			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {	
				Query query = session.createQuery(hql);	
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}				
				return query.setMaxResults(1).uniqueResult(); 
			}
		});
		return entity;
	} 
	
	//---------------------------以下是数量统计的方法----------------------------
	
	/**
	 * findCount 返回long类型，表示查询到的记录�?数， hql中需要包含count(*)
	 * 两个方法，一个参数为hql�?另一个方法参数为hql与其问号对应的变�?
	 */
	public long findCount(final String hql){		
		return findCount(hql,null);
	}
	public long findCount(final String hql, final Object[] values){
		Object object = hibernateTemplate.executeWithNativeSession(new HibernateCallback() {			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {		
				Query query = session.createQuery(hql);	
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.setMaxResults(1).uniqueResult(); 
			}
		});	
		Long entity = 0L;
		if(object != null) {
			entity = (Long)object;
		}
		return entity;
	}
	 
	
	//--------------------------------以下是返回list 的方�?----------------------------
	/**
	 * hql语句查询，返回List对象
	 * @param hql	
	 * @return List
	 */
	public List findList(String hql){
		return hibernateTemplate.find(hql);
	}
	
	/**
	 * findList  HQL语句查询，返回List对象
	 * @param hql,Object[]  	(hql中的变量以？代替，由values这个数组按顺序补�?
	 * @return List
	 */
	public List findList(String hql, Object[] values) {
		return hibernateTemplate.find(hql, values);
	}	 
	
	//--------------------------- 以下是多条查�?-------------------------------------------
	/**  findMore 多条查询
	 * @param  begin(查询起始条目)
	 * @param  size(查询数目)
	 * @param  hql(查询语句)
	 * @return List
	 */
	public List findMore(final int begin, final int size, final String hql) {
		return findMore(begin, size, hql, null);
	}
	
	
	/**  findMore 多条查询
	 * @param  begin(查询起始条目)
	 * @param  size(查询数目)
	 * @param  hql(包含问号的查询语�?
	 * @param  values(变量数组)
	 * @return List
	 */
	public List findMore(final int begin, final int size, final String hql, final Object[] values) {
		List list = (List)hibernateTemplate.executeWithNativeSession(new HibernateCallback() {			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);	
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				query.setFirstResult(begin);
				query.setMaxResults(size);	
				
				return query.list();  //返回多条查询对象
			}
		});	 
		return list;
	}
	
	
	
	public List findListWithDate(final String hql, final Object[] values) {
		List list = (List)hibernateTemplate.executeWithNativeSession(new HibernateCallback() {			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);	
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						if(values[i] instanceof Date) {
							query.setDate(i, (Date) values[i]);
						} else {
							query.setParameter(i, values[i]);
						}
					}
				} 
				
				return query.list();  //返回多条查询对象
			}
		});	 
		return list;
	}
	 
	//--------------------------- HibernateTemplate的get/set方法------------------------------------
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public int getBatchMaxRow() {
		return BATCH_MAX_ROW;
	}

}
