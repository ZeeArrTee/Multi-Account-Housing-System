package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;

abstract class Account {
	private String userID;
	private String password = "password";
	private int age;
	private String maritalStatus;
	private List<String> role;

	public Account(String id, String pw, int a, String marital, String role) {
		this.userID = id;
		this.password = pw;
		this.age = a;
		this.maritalStatus = marital;
		this.role = new ArrayList<String>();
		this.role.add("User");
		this.role.add(role);
	}

	public String getUserID() {
		return userID;
	}

	public String getPassword() {
		return password;
	}

	public void changePassword(String newPwd) {
		password = newPwd;
	}

	public int getAge() {
		return age;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public List<String> getRole() {
		return role;
	}

}
