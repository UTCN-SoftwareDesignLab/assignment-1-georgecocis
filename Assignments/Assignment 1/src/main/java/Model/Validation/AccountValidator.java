package Model.Validation;

import Model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountValidator {

    public static final int IDENTIFICATION_LOWER_BOUND = 6;
    public static final int IDENTIFICATION_UPPER_BOUND = 12;

    public final Account account;
    public final List<String> errors;

    public AccountValidator(Account account){
        this.account = account;
        this.errors = new ArrayList<>();
    }

    public boolean validate(){
        validateAccount(account);
        return errors.isEmpty();
    }

    public void validateAccount(Account account){
        if (!validateBalance(account.getBalance()))
            errors.add("Error: balance must be 0.");
        if (!validateIdentification(account.getIdentification()))
            errors.add("Error: Identification must be between 6 and 12 characters");
        if (!validateType(account.getType()))
            errors.add("Error: card must be of type Debit or Credit.");

    }

    public boolean validateBalance(Float balance){
        return balance == 0;
    }

    public boolean validateIdentification(String identification){
        return identification.length() >= IDENTIFICATION_LOWER_BOUND && identification.length() <= IDENTIFICATION_UPPER_BOUND;
    }

    public boolean validateType(String type){
        return type.equals("Debit") || type.equals("Credit") || type.equals("debit") || type.equals("credit");
    }

    public List<String> getErrors(){
        return errors;
    }
}
