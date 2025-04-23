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
	private List<Application> apps;
	private int enquiryCount;

	public Project(String projectName, String neighbourhood, Map<String, Integer> units, LocalDate openDate,
			LocalDate closeDate, int officerSlots, HDBManager managerInCharge) {
		this.isVisible = true;
		this.enquiryCount = 0;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.projectName = projectName;
		this.officerSlots = officerSlots;
		this.neighbourhood = neighbourhood;
		this.managerInCharge = managerInCharge;
		this.apps = new ArrayList<Application>();
		this.enquiries = new ArrayList<Enquiry>();
		this.units = new HashMap<String, Integer>();
		this.assignedOfficers = new ArrayList<HDBOfficer>();
	}

	public void toggleVisibility() {
		isVisible = !isVisible;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public List<Application> getApplications() {
		return apps;
	}

	public void addApplication(Application application) {
		if (application != null) {
			apps.add(application);
		}
	}

	public Application getApplication(String uid) {
		for (Application app : apps) {
			if (app.getApplicant().getUserID().compareTo(uid) == 0) {
				return app;
			}
		}
		return null;

	}

	public boolean isWithinApplicationPeriod(LocalDate date) {
		return ((date.isAfter(openDate) || date.isEqual(openDate))
				&& (date.isBefore(closeDate) || date.isEqual(closeDate)));
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

	public boolean addOfficer(HDBOfficer officer) {
		boolean success = false;
		if (officerSlots > 0) {
			assignedOfficers.add(officer);
			officerSlots--;
			success = true;
		}
		return success;
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

	public void setNeighbourhood(String neighbourhood) 
	{
		this.neighbourhood = neighbourhood;
	}
	
	public void setOfficerSlots(int slots) 
	{
		this.officerSlots = slots;
	}
}
