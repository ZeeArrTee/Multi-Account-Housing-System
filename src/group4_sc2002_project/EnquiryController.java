package group4_sc2002_project;

public class EnquiryController {
	Project project;

	EnquiryController(Project project) {
		this.project = project;
	}

	void viewEnquiry(int id) {
		Enquiry enquiry = project.getEnquiry(id);
		System.out.println("ID: " + enquiry.getId() + " Body: " + enquiry.getContent());
		String reply = enquiry.getReply();
		if (reply.isEmpty()) {
			System.out.println("Reply: " + enquiry.getReply());
		}
	}
}
