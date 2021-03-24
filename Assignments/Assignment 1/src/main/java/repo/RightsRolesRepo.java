package repo;
import Model.Right;
import Model.Role;
import Model.User;

import java.util.*;

public interface RightsRolesRepo {

    void addRole(String role);

    void addRight(String right);

    void addRoleRight(Long roleId, Long rightId);

    Role findRoleByTitle (String role);

    Right findRightByTitle(String right);

    List<Role> findRolesForUser(Long userId);

    void addRolesToUser(User user, List<Role> roles);

    Role findRoleById(Long roleId);
}
