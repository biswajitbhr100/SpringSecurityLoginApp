package com.spring.security.controller;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.spring.security.model.UserTab;
import com.spring.security.model.RoleTab;
import com.spring.security.service.UserService;
import com.spring.security.service.RoleService;
@Controller("userController")
public class RegisterController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String register(ModelMap model) {
		UserTab user = new UserTab();
		model.addAttribute("user",user);
		return "userRegistration";
	}
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute("user")UserTab user,BindingResult result,ModelMap model) {
		if(result.hasErrors()) {
			System.out.println("Found Errors in the UserForm");
			return "userRegistration";
		}
		Set<RoleTab> role = new HashSet<RoleTab>();
		role.add(roleService.findByType("USER"));
		user.setRole(role);
		userService.save(user);
		model.addAttribute("success","User : " + user.getName() + " Sucessfully Registered");
		return "success";
	}
}
