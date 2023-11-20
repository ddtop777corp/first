package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS crud(" +
                "id SERIAL PRIMARY KEY, name TEXT," +
                "email TEXT, age SMALLINT);";
        try (Connection connection = Util.getNewConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.execute(sql);

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void dropUsersTable() {
        try (Connection connection = Util.getNewConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS crud");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void saveUser(String name, byte age, String email ) {
        try (Connection connection = Util.getNewConnection();
             PreparedStatement pstm = connection.prepareStatement("INSERT INTO crud (name, age, email) VALUES (?, ?, ?)")) {
            pstm.setString(1, name);
            pstm.setByte(2, age);
            pstm.setString(3, email);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getNewConnection();
                PreparedStatement pstm = connection.prepareStatement("DELETE FROM crud WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getNewConnection();
                ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM crud")) {
            while(resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                         resultSet.getByte("age"),resultSet.getString("email"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getNewConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE crud");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
