package Model.Validation;

import Model.User;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final int MIN_PASSWORD_LENGTH = 9;

    private final User user;

    private final List<String> errors;

    public UserValidator(User user){
        this.user = user;
        errors = new ArrayList<>();
    }

    public boolean validate(){
        validateUsername(user.getUsername());
        validatePassword(user.getPassword());
        return errors.isEmpty();
    }

    private void validateUsername(String username) {
        if (!Pattern.compile(EMAIL_VALIDATION_REGEX).matcher(username).matches()) {
            errors.add("Invalid email!");
        }
    }

    private void validatePassword(String password) {
        if (!correctLength(password)){
            errors.add("Not enough characters.");
        }
        if (!containsDigit(password)){
            errors.add("The password contains no digit.");
        }
        if (!containsSpecialCharacter(password)){
            errors.add("The password contains no special character.");
        }
        if (!containsUppercaseLetter(password)){
            errors.add("The password contains no uppercase letter.");
        }

    }

    private boolean correctLength(String password){
        return password.length() >= MIN_PASSWORD_LENGTH;
    }

    private boolean containsDigit(String password){
        if (password != null || !password.isEmpty()){
            for (char c : password.toCharArray()){
                if (Character.isDigit(c))
                    return true;
            }
        }
        return false;
    }

    private boolean containsUppercaseLetter(String password){
        if (password == null || password.trim().isEmpty()){
            return false;
        }
        Pattern p = Pattern.compile("[A-Z]");
        Matcher m = p.matcher(password);
        return m.find();
    }

    private boolean containsSpecialCharacter(String password){
        if (password == null || password.trim().isEmpty()){
            return false;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(password);
        return m.find();
    }

    public List<String> getErrors(){
        return errors;
    }

}
