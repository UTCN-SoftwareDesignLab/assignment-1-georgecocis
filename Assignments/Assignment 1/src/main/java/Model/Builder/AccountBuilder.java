package Model.Builder;

import Model.Account;

import java.sql.*;

public class AccountBuilder {

    private Account account;

    public AccountBuilder() {
        account = new Account();
    }

    public AccountBuilder setId(Long id){
        account.setId(id);
        return this;
    }

    public AccountBuilder setIdentification(String identification){
        account.setIdentification(identification);
        return this;
    }

    public AccountBuilder setType(String type){
        account.setType(type);
        return this;
    }

    public AccountBuilder setBalance (Float balance){
        account.setBalance(balance);
        return this;
    }

    public AccountBuilder setDate(Date date){
        account.setDate(date);
        return this;
    }

    public AccountBuilder setIdClient(Long idClient){
        account.setIdClient(idClient);
        return this;
    }

    public Account build(){
        return account;
    }
}
