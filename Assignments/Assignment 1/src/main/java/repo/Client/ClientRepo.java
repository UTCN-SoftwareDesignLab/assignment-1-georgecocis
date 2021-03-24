package repo.Client;

import Model.Client;
import repo.EntityNotFoundException;

import java.sql.ResultSet;

public interface ClientRepo {

    public boolean save(Client client);

    public boolean updateNameById(Long id, String name);

    public boolean updateIdentityById(Long id, String identity);

    public boolean updatePncById(Long id, String pnc);

    public boolean updateAddressById(Long id, String address);

    public Client viewClient(Long id) throws EntityNotFoundException;

    public void removeAll();
}
