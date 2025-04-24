package group4_sc2002_project;

public abstract class ApplicationView {
	private Project project;
	private User user;
	private Application application;

	ApplicationView(Project project, User user) {
		this.project = project;
		this.user = user;
		this.application = null;
	}

	public void requestWithdrawal() {
		application.setPendingWithdrawal(true);
	}

	public Applicant applyForProject(String flatType) {
		Applicant applicant = new Applicant(user.getName(), user.getUserID(), user.getAge(),
				user.getMaritalStatus(), user.getPassword());
		if (isEligible(user, flatType)) {
			application = new Application(applicant, project, flatType, ApplicationStatus.Pending);
		}
		applicant.setApplication(application);
		project.addApplication(application);
		return applicant;
	}

	public static boolean isEligible(User user, String flatType) {
		if (user.getMaritalStatus() == "Single" && user.getAge() >= 35) {
			return flatType == "2-Room";
		} else if (user.getMaritalStatus() == "Married" && user.getAge() >= 21) {
			return true;
		} else {
			return false;
		}
	}
}
