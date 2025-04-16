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
	}

	List<Flat> getAvailableFlats(String flatType) {
		return flats.stream().filter(flat -> flat.getFlatType() == flatType).toList();
	}

	int getAvailableUnitsCount(String flatType) {
		return flats.stream().filter(flat -> flat.isAvailable()).toList();
	}

	boolean addOfficer(HDBOfficer officer) {
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
}
