package group4_sc2002_project;

public class AdminEnquiryController extends EnquiryController {
	private Application application;

	AdminEnquiryController(Application application) {
		super(application.getProject());
		this.application = application;

	}

	public Enquiry getEnquiry(int id) {
		return project.getEnquiry(id);
	}

	public void replyEnquiry(Enquiry enquiry, String reply) {

	}

}