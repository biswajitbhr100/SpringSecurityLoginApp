package com.spring.security.repository;
import java.util.List;

import com.spring.security.model.RoleTab;
public interface RoleDao {
	List<RoleTab> findAll();
	RoleTab findByType(String type);
	RoleTab findById(long id);
}
