package com.ratedforum.uaa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user", indexes = { 
		@Index(name = "ix_username", columnList = "username", unique = true),
		@Index(name = "ix_user_status_id", columnList = "user_status_id") 
})
public class UserBO implements Serializable {

	private static final long serialVersionUID = -5889526109417397633L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleBO> roles = new HashSet<RoleBO>();
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_status_id", referencedColumnName = "id", nullable = false)
	private UserStatusBO userStatusBO;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleBO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleBO> roles) {
		this.roles = roles;
	}

	public UserStatusBO getUserStatusBO() {
		return userStatusBO;
	}

	public void setUserStatusBO(UserStatusBO userStatusBO) {
		this.userStatusBO = userStatusBO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBO other = (UserBO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "com.ratedforum.user.model.UserBO [id=" + id + ", username=" + username + "]";
	}
}
