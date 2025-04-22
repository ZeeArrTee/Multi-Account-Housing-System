package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;

public class User implements Account
{
    private String userID;
	private String password = "password";
	private int age;
	private String maritalStatus;
	private List<String> role;

    public User(String userID, String password, int age, String maritalStatus, String role)
    {
        this.userID = userID;
        this.password = password;
        this.age = age;
        this.maritalStatus = maritalStatus;
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

    public void modifyRole(String role) {
		roles.add(role);
	}


}
