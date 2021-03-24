package Controller;

import Model.Client;
import Model.Validation.Notification;
import Service.Employee.EmployeeService;
import View.EmployeeView;
import repo.EntityNotFoundException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

public class EmployeeController {

    private final EmployeeView employeeView;
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeView = new EmployeeView();
        this.employeeService = employeeService;
        createListeners();
    }

    private void createListeners(){
        employeeView.setAddClientBtnListener(new EmployeeController.AddClientBtnListener());
        employeeView.setUpdateClientBtnListener(new EmployeeController.UpdateClientBtnListener());
        employeeView.setViewClientBtnListener(new EmployeeController.ViewClientBtnListener());

        employeeView.setCreateAccountBtnListener(new EmployeeController.CreateAccountBtnListener());
        employeeView.setUpdateAccountBtnListener(new EmployeeController.UpdateAccountBtnListener());
        employeeView.setViewAccountBtnListener(new EmployeeController.ViewAccountBtnListener());
    }

    private class AddClientBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = employeeView.getClientName();
            String ident = employeeView.getClientIdentity();
            String pnc = employeeView.getClientPnc();
            String address = employeeView.getClientAddress();
            Notification<Boolean> addClientNoti = employeeService.addClient(name, ident, pnc, address);

            if (addClientNoti.hasErrors())
                JOptionPane.showMessageDialog(employeeView.getContentPane(), addClientNoti.getFormattedErrors());
            else{
                if (!addClientNoti.getResult()){
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Error adding the client.");
                } else {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client added successfully.");
                }
            }
        }
    }

    private class UpdateClientBtnListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = employeeView.getClientName();
            String ids = employeeView.getClientId();
            if (ids.equals("")){
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Id error");
            } else {
                Long id = Long.parseLong(ids);
            Notification<Boolean> updateClientNoti = employeeService.updateClient(id, name);
            if (updateClientNoti.hasErrors())
                JOptionPane.showMessageDialog(employeeView.getContentPane(), updateClientNoti.getFormattedErrors());
            else {
                if (!updateClientNoti.getResult()) {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Error updating the client.");
                } else {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client updated successfully!");
                }
            }
            }
        }
    }

    private class ViewClientBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (employeeView.getClientId().equals("")){
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Id error");
            }
            else {
                Long id = Long.parseLong(employeeView.getClientId());
                try {
                    Client client = employeeService.viewClient(id);
                    updateClientsTable(client.getId(), client.getName(), client.getIdentity(), client.getPnc(), client.getAddress());

                } catch (EntityNotFoundException entityNotFoundException) {
                    entityNotFoundException.printStackTrace();
                }
            }
        }
    }

    private class CreateAccountBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String ident = employeeView.getAccountIdentification();
            String type = employeeView.getAccountType();
            Float balance = Float.parseFloat(employeeView.getAccountBalance());
            Long idClient = Long.parseLong(employeeView.getAccountIdClient());

            Notification<Boolean> addAccNoti = employeeService.addAccount(ident, type, balance, Date.valueOf(LocalDate.now()), idClient);

            if (addAccNoti.hasErrors())
                JOptionPane.showMessageDialog(employeeView.getContentPane(), addAccNoti.getFormattedErrors());
            else{
                if (!addAccNoti.getResult()){
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Error creating the account.");
                } else {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account created successfully.");
                }
            }
        }
    }

    private class UpdateAccountBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class ViewAccountBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public void updateClientsTable(Long id, String name, String ident, String pnc, String addr){
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Id");
        table.addColumn("Name");
        table.addColumn("Identity");
        table.addColumn("PNC");
        table.addColumn("Address");
        Object[] o = {id, name, ident, pnc, addr};
        table.addRow(o);

        employeeView.getTableClients().setModel(table);
    }

    public void setVisible(Boolean bool){
        employeeView.setVisible(bool);
    }

}
