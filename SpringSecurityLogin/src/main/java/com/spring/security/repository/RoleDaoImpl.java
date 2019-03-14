package com.spring.security.repository;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.spring.security.model.RoleTab;
@Repository("roleRepository")
public class RoleDaoImpl extends AbstractDao<Long,RoleTab> implements RoleDao{
	@SuppressWarnings("unchecked")
	public List<RoleTab> findAll(){
		Criteria cr = createEntityCriteria();
		cr.addOrder(Order.asc("type"));
		return (List<RoleTab>)cr.list();
	}
	public RoleTab findByType(String type) {
		Criteria cr = createEntityCriteria();
		cr.add(Restrictions.eq("type", type));
		return (RoleTab)cr.uniqueResult();
	}
	public RoleTab findById(long id) {
		return getByKey(id);
	}
}
