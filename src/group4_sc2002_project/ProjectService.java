package group4_sc2002_project;
import java.util.*;
import java.time.*;

public class ProjectService {
	private static List<Project> projectListing;
		
	public ProjectService() {
		projectListing = new ArrayList<Project>();
	}
	
	public void createProject(Project project) {
		projectListing.add(project);
	}
	
	public static List<Project> getProjectListing(){
		return projectListing;
	}
	
	public void addOfficer(String projectName, HDBOfficer officer) {
		for (Project project: projectListing) {
			if (project.getProjectName() == projectName) {
				project.addOfficer(officer);
			}
		}
	}
	
	public void removeOfficer(String projectName, HDBOfficer officer){
		for (Project project: projectListing) {
			if (project.getProjectName() == projectName) {
				project.removeOfficer(officer);
			}
		}
	}
	
	public void toggleVisibility(String projectName) {
		for (Project project: projectListing) {
			if (project.getProjectName() == projectName) {
				project.toggleVisibilty();
			}
		}
	}
	
	public void setProjectName(String projectName, String newName) {
		for (Project project: projectListing) {
			if (project.getProjectName() == projectName) {
				project.setProjectName(newName);
			}
		}
	}
	
	public void setUnits(String projectName, String flatType, int count) {
		for (Project project: projectListing) {
			if (project.getProjectName() == projectName) {
				project.setUnits(flatType, count);
			}
		}
	}
	
	public void setOpenDate(String projectName, LocalDate date) {
		for (Project project: projectListing) {
			if (project.getProjectName() == projectName) {
				project.setOpenDate(date);
			}
		}
	}
	
	public void setCloseDate(String projectName, LocalDate date) {
		for (Project project: projectListing) {
			if (project.getProjectName() == projectName) {
				project.setCloseDate(date);
			}
		}
	}
	
	public void deleteProject(String projectName) {
		for (Project project: projectListing) {
			if (project.getProjectName() == projectName)
				projectListing.remove(project);
		}
	}
}
