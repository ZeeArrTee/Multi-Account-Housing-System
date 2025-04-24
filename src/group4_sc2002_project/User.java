package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;

public class User implements Account {
	private String name;
	private String userID;
	private String password = "password";
	private int age;
	private String maritalStatus;
	private List<String> roles;

	public User(String name, String userID, int age, String maritalStatus, String password, String role) {
		this.name = name;
		this.userID = userID;
		this.password = password;
		this.age = age;
		this.maritalStatus = maritalStatus;
		this.roles = new ArrayList<String>();
		this.roles.add(role);
	}

	public String getName() {
		return name;
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
		return roles;
	}

	public void modifyRole(String role) {
		roles.add(role);
	}

}
