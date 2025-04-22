package group4_sc2002_project;
import java.util.*;
import java.time.*;

public class HDBProjectService {
	List<Project> projectListing = ProjectService.getProjectListing();
	private HDBManager manager;
	
	public void createProject(String projectName, String neighbourhood, Map<String,Integer> unit, LocalDate openDate, LocalDate closeDate,  int officerSlots) {
		Project project = new Project(projectName, neighbourhood, unit, openDate, closeDate, officerSlots, manager);
		projectListing.add(project);	
	}
}
