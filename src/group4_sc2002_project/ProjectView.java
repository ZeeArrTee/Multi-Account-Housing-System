package group4_sc2002_project;

import java.time.LocalDate;
import java.util.Map;

public interface ProjectView {
	void displayProjects();

	void createProject(String projectName, String neighbourhood, Map<String, Integer> units, LocalDate openDate,
			LocalDate closeDate, int officerSlots, HDBManager managerInCharge);

	void editProject(Project project);
}
