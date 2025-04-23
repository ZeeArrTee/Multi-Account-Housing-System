package group4_sc2002_project;

public class ApplicantEnquiryService implements ApplicantEnquiryView {
	// AdminEnquiryController projectEnquiryController;
	Project project;
	Applicant applicant;

	ApplicantEnquiryService(Project project, Applicant applicant) {
		this.project = project;
		this.applicant = applicant;
	}
	
	@Override
	public void submitEnquiry() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void editEnquiry() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteEnquiry() {
		// TODO Auto-generated method stub
		
	}
	
	public void submitEnquiry(String message) {
		Enquiry enquiry = new Enquiry(project.getEnquiryCount(), applicant, project, message);
		project.addEnquiry(enquiry);
		project.incrementEnquiryCount();
	}

	public void editEnquiry(int enquiryId, String message) {
		Enquiry enquiry = project.getEnquiry(enquiryId);
		enquiry.setContent(message);
	}

	public void deleteEnquiry(int enquiryId) {
		project.removeEnquiry(enquiryId);
	}
}
