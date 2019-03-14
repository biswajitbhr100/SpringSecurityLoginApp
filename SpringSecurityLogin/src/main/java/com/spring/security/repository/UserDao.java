package com.spring.security.repository;
import java.util.List;
import com.spring.security.model.UserTab;
public interface UserDao {
	void save(UserTab user);
	void deleteUserBySso(String ssoId);
	UserTab findById(long id);
	UserTab findBySSO(String sso);
	List<UserTab> getAllUsers();
}
