package repo.Account;

import Model.Account;
import repo.EntityNotFoundException;

import java.sql.*;

public interface AccountRepo {

    public boolean save(Account account);

    public Account viewAccount(Long id) throws EntityNotFoundException;

    public void deleteAccount(Long id);

    public boolean updateIdentificationById(Long id, String identification);

    public boolean updateTypeById(Long id, String type);

    public boolean updateBalanceById(Long id, Float balance);

    public boolean updateDateById(Long id, Date date);

    public void removeAll();

}
