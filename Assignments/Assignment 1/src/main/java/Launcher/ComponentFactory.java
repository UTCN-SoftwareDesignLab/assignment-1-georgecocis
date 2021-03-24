package Launcher;

import Controller.AdminController;
import Controller.EmployeeController;
import Controller.LoginController;
import Database.ConnectionFactory;
import Service.Admin.AdminService;
import Service.Admin.AdminServiceMySQL;
import Service.Employee.EmployeeService;
import Service.Employee.EmployeeServiceMySQL;
import Service.User.AuthenticationService;
import Service.User.AuthenticationServiceMySQL;
import View.EmployeeView;
import View.LoginView;
import repo.Account.AccountRepo;
import repo.Account.AccountRepoMySQL;
import repo.Client.ClientRepo;
import repo.Client.ClientRepoMySQL;
import repo.RightsRolesRepo;
import repo.RightsRolesRepoMySQL;
import repo.User.UserRepo;
import repo.User.UserRepoMySQL;

import java.sql.Connection;

public class ComponentFactory {

    private final LoginView loginView;
    private final EmployeeView employeeView;
    private final LoginController loginController;
    private final EmployeeController employeeController;
    private final AdminController adminController;

    private final AuthenticationService authenticationService;
    private final EmployeeService employeeService;
    private final AdminService adminService;

    private final UserRepo userRepo;
    private final RightsRolesRepo rightsRolesRepo;
    private final ClientRepo clientRepo;
    private final AccountRepo accountRepo;

    private static ComponentFactory instance;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new ConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepo = new RightsRolesRepoMySQL(connection);
        this.userRepo = new UserRepoMySQL(connection, rightsRolesRepo);
        this.clientRepo = new ClientRepoMySQL(connection);
        this.accountRepo = new AccountRepoMySQL(connection);
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepo, this.rightsRolesRepo);
        this.employeeService = new EmployeeServiceMySQL(this.clientRepo, this.accountRepo);
        this.adminService = new AdminServiceMySQL(this.userRepo, this.rightsRolesRepo, this.authenticationService);
        this.loginView = new LoginView();
        this.employeeView = new EmployeeView();
        this.employeeController = new EmployeeController(employeeService);
        this.adminController = new AdminController(adminService);
        this.loginController = new LoginController(loginView, authenticationService, employeeController, adminController);
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepo getUserRepository() {
        return userRepo;
    }

    public RightsRolesRepo getRightsRolesRepository() {
        return rightsRolesRepo;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public EmployeeView getEmployeeView() {
        return employeeView;
    }

    public EmployeeController getEmployeeController() {
        return employeeController;
    }

    public AdminController getAdminController() {
        return adminController;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public RightsRolesRepo getRightsRolesRepo() {
        return rightsRolesRepo;
    }

    public ClientRepo getClientRepo() {
        return clientRepo;
    }

    public AccountRepo getAccountRepo() {
        return accountRepo;
    }
}
