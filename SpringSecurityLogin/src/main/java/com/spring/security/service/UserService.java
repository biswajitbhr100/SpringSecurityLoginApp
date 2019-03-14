package com.spring.security.service;
import java.util.List;
import com.spring.security.model.UserTab;
public interface UserService {
	void save(UserTab user);
	void deleteUserBySso(String ssoId);
	void update(UserTab user);
	UserTab findById(long id);
	UserTab findBySso(String sso);
	List<UserTab> getAllUsers();
}
