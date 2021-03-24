package repo.Client;

import Model.Builder.ClientBuilder;
import Model.Client;
import repo.EntityNotFoundException;

import java.sql.*;

public class ClientRepoMySQL implements ClientRepo{

    Connection connection;

    public ClientRepoMySQL(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean save(Client client){
        try {
            PreparedStatement saveStatement = connection.prepareStatement("INSERT INTO client values (null, ?, ?, ?, ?)");
            saveStatement.setString(1, client.getName());
            saveStatement.setString(2, client.getIdentity());
            saveStatement.setString(3, client.getPnc());
            saveStatement.setString(4, client.getAddress());
            saveStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateNameById(Long id, String name) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE client SET name = ? where id=" + id);
            updateStatement.setString(1, name);
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateIdentityById(Long id, String identity) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE client SET identity = ? where id=" + id);
            updateStatement.setString(1, identity);
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePncById(Long id, String pnc) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE client SET pnc = ? where id=" + id);
            updateStatement.setString(1, pnc);
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAddressById(Long id, String address) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE client SET address = ? where id=" + id);
            updateStatement.setString(1, address);
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Client viewClient(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String query = "Select * from client where id=" + id;
            ResultSet rs =statement.executeQuery(query);
            if (rs.next()){
                return getClientFromResultset(rs);
            }
            else
                throw new EntityNotFoundException(id, Client.class.getSimpleName());
        } catch (SQLException e){
            throw new EntityNotFoundException(id, Client.class.getSimpleName());
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from client where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client getClientFromResultset(ResultSet rs) throws SQLException {
        return new ClientBuilder()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setIdentity(rs.getString("identity"))
                .setPnc(rs.getString("pnc"))
                .setAddress(rs.getString("address"))
                .build();
    }
}
