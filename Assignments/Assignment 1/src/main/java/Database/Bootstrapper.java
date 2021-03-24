package Database;

import Model.Builder.UserBuilder;
import Model.Role;
import Model.User;
import Service.User.AuthenticationService;
import Service.User.AuthenticationServiceMySQL;
import repo.RightsRolesRepo;
import repo.RightsRolesRepoMySQL;
import repo.User.UserRepo;
import repo.User.UserRepoMySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static Database.Constants.Admins.*;
import static Database.Constants.Rights.RIGHTS;
import static Database.Constants.Roles.ADMINISTRATOR;
import static Database.Constants.Roles.ROLES;
import static Database.Constants.Schemas.SCHEMAS;
import static Database.Constants.getRolesRights;

public class Bootstrapper {

    private RightsRolesRepo rightsRolesRepo;
    private UserRepo userRepo;
    private AuthenticationService authenticationService;

    public void execute() throws SQLException {

        dropAll();

        bootstrapTables();

        bootstrapData();
    }

    private void dropAll() throws SQLException {
        for (String schema : SCHEMAS) {
            System.out.println("Dropping all tables in schema: " + schema);

            Connection connection = new ConnectionWrapper(schema).getConnection();
            Statement statement = connection.createStatement();

            String[] dropStatements = {
                    "TRUNCATE `role_right`;",
                    "DROP TABLE `role_right`;",
                    "TRUNCATE `right`;",
                    "TRUNCATE `user_role`;",
                    "DROP TABLE `user_role`;",
                    "TRUNCATE `role`;",
                    "DROP TABLE  `role`, `user`;",
                    "DROP TABLE `account`, `client`"
                   // "DROP TABLE `client`, `account`;"
            };

            Arrays.stream(dropStatements).forEach(dropStatement -> {
                try {
                    statement.execute(dropStatement);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println("Done table bootstrap");
    }

    private void bootstrapTables() throws SQLException {
        SQLTableCreationFactory sqlTableCreationFactory = new SQLTableCreationFactory();

        for (String schema : SCHEMAS) {
            System.out.println("Bootstrapping " + schema + " schema");


            ConnectionWrapper connectionWrapper = new ConnectionWrapper(schema);
            Connection connection = connectionWrapper.getConnection();

            Statement statement = connection.createStatement();

            for (String table : Constants.Tables.ORDERED_TABLES_FOR_CREATION) {
                String createTableSQL = sqlTableCreationFactory.getCreateSqlTable(table);
                statement.execute(createTableSQL);
            }
        }

        System.out.println("Done table bootstrap");
    }

    private void bootstrapData(){
        for (String schema: SCHEMAS){
            ConnectionWrapper connectionWrapper = new ConnectionWrapper(schema);
            rightsRolesRepo = new RightsRolesRepoMySQL(connectionWrapper.getConnection());
            userRepo = new UserRepoMySQL(connectionWrapper.getConnection(), rightsRolesRepo);
            authenticationService = new AuthenticationServiceMySQL(userRepo,rightsRolesRepo);

            bootstrapRoles();
            bootstrapRights();
            bootstrapRoleRight();
            bootstrapUsers();
        }
    }

    private void bootstrapRoles() {
        for (String role : ROLES) {
            rightsRolesRepo.addRole(role);
        }
    }

    private void bootstrapRights() {
        for (String right : RIGHTS) {
            rightsRolesRepo.addRight(right);
        }
    }

    private void bootstrapUsers() {
        Role adminRole = rightsRolesRepo.findRoleByTitle(ADMINISTRATOR);
        User user = new UserBuilder()
                .setUsername(ADMIN1_USER)
                .setPassword(authenticationService.encodePassword(ADMIN1_PASSWORD))
                .setRoles(Collections.singletonList(adminRole))
                .build();
        userRepo.save(user);

        user = new UserBuilder()
                .setUsername(ADMIN2_USER)
                .setPassword(authenticationService.encodePassword(ADMIN2_PASSWORD))
                .setRoles(Collections.singletonList(adminRole))
                .build();
        userRepo.save(user);
    }

    private void bootstrapRoleRight() {
        Map<String, List<String>> rolesRights = getRolesRights();

        for (String role : rolesRights.keySet()) {
            Long roleId = rightsRolesRepo.findRoleByTitle(role).getId();

            for (String right : rolesRights.get(role)) {
                Long rightId = rightsRolesRepo.findRightByTitle(right).getId();

                rightsRolesRepo.addRoleRight(roleId, rightId);
            }
        }
    }
}
