package group4_sc2002_project;
import java.util.*;

public class MainMenu {
	public static Scanner s = new Scanner(System.in);
	public static User Login() {
		AuthenticationService service = new AuthenticationService();
		String userID, password;
		User check = null;
		
		while (true) {
			System.out.println("Key in your userID: (Key in # to go back)");
			userID = s.next();
			if (userID == "#") {
				break;
			}
			System.out.println("Key in your password:");
			password = s.next();
			check = service.loginUser(userID, password);
		}
		
		return check;
	}
	
	public static void viewProjects() {
		
	}
	
	public static void viewApplications() {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int choice;
		User user = null;
		do {
		System.out.println("Enter your choice:\n1) Login\n2) Projects\n3) Applications\n4) Exit");
		choice = s.nextInt();
		switch(choice) {
			case 1:
				user = Login();
				break;
			case 2:
				if (user == null) {
					System.out.println("Login to access!");
					break;
				}
				viewProjects();
				break;
			case 3:
				if (user == null) {
					System.out.println("Login to access!");
					break;
				}
				viewApplications();
				break;
		}
		} while (choice < 4);
		
	}
	
}
