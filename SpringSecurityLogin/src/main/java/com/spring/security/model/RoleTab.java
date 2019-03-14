package com.spring.security.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Entity
@Table(name = "ROLE_TAB")
public class RoleTab implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1727549391852671985L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	@Column(name = "TYPE",nullable = false,unique = true)
	private String type = UserRoleType.USER.getRoleType();
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int)id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		RoleTab u = (RoleTab)obj;
		if(id != u.id)
			return false;
		if(type == null) {
			if(u.type != null)
				return false;
		}else if(!type.equals(u.type)) 
			return false;
		return true;
	}
}
