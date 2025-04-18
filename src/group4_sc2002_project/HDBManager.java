package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HDBManager {
	private List<Project> managedProjects;

	HDBManager() {
		managedProjects = new ArrayList<Project>();
	}

	List<Project> getManagedProjects() {
		return managedProjects;
	}

	Boolean approveRejectWithdrawal(Application application) {
		Scanner input = new Scanner(System.in);
		System.out.println(application.getId() + application.getApplicant().getUserID()
				+ application.getApplicant().getAge() + application.getFlatType());
		System.out.println("Approve? (Y/N)");
		while (true) {
			String decision = input.next();
			if (decision.compareTo("Y") == 0) {
				application.setPendingWithdrawal(true);
				input.close();
				return true;

			} else if (decision.compareTo("N") == 0) {
				application.setPendingWithdrawal(false);
				input.close();
				return false;
			}
		}

	}
}
