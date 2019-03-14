package com.spring.security.repository;
import java.util.List;
import com.spring.security.model.UserTab;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
@Repository("userRepository")
public class UserDaoImpl extends AbstractDao<Long,UserTab> implements UserDao{
	public void save(UserTab user) {
		persist(user);
	}
	public void deleteUserBySso(String ssoId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", ssoId));
		UserTab user = (UserTab)crit.uniqueResult();
		delete(user);
	}
	public UserTab findById(long id) {
		return getByKey(id);
	}
	public UserTab findBySSO(String sso) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ssoId", sso));
		return (UserTab)criteria.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<UserTab> getAllUsers(){
		Criteria crit = createEntityCriteria().addOrder(Order.asc("name"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<UserTab>)crit.list();
	}
}
