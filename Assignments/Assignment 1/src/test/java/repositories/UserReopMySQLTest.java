package repositories;
import Database.Constants;
import Launcher.ComponentFactory;
import Model.Builder.UserBuilder;
import Model.Role;
import Model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repo.RightsRolesRepo;
import repo.User.UserRepo;

import java.util.Collections;

public class UserReopMySQLTest {

    private static UserRepo userRepo;
    private static RightsRolesRepo rightsRolesRepo;

    public static void setup(){
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        userRepo = componentFactory.getUserRepo();
        rightsRolesRepo = componentFactory.getRightsRolesRepo();
    }

    @Before
    public void cleanUp(){
        userRepo.removeAll();
    }

}
