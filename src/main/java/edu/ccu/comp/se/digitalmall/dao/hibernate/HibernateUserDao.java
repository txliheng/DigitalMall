package edu.ccu.comp.se.digitalmall.dao.hibernate;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import edu.ccu.comp.se.digitalmall.dao.IUserDao;
import edu.ccu.comp.se.digitalmall.model.User;

@Repository
public class HibernateUserDao implements IUserDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public int save(User user) {
		String result = this.hibernateTemplate.save(user).toString();
		if(result!=null){
			return Integer.parseInt(result);
		}
		return 0;
	}

	
	@Override
	public int login(String username, String password) {
		final String hql = getCountAllHql() + " where (mobile = ? or email=? or nick = ?) and password = ?";
		Number count =  (Number) unique(hql,username,username,username,password);
		
		return count.intValue();
	}


	@Override
	public int findByUsername(String username) {
		final String hql = getCountAllHql() + " where mobile = ? or email = ? or nick = ? ";
		Number count =  (Number) unique(hql,username,username,username);
	
		return count.intValue();

	}	

	@Override
	public int findByNick(String nick) {
		final String hql = getCountAllHql() + " where nick = ?";
		Number count =  (Number) unique(hql,nick);
		return count.intValue();

	}
    private final String HQL_COUNT_ALL = " select count(*) from User " ;

    protected String getCountAllHql() {
    	return HQL_COUNT_ALL;
    }
    private final String HQL_LIST_ALL = "from User";
    protected String getListAllHql() {
        return HQL_LIST_ALL;
    }
    public int countAll() {
        Number total = unique(getCountAllHql());
        return total.intValue();
    }
    @SuppressWarnings("unchecked")
    protected <T> T unique(final String hql, final Object... paramlist) {
        return this.hibernateTemplate.execute(new HibernateCallback<T>() {

            
			public T doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                if (paramlist != null) {
                    for (int i = 0; i < paramlist.length; i++) {
                        query.setParameter(i, paramlist[i]);
                    }
                }
                return (T) query.setMaxResults(1).uniqueResult();
            }
        });
    }


	@Override
	public User findUser(String username,String password) {
		final String hql = getListAllHql() + " where (mobile = ? or email=? or nick = ?) and password = ?";
		return  unique(hql,username,username,username,password);
	
	}
}
