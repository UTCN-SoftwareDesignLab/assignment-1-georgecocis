package services;
import Launcher.ComponentFactory;
import Model.Client;
import Service.Employee.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repo.Client.ClientRepo;
import repo.EntityNotFoundException;
import repo.User.UserRepo;

import java.sql.Date;
import java.time.LocalDate;

public class EmployeeServiceMySQL {

    private static final String name = "Test Name";
    private static final String identity = "1234567890";
    private static final String pnc = "1234567890123";
    private static final String address = "Whenever, wherever";

    private static final String identification = "123456789012";
    private static final String type = "Debit";
    private static final Float balance = 0.0f;
    private static final Date date = Date.valueOf(LocalDate.now());
    private static final Long id = (long) 1;

    private static ClientRepo clientRepo;
    private static EmployeeService employeeService;

    @BeforeClass
    public static void setup(){
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        clientRepo = componentFactory.getClientRepo();
        employeeService = componentFactory.getEmployeeService();
    }

    @Before
    public void cleanUp(){
        clientRepo.removeAll();
    }

    @Test
    public void addClient(){
        Assert.assertTrue(employeeService.addClient(name, identity, pnc, address).getResult());
    }

    @Test
    public void updateClient(){
        employeeService.addClient(name, identity, pnc, address);
        Assert.assertTrue(employeeService.updateClient((long)1, "Another Name").getResult());
    }
}
