package group4_sc2002_project;

abstract class User {
	private String userID;
	private String password = "password";
	private int age;
	private String maritalStatus;
	private String role;

	User(String id, String pw, int a, String marital, String r) {
		userID = id;
		password = pw;
		age = a;
		maritalStatus = marital;
		role = r;
	}

	public String getUserID() {
		return userID;
	}

	public String getPassword() {
		return password;
	}

	public void changePassword(String newPassword) {
		password = newPassword;
	}

	public int getAge() {
		return age;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public String getRole() {
		return role;
	}

}
