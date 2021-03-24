package Service.Employee;

import Model.Account;
import Model.Client;
import Model.Validation.Notification;
import repo.EntityNotFoundException;

import java.sql.Date;

public interface EmployeeService {
    Notification<Boolean> addClient(String name, String identity, String pnc, String address);

    Notification<Boolean> updateClient(Long id, String name);

    public Client viewClient(Long id) throws EntityNotFoundException;

    Notification <Boolean> addAccount(String identification, String type, Float balance, Date date, Long idClient);

    Notification<Boolean> updateAccount(Long id, String type);

    public Account viewAccount(Long id);
}
