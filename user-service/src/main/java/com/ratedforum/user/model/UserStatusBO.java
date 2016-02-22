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

@Entity
@Table(name = "user_status", indexes = { 
		@Index(name = "ix_name", columnList = "name", unique = true),
		@Index(name = "ix_is_selectable", columnList = "is_selectable") 
})
public class UserStatusBO implements Serializable{
	
	private static final long serialVersionUID = 1815696712482274042L;

	public enum UserStatus{
		ACTIVE, PENDING, SUSPENDED, BLACKLISTED;
	}
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "name", nullable = false)
	private UserStatus name;
	
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

	public UserStatus getName() {
		return name;
	}
	
	public void setName(UserStatus name) {
		this.name = name;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isSelectable ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		UserStatusBO other = (UserStatusBO) obj;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		if (isSelectable != other.isSelectable) return false;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "com.ratedforum.user.model.UserStatusBO [id=" + getId() + ", name=" + getName() + "]";
	}
}
