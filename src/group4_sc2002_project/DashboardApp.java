package group4_sc2002_project;
import java.util.*;

public class DashboardApp {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		// TODO Auto-generated method stub
		int choice;
		System.out.println("Enter your choice:\n 1) Login\n 2)Projects\n 3) Applications\n 4) Exit");
		choice = s.nextInt();
		switch(choice) {
			case 1:
				Login();
			case 2: 
				viewProjects();
			case 3:
				viewApplication();
			case 4:
				break;
		}
			
			
		
	}

}
