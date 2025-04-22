package group4_sc2002_project;

import java.util.List;

interface Account {

	public String getUserID();

	public String getPassword();

	public void changePassword(String newPwd);

	public int getAge();

	public String getMaritalStatus();

	public List<String> getRole();

}
