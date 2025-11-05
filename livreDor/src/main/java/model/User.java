package model;

import java.sql.Date;

public class User {
	private String name;
	private String pwd;
	private Date dateCreation;
	public String getName() {
		return name;
	}
	
	public User() {
		super();
	}

	public User(String name, String pwd, Date dateCreation) {
		super();
		this.name = name;
		this.pwd = pwd;
		this.dateCreation = dateCreation;
	}






	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
}
