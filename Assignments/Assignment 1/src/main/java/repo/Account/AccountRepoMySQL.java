package repo.Account;

import Model.Account;
import Model.Builder.AccountBuilder;
import repo.EntityNotFoundException;

import java.sql.*;

public class AccountRepoMySQL implements AccountRepo {

    Connection connection;

    public AccountRepoMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Account account) {
        try{
            PreparedStatement saveStatement = connection.prepareStatement("INSERT INTO account values(null, ?, ?, ?, ?, ?) ");
            saveStatement.setString(1, account.getIdentification());
            saveStatement.setString(2, account.getType());
            saveStatement.setFloat(3, account.getBalance());
            saveStatement.setDate(4, (Date) account.getDate());
            saveStatement.setLong(5, account.getIdClient());
            saveStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public Account viewAccount(Long id) throws EntityNotFoundException {
        try{
            Statement statement = connection.createStatement();
            String sql = "Select * from account where id=" + id;
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){
                return getAccountFromResultset(rs);
            }
            else{
                throw new EntityNotFoundException(id, Account.class.getSimpleName());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new EntityNotFoundException(id, Account.class.getSimpleName());
        }
    }

    @Override
    public void deleteAccount(Long id) {

    }

    @Override
    public boolean updateIdentificationById(Long id, String identification) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE account SET identification = ? where id=" + id);
            updateStatement.setString(1, identification);
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTypeById(Long id, String type) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE account SET type = ? where id=" + id);
            updateStatement.setString(1, type);
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateBalanceById(Long id, Float balance) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE account SET balance = ? where id=" + id);
            updateStatement.setFloat(1, balance);
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateDateById(Long id, Date date) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE account SET date = ? where id=" + id);
            updateStatement.setDate(1, date);
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Account getAccountFromResultset(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getLong("id"))
                .setIdentification(rs.getString("identification"))
                .setType(rs.getString("type"))
                .setBalance(rs.getFloat("balance"))
                .setDate(rs.getDate("date"))
                .setIdClient(rs.getLong("idclient"))
                .build();
    }
}
