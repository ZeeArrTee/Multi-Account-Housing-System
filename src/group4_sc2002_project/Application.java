package group4_sc2002_project;

public class Application {
	private Applicant applicant;
	private Project project;
	private ApplicationStatus status;
	private Flat bookedFlat;
	private boolean pendingWithdrawal;

	public Application() {
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus s) {
		status = s;
	}

	void bookFlat(Flat flat) {
		bookedFlat = flat;
	}

	Flat getBookedFlat() {
		return bookedFlat;
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
