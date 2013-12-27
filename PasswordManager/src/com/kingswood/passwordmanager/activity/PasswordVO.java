package com.kingswood.passwordmanager.activity;

import java.io.Serializable;
import java.util.Date;

public class PasswordVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3810788930640706394L;

	private String name;

	private String username;

	private String password;

	private String description;
	
	private Date createdDate;
	
	private Date updatedDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString(){
		return name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
