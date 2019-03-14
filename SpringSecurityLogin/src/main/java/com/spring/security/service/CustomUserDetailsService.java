package com.spring.security.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.security.model.RoleTab;
import com.spring.security.model.UserTab;
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException{
		UserTab user = userService.findBySso(ssoId);
		if(user == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(), 
				user.getState().equals("Active"),true,true,true,getGrantedAuthorities(user));
	}
	private List<GrantedAuthority> getGrantedAuthorities(UserTab user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(RoleTab role : user.getRole()) {
			System.out.println("User Profile : " + role);
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getType()));
		}
		System.out.println("Authorities : " + authorities);
		return authorities;
	}
}
