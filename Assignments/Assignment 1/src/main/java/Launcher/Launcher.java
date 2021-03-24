package Launcher;

import Controller.LoginController;
import Database.Bootstrapper;
import Database.ConnectionFactory;
import Service.User.AuthenticationService;
import View.LoginView;
import repo.RightsRolesRepo;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.SQLException;

public class Launcher {
    public static void main(String[] args) throws SQLException {

        bootstrap(false);
        ComponentFactory componentFactory = ComponentFactory.instance(false);
    }

    private static void bootstrap(Boolean bootStrap){
        if (bootStrap){
            try {
                new Bootstrapper().execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
