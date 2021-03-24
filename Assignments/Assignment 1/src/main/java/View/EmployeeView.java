package View;

import Service.Employee.EmployeeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame {
    private JTable clientsTable;
    private DefaultTableModel table;
    private JScrollPane scrollPane;

    JButton addClientBtn;
    JButton updateClientBtn;
    JButton viewClientBtn;

    JButton createAccountBtn;
    JButton updateAccountBtn;
    JButton deleteAccountBtn;
    JButton viewAccountBtn;

    JButton transferMoneyBtn;
    JButton processBillsBtn;

    JTextField clientNameTf;
    JTextField clientIdentityTf;
    JTextField clientPncTf;
    JTextField clientAddressTf;
    JTextField idTf;

    JTextField accountIdentificationTf;
    JTextField accountTypeTf;
    JTextField accountBalanceTf;
    JTextField accountDateTf;
    JTextField accountIdClientTf;

    JLabel clientNameLb;
    JLabel clientIdentityLb;
    JLabel clientPncLb;
    JLabel clientAddressLb;
    JLabel idLb;

    JLabel accountIdentLb;
    JLabel accountTypeLb;
    JLabel accountBalanceLb;
    JLabel accountDateLb;
    JLabel accountIdClientLb;

    public EmployeeView() throws HeadlessException {
        setTitle("Employee view");
        setSize(800, 600);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(180, 140, 600, 40);

        table  = new DefaultTableModel();
        table.addColumn("id");
        table.addColumn("name");
        table.addColumn("card_number");
        table.addColumn("pnc");
        table.addColumn("address");

        clientsTable = new JTable();
        clientsTable.setModel(table);
        scrollPane.setViewportView(clientsTable);

        addComponents();
    }

    private void addComponents(){
        addButtons();
        addTextFields();
        addLabels();
        add(scrollPane);
    }

    private void addButtons(){
        add(addClientBtn);
        add(updateClientBtn);
        add(viewClientBtn);

        add(createAccountBtn);
        add(updateAccountBtn);
        add(deleteAccountBtn);
        add(viewAccountBtn);

        add(transferMoneyBtn);
        add(processBillsBtn);
    }

    private void addTextFields(){
        add(clientNameTf);
        add(clientIdentityTf);
        add(clientPncTf);
        add(clientAddressTf);
        add(idTf);

        add(accountBalanceTf);
        add(accountDateTf);
        add(accountIdClientTf);
        add(accountIdentificationTf);
        add(accountTypeTf);
    }

    private void addLabels(){
        add(clientNameLb);
        add(clientIdentityLb);
        add(clientPncLb);
        add(clientAddressLb);
        add(idLb);

        add(accountTypeLb);
        add(accountBalanceLb);
        add(accountIdentLb);
        add(accountDateLb);
        add(accountIdClientLb);
    }

    private void initializeFields(){
        initializeButtons();
        initializeTextFields();
        initializeLabels();
    }

    private void initializeButtons(){
        addClientBtn = new JButton("Add client");
        addClientBtn.setBounds(10, 10, 140, 40);

        updateClientBtn = new JButton("Update Client");
        updateClientBtn.setBounds(10, 60, 140, 40);

        viewClientBtn = new JButton("View Client");
        viewClientBtn.setBounds(10, 110, 140, 40);

        createAccountBtn = new JButton("Create Account");
        createAccountBtn.setBounds(10, 210, 140, 40);

        updateAccountBtn = new JButton("Update Account");
        updateAccountBtn.setBounds(10, 260, 140, 40);

        deleteAccountBtn = new JButton("Delete Account");
        deleteAccountBtn.setBounds(10, 310, 140, 40);

        viewAccountBtn = new JButton("View Account");
        viewAccountBtn.setBounds(10, 360, 140, 40);

        transferMoneyBtn = new JButton("Transfer Money");
        transferMoneyBtn.setBounds(10, 460, 140, 40);

        processBillsBtn = new JButton("Process bill");
        processBillsBtn.setBounds(10, 510, 140, 40);
    }

    private void initializeTextFields(){
        clientNameTf = new JTextField();
        clientNameTf.setBounds(450, 10, 300, 20);

        clientIdentityTf = new JTextField();
        clientIdentityTf.setBounds(450, 40, 300, 20);

        clientPncTf = new JTextField();
        clientPncTf.setBounds(450, 70, 300, 20);

        clientAddressTf = new JTextField();
        clientAddressTf.setBounds(450, 100, 300, 20);

        idTf = new JTextField();
        idTf.setBounds(250, 10, 40, 20);

        accountIdentificationTf = new JTextField();
        accountIdentificationTf.setBounds(450, 200, 300, 20);

        accountTypeTf = new JTextField();
        accountTypeTf.setBounds(450, 230, 300, 20);

        accountBalanceTf= new JTextField();
        accountBalanceTf.setBounds(450, 260, 300, 20);

        accountDateTf = new JTextField();
        accountDateTf.setBounds(450, 290, 300, 20);

        accountIdClientTf = new JTextField();
        accountIdClientTf.setBounds(450, 320, 300, 20);
    }

    private void initializeLabels(){
        clientNameLb = new JLabel("Client Name");
        clientNameLb.setBounds(350, 10, 200, 20);

        clientIdentityLb =  new JLabel("Client Identity");
        clientIdentityLb.setBounds(350, 40, 200, 20);

        clientPncLb = new JLabel("Client PNC");
        clientPncLb.setBounds(350, 70, 200, 20);

        clientAddressLb = new JLabel("Client Address");
        clientAddressLb.setBounds(350, 100, 200, 20);

        idLb = new JLabel("ID");
        idLb.setBounds(230, 9, 20, 20);

        accountIdentLb = new JLabel("Account Identification");
        accountIdentLb.setBounds(310, 200, 200, 20);

        accountTypeLb = new JLabel("Account Type");
        accountTypeLb.setBounds(310, 230, 200, 20);

        accountBalanceLb = new JLabel("Account Balance");
        accountBalanceLb.setBounds(310, 260, 200, 20);

        accountDateLb = new JLabel("Account Date");
        accountDateLb.setBounds(310, 290, 200, 20);

        accountIdClientLb = new JLabel("Account Client ID");
        accountIdClientLb.setBounds(310, 320, 200, 20);
    }

    public String getClientName(){
        return clientNameTf.getText();
    }

    public String getClientIdentity(){
        return clientIdentityTf.getText();
    }

    public String getClientPnc(){
        return clientPncTf.getText();
    }

    public String getClientAddress(){
        return clientAddressTf.getText();
    }

    public String getClientId(){
        return idTf.getText();
    }

    public String getAccountIdentification(){
        return accountIdentificationTf.getText();
    }

    public String getAccountType(){
        return accountTypeTf.getText();
    }

    public String getAccountBalance(){
        return accountBalanceTf.getText();
    }

    public String getAccountDate(){
        return accountDateTf.getText();
    }

    public String getAccountIdClient(){
        return accountIdClientTf.getText();
    }

    public void setAddClientBtnListener(ActionListener listener) {
        addClientBtn.addActionListener(listener);
    }

    public void setUpdateClientBtnListener(ActionListener listener) {
        updateClientBtn.addActionListener(listener);
    }

    public void setViewClientBtnListener(ActionListener listener){
        viewClientBtn.addActionListener(listener);
    }

    public void setViewAccountBtnListener(ActionListener listener){
        viewAccountBtn.addActionListener(listener);
    }

    public void setCreateAccountBtnListener(ActionListener listener){
        createAccountBtn.addActionListener(listener);
    }

    public void setUpdateAccountBtnListener(ActionListener listener){
        updateAccountBtn.addActionListener(listener);
    }

    public DefaultTableModel getModel() {
        return this.table;
    }

    public JTable getTableClients() {
        return this.clientsTable;
    }
}
