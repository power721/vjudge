// ========================================================================
// 文件名：BaseService.java
//
// 文件说明：
//     本文件主要实现各模块service层的公共功能，包括增、删、改、计算
//
// ========================================================================
package vjudge.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("rawtypes")
public class BaseService implements IBaseService {

	public static BaseService baseService = new BaseService();
	static private Model baseDao;

	public void addOrModify(Object entity) {
		//BaseService.baseDao.addOrModify(entity);
	}

	public void delete(Object entity) {
		//BaseService.baseDao.delete(entity);
	}

	public void delete(Class entityClass, Serializable id) {
		//BaseService.baseDao.delete(entityClass, id);
	}

	public Object query(Class entityClass, Serializable id) {
		//return BaseService.baseDao.query(entityClass, id);
		return null;
	}

	public List query(String queryString) {
		//return BaseService.baseDao.query(queryString);
		return null;
	}
		
	public long count(String hql) {
		hql = hql.substring(hql.indexOf("from"));
		hql = "select count(*) " + hql;
		long re;
		//根据hql中是否有group by来选择计算方法
		if (hql.contains("group by")){
			re = this.query(hql).size();
		}else{
			re = ((Number)this.query(hql).get(0)).longValue();
		}
		return re;
	}
	
	public List list(String queryString, int FirstResult, int MaxResult) {
		//return BaseService.baseDao.query(queryString, FirstResult, MaxResult);
		return null;
	}

	public List list(String hql, Map parMap, int FirstResult, int MaxResult) {
		//return BaseService.baseDao.query(hql, parMap, FirstResult, MaxResult);
		return null;
	}
	
	public List query(String queryString, Map parMap) {
		//return BaseService.baseDao.query(queryString, parMap);
		return null;
	}
	
	public long count(String hql, Map parMap) {
		if(hql.contains("union")){
			String hql1 = "select count(*) from (" + hql + ")";
			long re;
			re = ((Number)this.query(hql1, parMap).get(0)).longValue();
			return re;
		}else{
			String hql1 = hql.substring(hql.indexOf("from"));
			hql1 = "select count(*) " + hql1;
			long re;
			//根据hql中是否有group by和distinct来选择计算方法
			if (hql.contains("group by")||hql.contains("distinct")){
				re = this.query(hql, parMap).size();
			}else{
				re = ((Number)this.query(hql1, parMap).get(0)).longValue();
			}
			return re;
		}
	}
	
	public void execute(String hql) {
		//BaseService.baseDao.execute(hql);
	}

	public void execute(String hql, Map parMap) {
		//BaseService.baseDao.execute(hql, parMap);
	}
	
	//public Session getSession() {
	//	return baseDao.createSession();
	//}

	//public void releaseSession(Session session) {
	//	baseDao.closeSession(session);
	//}
	
	//////////////////////////////////////////////

	
	public Model getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(Model baseDao) {
		BaseService.baseDao = baseDao;
	}


}
