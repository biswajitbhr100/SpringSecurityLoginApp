package com.spring.security.service;
import java.util.List;
import com.spring.security.model.RoleTab;
public interface RoleService {
	List<RoleTab> findAll();
	RoleTab findByType(String type);
	RoleTab findById(long id);
}
