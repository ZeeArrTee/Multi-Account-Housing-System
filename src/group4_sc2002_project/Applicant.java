package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;

public class Applicant extends Account {
	private Application application;
	private List<Integer> enquiryId;
	private String bookedFlat;

	public Applicant(String id, String pw, int age, String marital) {
		super(id, pw, age, marital, "Applicant");
		application = null;
		enquiryId = new ArrayList<Integer>();
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
			application = new Application(this, project, flatType, ApplicationStatus.Pending);
		}
	}

	public Application getApplication() {
		return application;
	}

	public void requestWithdrawal() {
		application.toggleWithdrawal();
	}

	public void addEnquiryId(int id) {
		enquiryId.add(id);
	}

	public void removeEnquiryId(int id) {
		enquiryId.remove(id);
	}

	public void setBookedFlat(String flatType) {
		bookedFlat = flatType;
	}

	public String getBookedFlat() {
		return bookedFlat;
	}
	// public void displayEnquiries() {
	// for (int id : enquiryId) {
	// Enquiry enquiry = application.getProject().getEnquiry(id);
	// System.out.println("ID: " + id + " Created: " + enquiry.getCreatedDate());
	// System.out.println(enquiry.getContent());
	// System.out.println();
	// }
	// }

}
