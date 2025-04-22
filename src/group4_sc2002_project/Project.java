package group4_sc2002_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {

	private String projectName;
	private String neighbourhood;
	private Map<String, Integer> units;
	private LocalDate openDate;
	private LocalDate closeDate;
	private boolean isVisible;
	private int officerSlots;
	private List<HDBOfficer> assignedOfficers;
	private HDBManager managerInCharge;
	private List<Enquiry> enquiries;
	private int enquiryCount;

	public Project(String projectName, String neighbourhood, Map<String, Integer> units, LocalDate openDate,
			LocalDate closeDate, int officerSlots, HDBManager managerInCharge) {
		this.projectName = projectName;
		this.neighbourhood = neighbourhood;
		this.units = new HashMap<String, Integer>();
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.isVisible = true;
		this.officerSlots = officerSlots;
		this.assignedOfficers = new ArrayList<HDBOfficer>();
		this.managerInCharge = managerInCharge;
		this.enquiries = new ArrayList<Enquiry>();
		this.enquiryCount = 0;
	}

	public void toggleVisibilty() {
		isVisible = !isVisible;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public boolean isWithinApplicationPeriod(LocalDate date) {
		return (date.isAfter(openDate) && date.isBefore(closeDate));
	}

	public void setProjectName(String newName) {
		projectName = newName;
	}

	public void setUnits(String flatType, int count) {
		units.put(flatType, count);
	}

	public void setOpenDate(LocalDate date) {
		openDate = date;
	}

	public void setCloseDate(LocalDate date) {
		closeDate = date;
	}

	public void addOfficer(HDBOfficer officer) {
		if (officerSlots > 0) {
			assignedOfficers.add(officer);
			officerSlots--;
		}
	}

	public void removeOfficer(HDBOfficer officer) {
		for (HDBOfficer off : assignedOfficers) {
			if (off.getUserID().compareTo(officer.getUserID()) == 0) {
				assignedOfficers.remove(off);
			}
		}
	}

	public void removeEnquiry(int enquiryId) {
		for (Enquiry enquiry : enquiries) {
			if (enquiry.getId() == enquiryId) {
				enquiries.remove(enquiry);
				break;
			}
		}
	}

	public int getAvailableUnitsCount(String flatType) {
		return units.get(flatType);
	}

	public String getProjectName() {
		return projectName;
	}

	public Map<String, Integer> getUnits() {
		return units;
	}

	public LocalDate getOpeningDate() {
		return openDate;
	}

	public LocalDate getClosingDate() {
		return closeDate;
	}

	public HDBManager getManager() {
		return managerInCharge;
	}

	public List<HDBOfficer> getOfficers() {
		return assignedOfficers;
	}

	public List<Enquiry> getEnquiries() {
		return enquiries;
	}

	public Enquiry getEnquiry(int enquiryId) {
		for (Enquiry enquiry : enquiries) {
			if (enquiry.getId() == enquiryId) {
				return enquiry;
			}
		}
		return null;
	}

	public void addEnquiry(Enquiry enquiry) {
		enquiries.add(enquiry);
	}

	public int getEnquiryCount() {
		return enquiryCount;
	}

	public void incrementEnquiryCount() {
		enquiryCount++;
	}

	public int getOfficerSlots() {
		return officerSlots;
	}
}
