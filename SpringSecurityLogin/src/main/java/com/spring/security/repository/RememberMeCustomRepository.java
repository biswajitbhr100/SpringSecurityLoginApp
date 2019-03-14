package com.spring.security.repository;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.spring.security.model.RememberMe;
@Repository("rememberMeDao")
@Transactional
public class RememberMeCustomRepository extends AbstractDao<String,RememberMe> implements PersistentTokenRepository{
	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		RememberMe persistentLogin = new RememberMe();
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setUsername(token.getUsername());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setLast_used(token.getDate());
		persist(persistentLogin);
	}
	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		try {
			Criteria crit = createEntityCriteria();
			crit.add(Restrictions.eq("series", seriesId));
			RememberMe persistentLogin = (RememberMe)crit.uniqueResult();
			return new PersistentRememberMeToken(persistentLogin.getSeries(),persistentLogin.getUsername(),persistentLogin.getToken(),persistentLogin.getLast_used());
		}catch(Exception e) {
			System.out.println("Token Not Found");
			return null;
		}
	}
	@Override
	public void removeUserTokens(String username) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		RememberMe persistentLogin = (RememberMe)crit.uniqueResult();
		if(persistentLogin != null) {
			delete(persistentLogin);
		}
	}
	@Override
	public void updateToken(String seriesId,String tokenValue,Date lastUsed) {
		RememberMe persistentLogin = getByKey(seriesId);
		persistentLogin.setToken(tokenValue);
		persistentLogin.setLast_used(lastUsed);
		update(persistentLogin);
	}
}
