package Service.Admin;

import Database.Constants;
import Model.Builder.UserBuilder;
import Model.Role;
import Model.User;
import Model.Validation.Notification;
import Model.Validation.UserValidator;
import Service.User.AuthenticationService;
import repo.RightsRolesRepo;
import repo.User.UserRepo;

import java.util.Collections;

public class AdminServiceMySQL implements AdminService {

    private final UserRepo userRepo;
    private final RightsRolesRepo rightsRolesRepo;
    private final AuthenticationService authenticationService;

    public AdminServiceMySQL(UserRepo userRepo, RightsRolesRepo rightsRolesRepo, AuthenticationService authenticationService){
        this.userRepo = userRepo;
        this.rightsRolesRepo = rightsRolesRepo;
        this.authenticationService = authenticationService;
    }
    @Override
    public Notification<Boolean> createEmployee(String username, String password) {
        Role role = rightsRolesRepo.findRoleByTitle(Constants.Roles.EMPLOYEE);
        User user = new UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .setRoles(Collections.singletonList(role))
                .build();

        UserValidator userValidator = new UserValidator(user);
        boolean userValid = userValidator.validate();
        Notification<Boolean> createEmpNoti = new Notification<>();
        if (!userValid){
            userValidator.getErrors().forEach(createEmpNoti::addError);
            createEmpNoti.setResult(Boolean.FALSE);
        } else {
            user.setPassword(authenticationService.encodePassword(password));
            createEmpNoti.setResult(userRepo.save(user));
        }
        return createEmpNoti;
    }

    @Override
    public User readEmployee(Long id) {
        return null;
    }

    @Override
    public Notification<Boolean> updateEmployee(Long id, String username, String password) {
        User user = new UserBuilder()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setRoles(Collections.singletonList(rightsRolesRepo.findRoleByTitle(Constants.Roles.EMPLOYEE)))
                .build();
        UserValidator userValidator = new UserValidator(user);
        boolean validUser = userValidator.validate();
        Notification<Boolean> updateEmpNoti = new Notification<>();
        if (!validUser){
            userValidator.getErrors().forEach(updateEmpNoti::addError);
            updateEmpNoti.setResult(Boolean.FALSE);
        } else {
            user.setPassword(authenticationService.encodePassword(password));
            updateEmpNoti.setResult(userRepo.updateUserById(id, username, authenticationService.encodePassword(password)));
        }
        return updateEmpNoti;
    }

    @Override
    public Notification<Boolean> deleteEmployee(Long id) {
        return null;
    }
}
