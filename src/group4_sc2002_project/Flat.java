package group4_sc2002_project;

public class Flat {
	private String unitID;
	private String flatType;
	private boolean isBooked;
	private Project project;

	Flat(String id, String fType, Project project) {
		this.unitID = id;
		this.flatType = fType;
		this.isBooked = false;
		this.project = project;
	}

	String getUnitID() {
		return unitID;
	}

	String getFlatType() {
		return flatType;
	}

	boolean isAvailable() {
		return !isBooked;
	}

	void markAsBooked() {
		isBooked = true;
	}

	Project getProject() {
		return project;
	}
}
