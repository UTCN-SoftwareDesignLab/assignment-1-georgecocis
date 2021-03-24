package services;
import Launcher.ComponentFactory;
import Model.User;
import Service.User.AuthenticationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repo.User.UserRepo;

public class AuthenticationServiceMySQLTest {

    public static final String TEST_USER = "george@gmail.com";
    public static final String TEST_PASSWORD = "Parolaluigeorge1!";
    public static final String SOME_OTHER_PASSWORD = "Thisisnotthepassword1!";

    private static UserRepo userRepo;
    private static AuthenticationService authenticationService;

    @BeforeClass
    public static void setup(){
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        userRepo = componentFactory.getUserRepository();
        authenticationService = componentFactory.getAuthenticationService();
    }

    @Before
    public void cleanUp(){
        userRepo.removeAll();
    }

    @Test
    public void register(){
        Assert.assertTrue(authenticationService.register(TEST_USER, TEST_PASSWORD).getResult());
    }

    @Test
    public void login(){
        authenticationService.register(TEST_USER, TEST_PASSWORD);
        User testUser1 = authenticationService.login(TEST_USER, TEST_PASSWORD).getResult();
        Assert.assertNotNull(testUser1);
    }
}
