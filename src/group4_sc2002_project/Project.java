package group4_sc2002_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {

	private String projectName;
	private String neighbourhood;
	private List<Flat> flats;
	private LocalDate openDate;
	private LocalDate closeDate;
	private boolean isVisible;
	private int officerSlots;
	private List<HDBOfficer> assignedOfficers;
	private HDBManager managerInCharge;
	private List<Enquiry> enquiries;
	private int enquiryCount;

	Project(String projectName, String neighbourhood, List<Flat> flats, LocalDate openDate, LocalDate closeDate,
			int officerSlots, HDBManager managerInCharge) {
		this.projectName = projectName;
		this.neighbourhood = neighbourhood;
		this.flats = flats;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.isVisible = true;
		this.officerSlots = officerSlots;
		this.assignedOfficers = new ArrayList<HDBOfficer>();
		this.managerInCharge = managerInCharge;
		this.enquiries = new ArrayList<Enquiry>();
		this.enquiryCount = 0;
	}

	List<Flat> getAvailableFlats(String flatType) {
		return flats.stream().filter(flat -> flat.getFlatType() == flatType).toList();
	}

	String getProjectName() {
		return projectName;
	}

	int getAvailableUnitsCount(String flatType) {
		return flats.stream().filter(flat -> flat.isAvailable()).toList().size();
	}

	void addOfficer(HDBOfficer officer) {
		assignedOfficers.add(officer);
	}

	HDBManager getManager() {
		return managerInCharge;
	}

	void toggleVisibility() {
		isVisible = !isVisible;
	}

	boolean isWithinApplicationPeriod(LocalDate date) {
		return (date.isAfter(openDate) && date.isBefore(closeDate));
	}

	Enquiry getEnquiry(int id) {
		for (Enquiry enquiry : enquiries) {
			if (enquiry.getId() == id) {
				return enquiry;
			}
		}
		return null;
	}

	int makeEnquiry(Applicant applicant, String message) {
		Enquiry enquiry = new Enquiry(enquiryCount, applicant, this, message, LocalDate.now());
		enquiryCount++;
		enquiries.add(enquiry);
		return enquiryCount - 1;
	}

	void editEnquiry(int id, String message) {
		for (Enquiry enquiry : enquiries) {
			if (enquiry.getId() == id) {
				enquiry.setContent(message);
				break;
			}
		}
	}

	void deleteEnquiry(int id) {
		for (Enquiry enquiry : enquiries) {
			if (enquiry.getId() == id) {
				enquiries.remove(enquiry);
			}
		}
	}
}
