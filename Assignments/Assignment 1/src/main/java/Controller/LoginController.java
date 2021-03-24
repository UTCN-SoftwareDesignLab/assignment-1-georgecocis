package Controller;

import Database.Constants;
import Database.Constants.Roles.*;
import Model.Role;
import Model.User;
import Model.Validation.Notification;
import Service.User.AuthenticationService;
import View.AdminView;
import View.EmployeeView;
import View.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private final LoginView loginView;
    private final AuthenticationService authenticationService;
    private final EmployeeController employeeController;
    private final AdminController adminController;

    public LoginController(LoginView loginView, AuthenticationService authenticationService, EmployeeController employeeController, AdminController adminController){
        this.loginView = loginView;
        this.employeeController = employeeController;
        this.adminController = adminController;
        this.authenticationService = authenticationService;
        loginView.setLoginButtonListener(new LoginButtonListener());
        loginView.setRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            Notification<User> loginNotification = authenticationService.login(username, password);


            if (loginNotification.hasErrors()){
                JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
            }
            else {
                User user = loginNotification.getResult();
                for (Role role : user.getRoles()){
                   if (role.getRole().equals(Constants.Roles.EMPLOYEE)){
                       employeeController.setVisible(true);
                        loginView.setVisible(false);
                    }
                   if (role.getRole().equals(Constants.Roles.ADMINISTRATOR)){
                        adminController.setVisible(true);
                       loginView.setVisible(false);
                   }
                }

                JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");

            }
        }
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);

            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");
                }
            }
        }

        public void setVisible(Boolean bool){
            loginView.setVisible(bool);
        }
    }
}
