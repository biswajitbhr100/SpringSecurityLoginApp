package com.spring.security.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.security.repository.RoleDao;
import com.spring.security.model.RoleTab;
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDao roleDao;
	public List<RoleTab> findAll(){
		return roleDao.findAll();
	}
	public RoleTab findByType(String type) {
		return roleDao.findByType(type);
	}
	public RoleTab findById(long id) {
		return roleDao.findById(id);
	}
}
