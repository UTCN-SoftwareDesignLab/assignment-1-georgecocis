package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AdminView extends JFrame {

    JButton createB;
    JButton readB;
    JButton deleteB;
    JButton updateB;

    JTextField usernameT;
    JTextField passwordT;
    JTextField idT;

    JLabel usernameL;
    JLabel passwordL;
    JLabel idL;

    public AdminView(){
        setTitle("Administrator view");
        setSize(800, 600);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponents();
    }

    private void addComponents(){
        addButtons();
        addTextFields();
        addLabels();
    }

    private void addButtons(){
        add(createB);
        add(readB);
        add(updateB);
        add(deleteB);
    }

    private void addTextFields(){
        add(usernameT);
        add(passwordT);
        add(idT);
    }

    private void addLabels(){
        add(usernameL);
        add(passwordL);
        add(idL);
    }

    private void initializeFields(){
        initializeButtons();
        initializeTextFields();
        initializeLabels();
    }

    private void initializeButtons(){
        createB = new JButton("Create Emp");
        createB.setBounds(10, 10, 140, 40);

        readB = new JButton("Read Emp");
        readB.setBounds(10, 60, 140, 40);

        updateB = new JButton("Update Emp");
        updateB.setBounds(10, 110, 140, 40);

        deleteB = new JButton("Delete Emp");
        deleteB.setBounds(10, 160, 140, 40);
    }

    private void initializeTextFields(){
        usernameT = new JTextField();
        usernameT.setBounds(450, 10, 300, 20);

        passwordT = new JTextField();
        passwordT.setBounds(450, 40, 300, 20);

        idT = new JTextField();
        idT.setBounds(250, 10, 40, 20);
    }

    private void initializeLabels(){
        usernameL = new JLabel("Emp username");
        usernameL.setBounds(350, 10, 200, 20);

        passwordL =  new JLabel("Emp password");
        passwordL.setBounds(350, 40, 200, 20);

        idL = new JLabel("ID");
        idL.setBounds(230, 9, 20, 20);
    }

    public String getUsername(){
        return usernameT.getText();
    }

    public String getPassword(){
        return passwordT.getText();
    }

    public String getId(){
        return idT.getText();
    }

    public void setCreateEmpBtnListener(ActionListener listener) {
        createB.addActionListener(listener);
    }

    public void setUpdateEmpBtnListener(ActionListener listener) {
        updateB.addActionListener(listener);
    }

}
