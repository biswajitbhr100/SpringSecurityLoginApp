package com.spring.security.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.spring.security.model.RoleTab;
import com.spring.security.service.RoleService;
@Component
public class RoleToUserConverter implements Converter<Object,RoleTab> {
	@Autowired
	private RoleService roleService;
	public RoleTab convert(Object element) {
		Long id = Long.parseLong((String)element);
		RoleTab role = roleService.findById(id);
		return role;
	}
	/*public RoleTab convert(Object element) {
		String type = (String)element;
		RoleTab role = roleService.findByType(type);
		return role;
	}*/
}
