package group4_sc2002_project;

import java.util.ArrayList;
import java.util.List;

public interface Account {

    String getUserID();

    String getPassword();

    void changePassword(String newPwd);

    int getAge();

    String getMaritalStatus();

    List<String> getRole();
}
