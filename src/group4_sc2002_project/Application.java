package group4_sc2002_project;

public class Application {
	private Applicant applicant;
	private Project project;
	private ApplicationStatus status;
	private String flatType;
	private boolean withdrawalRequested;

	public Application(Applicant applicant, Project proj, String fType, ApplicationStatus stat) {
		this.applicant = applicant;
		this.project = proj;
		this.flatType = fType;
		this.status = stat;
		this.withdrawalRequested = false;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public Project getProject() {
		return project;
	}

	public String getFlatType() {
		return flatType;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus s) {
		status = s;
	}

	public void toggleWithdrawal()
	{
		toggleWithdrawal = !toggleWithdrawal;
	}

	void setFlatType(String flatType) {
		this.flatType = flatType;
	}

	void setPendingWithdrawal(boolean status) {
		pendingWithdrawal = status;
	}

	boolean isWithdrawalRequested() {
		return pendingWithdrawal;
	}

}
