package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;

public class Applicant extends Account {
	private Application application;
	private List<Integer> enquiryId;
	private String bookedFlat;
	ApplicantEnquiryController applicantEnquiryController;

	Applicant(String userID, String password, int age, String maritalStatus) {
		super(userID, password, age, maritalStatus, "Applicant");
		application = null;
		enquiryId = new ArrayList<Integer>();
		applicantEnquiryController = new ApplicantEnquiryController(this);

	}


	public Application viewApplication()
	{
		return application
	}

	public void addEnquiryID(int id)
	{
		enquiryId.add(id)
	}

	public void removeEnquiryId(int id)
	{
		enquiryId.remove(id)
	}

	public void setBookedFlat(String flatType)
	{
		bookedFlat = flatType
	}

	public String getBookedFlat()
	{
		return bookedFlat
	}

	public boolean isEligible(String flatType) {
		if (application != null) {
			return false;
		}
		if (getMaritalStatus() == "Single" && getAge() >= 35) {
			return flatType == "2-Room";
		} else if (getMaritalStatus() == "Married" && getAge() >= 21) {
			return true;
		} else {
			return false;
		}
	}

	public void applyForProject(Project project, String flatType, int id) {
		if (isEligible(flatType)) {
			application = new Application(id, this, project, flatType, ApplicationStatus.Pending);
		}
	}

	public void requestWithdrawal() {
		application.setPendingWithdrawal(false);
	}

	public void displayEnquiries() {
		for (int id : enquiryId) {
			Enquiry enquiry = application.getProject().getEnquiry(id);
			System.out.println("ID: " + id + " Created: " + enquiry.getCreatedDate());
			System.out.println(enquiry.getContent());
			System.out.println();
		}
	}

}
