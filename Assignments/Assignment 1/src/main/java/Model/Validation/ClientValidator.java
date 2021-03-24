package Model.Validation;

import Model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    private static final int PNC_VALUE = 13;
    private static final int IDENTITY_VALUE = 5;

    private final Client client;

    private final List<String> errors;

    public ClientValidator(Client client){
        this.client = client;
        this.errors = new ArrayList<>();
    }

    public boolean validate(){
        validateClient(client);
        return errors.isEmpty();
    }

    private void validateClient(Client client){
        if (!validatePnc(client.getPnc()))
            errors.add("PNC too short");
        if (!validateIdentity(client.getIdentity()))
            errors.add("Identity too short");
        if (!validateName(client.getName()))
            errors.add("Wrong name format. Last name + First name");

    }

    private boolean validatePnc(String pnc){
        return pnc.length() >= PNC_VALUE;
    }

    private boolean validateIdentity(String identity){
        return identity.length() >= IDENTITY_VALUE;
    }

    public boolean validateName(String name){
        if (name.length() == 0)
            return false;
        String[] string = null;
        string = name.trim().split(" ");
        if (string.length <= 1)
            return false;
        for (char c : name.toCharArray()){
            if (!Character.isLetter(c) && !(c == ' '))
                return false;
        }
        return true;
    }

    public List<String> getErrors(){
        return errors;
    }

}
