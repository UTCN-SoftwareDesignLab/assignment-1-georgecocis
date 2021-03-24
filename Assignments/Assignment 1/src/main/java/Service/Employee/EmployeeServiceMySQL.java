package Service.Employee;

import Model.Account;
import Model.Builder.AccountBuilder;
import Model.Builder.ClientBuilder;
import Model.Client;
import Model.Validation.AccountValidator;
import Model.Validation.ClientValidator;
import Model.Validation.Notification;
import repo.Account.AccountRepo;
import repo.Client.ClientRepo;
import repo.EntityNotFoundException;

import java.sql.Date;

public class EmployeeServiceMySQL implements EmployeeService {

    private final ClientRepo clientRepo;
    private final AccountRepo accountRepo;

    public EmployeeServiceMySQL(ClientRepo clientRepo, AccountRepo accountRepo){
        this.clientRepo = clientRepo;
        this.accountRepo = accountRepo;
    }

    @Override
    public Notification<Boolean> addClient(String name, String identity, String pnc, String address) {
        Client client = new ClientBuilder()
                .setName(name)
                .setIdentity(identity)
                .setPnc(pnc)
                .setAddress(address)
                .build();

        ClientValidator cv = new ClientValidator(client);
        boolean validClient = cv.validate();

        Notification<Boolean> addClientNotification = new Notification<>();

        if (!validClient){
            cv.getErrors().forEach(addClientNotification::addError);
            addClientNotification.setResult(false);
        } else{
            addClientNotification.setResult(clientRepo.save(client));
        }
        return addClientNotification;
    }

    @Override
    public Notification<Boolean> updateClient(Long id, String name) {
        Client client = new ClientBuilder()
                .setId(id)
                .setName(name)
                .build();
        ClientValidator cv = new ClientValidator(client);
        Boolean validName = cv.validateName(client.getName());

        Notification<Boolean> updateClientNotification = new Notification<>();
        if (!validName){
           cv.getErrors().forEach(updateClientNotification::addError);
           updateClientNotification.setResult(false);
        } else{
            updateClientNotification.setResult(clientRepo.updateNameById(client.getId(), client.getName()));
        }
        return updateClientNotification;
    }

    @Override
    public Client viewClient(Long id) throws EntityNotFoundException {
        return clientRepo.viewClient(id);
    }

    @Override
    public Notification<Boolean> addAccount(String identification, String type, Float balance, Date date, Long idClient) {
        Account account = new AccountBuilder()
                .setIdentification(identification)
                .setType(type)
                .setBalance(balance)
                .setDate(date)
                .setIdClient(idClient)
                .build();
        AccountValidator av = new AccountValidator(account);
        boolean validAccount = av.validate();

        Notification<Boolean> addAccNotification = new Notification<>();

        if (!validAccount){
            av.getErrors().forEach(addAccNotification::addError);
            addAccNotification.setResult(false);
        } else{
            addAccNotification.setResult(accountRepo.save(account));
        }
        return addAccNotification;
    }

    @Override
    public Notification<Boolean> updateAccount(Long id, String type) {
        Account account = new AccountBuilder()
                .setId(id)
                .setType(type)
                .build();
        AccountValidator av = new AccountValidator(account);
        Boolean validType = av.validateType(account.getType());

        Notification<Boolean> updateAccNotification = new Notification<>();
        if (!validType){
            av.getErrors().forEach(updateAccNotification::addError);
            updateAccNotification.setResult(false);
        } else{
            updateAccNotification.setResult(accountRepo.updateTypeById(account.getId(), account.getType()));
        }
        return updateAccNotification;
    }

    @Override
    public Account viewAccount(Long id) {
        return null;
    }
}
