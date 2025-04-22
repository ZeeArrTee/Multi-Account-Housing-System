package group4_sc2002_project;

public class FlatBookingController {
	public void updateFlatAvailability(Project project, String flatType) {
		
	}
	
	public Application retrieveApplication(String nric) {
		return null;
	}
	
	public void updateApplicationStatus(Application application, ApplicationStatus status) {
		application.setStatus(status);
	}
	
	public void updateApplicantFlat(Application app, String flatType) {
		app.setFlatType(flatType);
	}
	
	public Receipt generateReceipt(Applicant applicant) {
		Receipt receipt = new Receipt();
		return receipt;
	}
}
