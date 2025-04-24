package group4_sc2002_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class OfficerService extends OfficerDisplay {
	private Officer officer;

	OfficerService(Officer officer) {
		super(officer);
		this.officer = officer;

		// TODO Auto-generated constructor stub
	}

	public static void createRegistration(User user, Project project) {
		ArrayList<User> temp = ManagerService.getRegistrations().get(project);
		temp.add(user);
		ManagerService.getRegistrations().put(project, temp);
	}

	public boolean canRegister(Officer officer, Project project) {
		if (officer.getHandledProject().isWithinApplicationPeriod(project.getOpeningDate())) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void displayOfficerRegistrationStatus() {
		// TODO Auto-generated method stub
		System.out.println("Officer Registration Status: " + this.officer.getRegistrationStatus());
	}

	@Override
	public void displayProjectDetails(Project project) {
		Map<String, Integer> units = project.getUnits();
		System.out.print("Name: " + project.getProjectName() + " Neighbourhood: " + project.getNeighbourhood()
				+ " Opening Date: " + project.getOpeningDate() + " Closing Date: " + project.getClosingDate()
				+ " Remaining Officer Slots: " + project.getOfficerSlots() + " Flats "
				+ units.keySet().stream().map(key -> key + ": " + units.get(key)));

	}

	public Receipt generateReceipt(Application application) {
		Receipt receipt = new Receipt(application.getApplicant(), application.getProject(), application.getFlatType(),
				LocalDate.now());
		return receipt;
	}
}
