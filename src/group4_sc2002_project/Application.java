package group4_sc2002_project;

public class Application {
	private int id;
	private Applicant applicant;
	private Project project;
	private ApplicationStatus status;
	private String flatType;
	private boolean pendingWithdrawal;

	public Application(int id, Applicant applicant, Project proj, String fType, ApplicationStatus stat) {
		this.id = id;
		this.applicant = applicant;
		this.project = proj;
		this.flatType = fType;
		this.status = stat;
		this.pendingWithdrawal = false;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus s) {
		status = s;
	}

	int getId() {
		return id;
	}

	void setFlatType(String flatType) {
		this.flatType = flatType;
	}

	String getFlatType() {
		return flatType;
	}

	void setPendingWithdrawal(boolean status) {
		pendingWithdrawal = status;
	}

	boolean isWithdrawalRequested() {
		return pendingWithdrawal;
	}

	Applicant getApplicant() {
		return applicant;
	}

	Project getProject() {
		return project;
	}
}
