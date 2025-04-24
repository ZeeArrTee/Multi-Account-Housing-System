package group4_sc2002_project;

import java.time.LocalDate;
import java.util.Map;

public interface ProjectView {
	public static void displayProject(String name) {
	}

	public static void createProject(String projectName, String neighbourhood, Map<String, Integer> units,
			LocalDate openDate, LocalDate closeDate, int officerSlots, Manager managerInCharge) {
	}

	public static void editProject(Project project) {
	}
}
