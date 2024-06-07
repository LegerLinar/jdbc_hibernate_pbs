package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util dbUtil = new Util();
    private Connection conn = dbUtil.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String creator = "CREATE TABLE IF NOT EXISTS users "
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + "name VARCHAR(20) NOT NULL,"
            + "lastName VARCHAR(20) NOT NULL,"
            + "age SMALLINT(20) NOT NULL";
        try (Statement stat = conn.createStatement()) {
            stat.executeUpdate(creator);
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE users";
        try (Statement stat = conn.createStatement()) {
            stat.executeUpdate(drop);
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users "
            + "(id, name, lastName, age) "
            + "VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Statement stat = conn.createStatement()) {
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try(Statement stat = conn.createStatement()) {
            ResultSet result = stat.executeQuery(sql);
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setLastName(result.getString("lastName"));
                user.setAge(result.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        String sqlClean = "DELETE FROM users";
        try (Statement stat = conn.createStatement()) {
            stat.executeUpdate(sqlClean);
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }
}
