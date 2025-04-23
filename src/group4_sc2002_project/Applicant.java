package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;

public class Applicant extends User {
	private Application application;
	private List<Integer> enquiryId;
	private String bookedFlat;

	public Applicant(String name, String userID, String password, int age, String maritalStatus) {
		super(name, userID, password, age, maritalStatus, "Applicant");
		application = null;
		enquiryId = new ArrayList<Integer>();
	}

	public void requestWithdrawal() {
		application.setPendingWithdrawal(true);
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

	public Application getApplication() {
		return application;
	}

	public List<Integer> getEnquiryIds() {
		return enquiryId;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

}
