package com.spring.security.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.spring.security.model.RoleTab;
import com.spring.security.model.UserTab;
import com.spring.security.service.RoleService;
import com.spring.security.service.UserService;
@Controller
@SessionAttributes("roles")
public class LoginController {
	private static String type = "";
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	@Autowired
	PersistentTokenBasedRememberMeServices rememberMeServices;
	@RequestMapping(value = {"/","/home"},method = RequestMethod.GET)
	public String home(ModelMap model) {
		//System.out.println("Inside Home");
		model.addAttribute("welcome", "Welcome Spring Security");
		return "welcome";
	}
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login() {
		if(isCurrentAuthenticationAnonymous()) {
			return "login";
		}else {
			return "redirect:/welcome";
		}
	}
	@RequestMapping(value = "/registration",method = RequestMethod.GET)
	public String userRegistration(ModelMap model) {
		UserTab user = new UserTab();
		model.addAttribute("user",user);
		return "userRegistration";
	}
	@RequestMapping(value = "/registration",method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute("user")UserTab user,BindingResult result,ModelMap model) {
		if(result.hasErrors()) {
			System.out.println("Found Errors in the UserForm");
			return "userRegistration";
		}
		userService.save(user);
		model.addAttribute("success","User : " + user.getName() + " Sucessfully Registered");
		return "success";
	}
	@RequestMapping(value = "/users",method = RequestMethod.GET)
	public String getAllUsers(ModelMap model) {
		List<UserTab> users = userService.getAllUsers();
		model.addAttribute("users",users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "users";
	}
	@RequestMapping(value = "/user",method = RequestMethod.GET)
	public String user(ModelMap model) {
		type = "user";
		model.addAttribute("type",type);
		model.addAttribute("user", getPrincipal());
		return "user";
	}
	@RequestMapping(value = {"/editUser/{ssoId}"},method = RequestMethod.GET)
	public String edit(@PathVariable String ssoId,ModelMap model) {
		UserTab user = userService.findBySso(ssoId);
		model.addAttribute("user",user);
		model.addAttribute("edit",true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "userRegistration";
	}
	@RequestMapping(value = {"/editUser/{ssoId}"},method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("user")UserTab user,
			BindingResult result,ModelMap model,@PathVariable String ssoId) {
		if(result.hasErrors()) {
			return "userRegistration";
		}
		//System.out.println(user);
		userService.update(user);
		model.addAttribute("success", user.getName() + " Updated Successfully..!");
		model.addAttribute("loggedinuser", getPrincipal());
		return "success";
	}
	@RequestMapping(value = "/deleteUser/{ssoId}",method = RequestMethod.GET)
	public String delete(@PathVariable String ssoId) {
		userService.deleteUserBySso(ssoId);
		return "redirect:/users";
	}
	@RequestMapping(value = "/admin",method = RequestMethod.GET)
	public String admin(ModelMap model) {
		type = "admin";
		model.addAttribute("type", type);
		model.addAttribute("user", getPrincipal());
		return "admin";
	}
	@RequestMapping(value = "/db",method = RequestMethod.GET)
	public String dba(ModelMap model) {
		type = "db";
		model.addAttribute("user", getPrincipal());
		return "dba";
	}
	@RequestMapping(value = "/access_denied",method = RequestMethod.GET)
	public String accessDenied(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "denied";
	}
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			//System.out.println("Auth : " + auth);
			rememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}
	
	public String getPrincipal() {
		String user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			user = ((UserDetails)principal).getUsername();
		}else {
			user = principal.toString();
		}
		return user;
	}
	@ModelAttribute("roles")
	public List<RoleTab> initializeRoles(){
		return roleService.findAll();
	}
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}
}
