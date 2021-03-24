package Service.Admin;

import Model.User;
import Model.Validation.Notification;

public interface AdminService {
    Notification<Boolean> createEmployee(String username, String password);

    public User readEmployee(Long id);

    Notification<Boolean> updateEmployee(Long id, String username, String password);

    Notification<Boolean> deleteEmployee(Long id);
}
