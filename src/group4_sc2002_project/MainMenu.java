package group4_sc2002_project;
import java.util.*;

public class MainMenu {
	public static Scanner s = new Scanner(System.in);
	public static User Login() {
		AuthenticationService service = new AuthenticationService();
		String userID, password;
		User check = null;
		int choice;
		do {
		System.out.println("Enter your choice:\n1) Login\n2) Create User\n3) Reset Password\n4) Exit");
		choice = s.nextInt();
		switch(choice) {
			case 1:
				while (true) {
					System.out.println("Key in your userID: (Key in # to go back)");
					userID = s.next();
					if (userID.equals("#")) {
						break;
					}
					System.out.println("Key in your password:");
					password = s.next();
					check = service.loginUser(userID, password);
				}
				break;
			case 2:
				List<String> married = new ArrayList<String>(2);
				married.add("Married");
				married.add("Single");
				String name = "\0";
				String maritalStatus = "\0";
				System.out.println("Key in your Name: (Key in # to go back)");
				name = s.next();
				if (name.equals("#")) {
					break;
				}
				System.out.println("Key in your userID: ");
				userID = s.next();
				System.out.println("Key in your password:");
				password = s.next();
				while (!married.contains(maritalStatus)) {
					System.out.println("Key in your marital status(Single/Married):");
					maritalStatus = s.next();
					if (!married.contains(maritalStatus)){
						System.out.println("Invalid marital status!");
					}
				}
				service.createUser(name, userID, password, choice, maritalStatus, "User");
				break;
			case 3:
				System.out.println("Key in your userID: (Key in # to go back)");
				userID = s.next();
				if (userID.equals("#")) {
					break;
				}
				System.out.println("Key in your new password:");
				password = s.next();
				service.changePassword(userID, password);
				break;
			case 4:
				System.out.println("Returning to main menu...");
				break;
		}
		} while (choice < 4);
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
			case 4:
				System.out.println("Exiting...");
				break;
		}
		} while (choice < 4);
		
	}
	
}
