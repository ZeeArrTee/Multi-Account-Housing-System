package group4_sc2002_project;

public class AdminEnquiryController extends EnquiryController {
	Application application;

	AdminEnquiryController(Application application) {
		super(application.getProject());
		this.application = application;

	}

	Enquiry getEnquiry(int id) {
		return project.getEnquiry(id);
	}

	void replyEnquiry(Enquiry enquiry, String reply) {

	}

}