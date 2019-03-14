package com.spring.security.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import java.util.Set;
import java.util.HashSet;
@Entity
@Table(name = "USER_TAB")
public class UserTab implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5998774546045044347L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	@Column(name = "USERNAME",nullable = false,unique = true)
	private String ssoId;
	@Column(name = "PASSWORD",nullable = false)
	private String password;
	@Column(name = "NAME",nullable = false)
	private String name;
	@Column(name = "EMAIL",nullable = false)
	private String mail;
	@Column(name = "STATE",nullable = false)
	private String state = UserStateType.ACTIVE.getUserStateType();
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_TAB_ROLE_TAB_PROFILE",joinColumns = {@JoinColumn(name = "USER_ID")}
	,inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
	private Set<RoleTab> role = new HashSet<RoleTab>();
	
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	public String getSsoId() {
		return ssoId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMail() {
		return mail;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setRole(Set<RoleTab> role) {
		this.role = role;
	}
	public Set<RoleTab> getRole(){
		return role;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int)id;
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof UserTab))
			return false;
		UserTab u = (UserTab)obj;
		if(id != u.id)
			return false;
		if(ssoId == null) {
			if(u.ssoId != null)
				return false;
		}else if(!ssoId.equals(u.ssoId)) 
			return false;
		return true;
	}
}
