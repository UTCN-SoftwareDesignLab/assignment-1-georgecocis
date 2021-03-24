package Service.User;

import Model.User;
import Model.Validation.Notification;

public interface AuthenticationService {
    Notification<Boolean> register(String username, String password);

    Notification<User> login(String username, String password);

    boolean logout(User user);

    String encodePassword(String password);
}
