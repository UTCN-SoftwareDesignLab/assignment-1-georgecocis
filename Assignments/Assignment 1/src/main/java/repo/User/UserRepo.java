package repo.User;

import Model.User;
import Model.Validation.Notification;

import java.util.List;

public interface UserRepo {
    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password);

    boolean updateUserById(Long id, String username, String password);

    boolean save(User user);

    void removeAll();
}
