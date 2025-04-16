package group4_sc2002_project;

import java.time.LocalDate;

public class ApplicationController {

    public boolean applyForProject(Applicant applicant, Project project, String flatType) 
    {
        if (!canApplyFor(applicant, project)) {
            return false;
        }

        int generatedId = 0; //you can cook the hash thingy u wanted
        Application application = new Application(generatedId, applicant, project, flatType, ApplicationStatus.Pending);

        applicant.setApplication(application);
        return true;
    }

  
    public boolean canApplyFor(Applicant applicant, Project project) 
    {
        if (applicant.viewApplication() != null) 
        {
            return false;
        }

        LocalDate today = LocalDate.now();
        return project.isWithinApplicationPeriod(today);
    }

    public void withdrawApplication(Applicant applicant) 
    {
        Application applic = applicant.viewApplication();
        if (applic != null && !applic.isWithdrawalRequested()) 
        {
            applic.setPendingWithdrawal(true);
            applic.setStatus(ApplicationStatus.Withdrawal_Requested);
        }
    }

    public ApplicationStatus getApplicationStatus(Applicant applicant) 
    {
        Application applic = applicant.viewApplication();
        if (applic != null) 
        {
            return applic.getStatus();
        }

        return null;
    }

}
