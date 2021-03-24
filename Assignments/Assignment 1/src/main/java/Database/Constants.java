package Database;

import java.util.*;

import static Database.Constants.Rights.*;
import static Database.Constants.Roles.*;

public class Constants {


    public static class Schemas {
        public static final String TEST = "test_bank";
        public static final String PRODUCTION = "bank";

        public static final String[] SCHEMAS = new String[]{TEST, PRODUCTION};
    }

    public static class Tables {
        public static final String CLIENT = "client";
        public static final String ACCOUNT = "account";
        public static final String USER = "user";
        public static final String ROLE = "role";
        public static final String RIGHT = "right";
        public static final String ROLE_RIGHT = "role_right";
        public static final String USER_ROLE = "user_role";

        public static final String[] ORDERED_TABLES_FOR_CREATION = new String[]{USER, ROLE, RIGHT, ROLE_RIGHT, USER_ROLE, CLIENT, ACCOUNT};
    }

    public static class Roles {
        public static final String ADMINISTRATOR = "administrator";
        public static final String EMPLOYEE = "employee";

        public static final String[] ROLES = new String[]{ADMINISTRATOR, EMPLOYEE};
    }

    public static class Admins{
        public static final String ADMIN1_USER = "george@gmail.com";
        public static final String ADMIN2_USER = "george1@gmail.com";
        public static final String ADMIN1_PASSWORD = "Parolaluigeorge1!";
        public static final String ADMIN2_PASSWORD = "Parolaluigeorge2!";
    }

    public static class Rights {
        public static final String CREATE_USER = "create_user";
        public static final String READ_USER = "read_user";
        public static final String DELETE_USER = "delete_user";
        public static final String UPDATE_USER = "update_user";

        public static final String GENERATE_REPORT = "generate_report";

        public static final String ADD_CLIENT = "add_client";

        public static final String UPDATE_NAME = "update_name";
        public static final String UPDATE_IDENTITY = "update_identity";
        public static final String UPDATE_PNC = "update_pnc";
        public static final String UPDATE_ADDRESS = "update_address";

        public static final String VIEW_CLIENT = "view_client";

        public static final String CREATE_ACCOUNT = "create_account";
        public static final String DELETE_ACCOUNT = "delete_account";

        public static final String UPDATE_IDENTIFICATION = "update_identification";
        public static final String UPDATE_TYPE = "update_type";
        public static final String UPDATE_BALANCE = "update_balance";
        public static final String UPDATE_DATE = "update_date";

        public static final String VIEW_ACCOUNT = "view_account";

        public static final String TRANSFER_MONEY = "transfer_money";

        public static final String PROCESS_BILL = "process_bill";

        public static final String[] RIGHTS = new String[]{
                CREATE_USER, READ_USER, DELETE_USER, UPDATE_USER, GENERATE_REPORT,
                ADD_CLIENT, UPDATE_NAME, UPDATE_IDENTITY, UPDATE_PNC, UPDATE_ADDRESS,
                VIEW_CLIENT,
                CREATE_ACCOUNT, DELETE_ACCOUNT,
                UPDATE_IDENTIFICATION, UPDATE_TYPE, UPDATE_BALANCE, UPDATE_DATE,
                VIEW_ACCOUNT,
                TRANSFER_MONEY, PROCESS_BILL};

        public static final String[] ADMIN_RIGHTS = new String[]{CREATE_USER, READ_USER, DELETE_USER, UPDATE_USER, GENERATE_REPORT};

        public static final String[] USER_RIGHTS = new String[]{
                ADD_CLIENT, UPDATE_NAME, UPDATE_IDENTITY, UPDATE_PNC, UPDATE_ADDRESS,
                VIEW_CLIENT,
                CREATE_ACCOUNT, DELETE_ACCOUNT,
                UPDATE_IDENTIFICATION, UPDATE_TYPE, UPDATE_BALANCE, UPDATE_DATE,
                VIEW_ACCOUNT,
                TRANSFER_MONEY, PROCESS_BILL};
    }

    public static Map<String, List<String>> getRolesRights(){
        Map <String, List<String>> ROLES_RIGHTS = new HashMap<>();

        for (String role : ROLES) {
            ROLES_RIGHTS.put(role, new ArrayList<>());
        }

        ROLES_RIGHTS.get(ADMINISTRATOR).addAll(Arrays.asList(ADMIN_RIGHTS));
        ROLES_RIGHTS.get(EMPLOYEE).addAll(Arrays.asList(USER_RIGHTS));

        return ROLES_RIGHTS;
    }
}
