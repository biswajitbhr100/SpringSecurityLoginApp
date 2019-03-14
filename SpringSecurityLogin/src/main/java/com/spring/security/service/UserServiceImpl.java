package com.spring.security.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.spring.security.repository.UserDao;
import com.spring.security.model.UserTab;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public void save(UserTab user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.save(user);
	}
	public void deleteUserBySso(String ssoId) {
		userDao.deleteUserBySso(ssoId);
	}
	public void update(UserTab user) {
		UserTab userEntity = userDao.findById(user.getId());
		if(userEntity != null) {
			userEntity.setSsoId(user.getSsoId());
			if(!user.getPassword().equals(userEntity.getPassword())) {
				userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			userEntity.setName(user.getName());
			userEntity.setMail(user.getMail());
			userEntity.setRole(user.getRole());
		}
	}
	public UserTab findById(long id) {
		return userDao.findById(id);
	}
	public UserTab findBySso(String sso) {
		return userDao.findBySSO(sso);
	}
	public List<UserTab> getAllUsers(){
		return userDao.getAllUsers();
	}
}
