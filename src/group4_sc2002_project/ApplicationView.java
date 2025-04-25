package group4_sc2002_project;

public class ApplicationView {
	private Project project;
	private User user;
	private Application application;

	ApplicationView(Project project, User user) {
		this.project = project;
		this.user = user;
		this.application = null;
		if (user instanceof Applicant) {
			application = ((Applicant) user).getApplication();
		}

	}

	public void requestWithdrawal() {
		application.setPendingWithdrawal(true);
	}

	public Applicant applyForProject(String flatType) {
		Applicant applicant = new Applicant(user.getName(), user.getUserID(), user.getAge(), user.getMaritalStatus(),
				user.getPassword());
		if (isEligible(user, flatType)) {
			application = new Application(applicant, project, flatType, ApplicationStatus.Pending);
			applicant.setApplication(application);
			project.addApplication(application);
			ApplicationRepository.getApplications().add(application);
			user = applicant;
			return applicant;
		}

		return null;
	}

	public static void displayApplication(Application app) {
		System.out.println("Project: " + app.getProject().getProjectName() + " Flat Type: " + app.getFlatType()
				+ " Pending Withdrawal: " + app.getPendingWithdrawal() + " Status: " + app.getStatus());
	}

	public static boolean isEligible(User user, String flatType) {
		if (user.getMaritalStatus().compareTo("Single") == 0 && user.getAge() >= 35) {
			return flatType.compareTo("2-Room") == 0;
		} else if (user.getMaritalStatus().compareTo("Married") == 0 && user.getAge() >= 21) {
			return true;
		} else {
			return false;
		}
	}
}
