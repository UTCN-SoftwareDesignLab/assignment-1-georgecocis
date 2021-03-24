package Controller;

import Model.Validation.Notification;
import Service.Admin.AdminService;
import View.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {

    private final AdminView adminView;
    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminView = new AdminView();
        this.adminService = adminService;
        createListeners();
    }

    private void createListeners(){
        adminView.setCreateEmpBtnListener(new AdminController.CreateEmpBtnListener());
        adminView.setUpdateEmpBtnListener(new AdminController.UpdateClientBtnListener());
    }

    private class CreateEmpBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String password = adminView.getPassword();
            String username = adminView.getUsername();

            Notification<Boolean> notification = adminService.createEmployee(username, password);

            if (notification.hasErrors()){
                JOptionPane.showMessageDialog(adminView.getContentPane(), notification.getFormattedErrors());
            } else {
                if (!notification.getResult()){
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Error creating the Employee.");
                } else {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Employee created successfully!");
                }
            }
        }
    }

    private class UpdateClientBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = adminView.getUsername();
            String password = adminView.getPassword();
            String ids = adminView.getId();
            if (ids.equals("")){
                JOptionPane.showMessageDialog(adminView.getContentPane(), "Id error");
            } else {
                Long id = Long.parseLong(ids);
                Notification<Boolean> notification = adminService.updateEmployee(id, username, password);
                if (notification.hasErrors())
                    JOptionPane.showMessageDialog(adminView.getContentPane(), notification.getFormattedErrors());
                else {
                    if (!notification.getResult()) {
                        JOptionPane.showMessageDialog(adminView.getContentPane(), "Error updating the employee.");
                    } else {
                        JOptionPane.showMessageDialog(adminView.getContentPane(), "Employee updated successfully!");
                    }
                }
            }
        }
    }

    public void setVisible(Boolean bool){
        adminView.setVisible(bool);
    }
}
