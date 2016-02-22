package com.ratedforum.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role", 
	indexes = { @Index(name = "ix_name", columnList = "name", unique = true)
})
public class RoleBO implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = -2934253177419534374L;
	
	public enum Role{
		ROLE_ADMIN, ROLE_CLUB_ADMIN, ROLE_CAPTAIN, ROLE_PLAYER;
	}
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "name", nullable = false)
	private Role name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Type(type="true_false")
	@Column(name = "is_selectable", nullable = false)
	private boolean isSelectable;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSelectable() {
		return isSelectable;
	}

	public void setSelectable(boolean isSelectable) {
		this.isSelectable = isSelectable;
	}
	
	public Role getName() {
		return name;
	}

	public void setName(Role name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name.name();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		RoleBO other = (RoleBO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "com.ratedforum.user.model.RoleBO [id=" + id + ", name=" + name + "]";
	}
}
