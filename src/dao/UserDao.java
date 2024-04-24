package dao;

import core.Db;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class UserDao {

    private final Connection conn;

    public UserDao(){
        this.conn = Db.getInstance();
    }

    public ArrayList<User> findAll() {

        ArrayList<User> userList = new ArrayList<>();

        String sql = "SELECT * FROM public.user ORDER BY id ASC";

        try {

            ResultSet rs = this.conn.createStatement().executeQuery(sql);

            while (rs.next()) {

                User obj = this.match(rs);

                userList.add(obj);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return userList;

    }

    public User findByLogin(String username, String password){

        User obj = null;

        String query = "SELECT * FROM public.user WHERE username = ? AND password = ?";

        try{

            PreparedStatement pr = this.conn.prepareStatement(query);

            pr.setString(1,username);

            pr.setString(2, password);

            ResultSet rs = pr.executeQuery();

            if (rs.next()) {

                obj = this.match(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return obj;

    }

    public User match(ResultSet rs) throws SQLException {

        User obj = new User();

        obj.setId(rs.getInt("id"));

        obj.setUsername(rs.getString("username"));

        obj.setPassword(rs.getString("password"));

        obj.setRole(User.Role.valueOf(rs.getString("role")));

        return obj;

    }

    public ArrayList<User> selectByQuery(String query) {

        ArrayList<User> userList = new ArrayList<>();

        try {

            ResultSet rs = this.conn.createStatement().executeQuery(query);

            while (rs.next()) {

                userList.add(this.match(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return userList;

    }

    public ArrayList<User> findByAdmin(){

        ArrayList<User> userList = new ArrayList<>();

        String query = "SELECT * FROM public.user WHERE role = 'ADMIN' ORDER BY id ASC";

        try {

            ResultSet rs = this.conn.createStatement().executeQuery(query);

            while (rs.next()) {

                userList.add(this.match(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return userList;

    }

    public ArrayList<User> findByEmployee(){

        ArrayList<User> userList = new ArrayList<>();

        String query = "SELECT * FROM public.user WHERE role = 'EMPLOYEE' ORDER BY id ASC";

        try {

            ResultSet rs = this.conn.createStatement().executeQuery(query);

            while (rs.next()) {

                userList.add(this.match(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return userList;

    }

    public User getByID(int id) {

        User obj = null;

        String query = "SELECT * FROM public.user WHERE id = ? ";

        try {

            PreparedStatement ps = this.conn.prepareStatement(query);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                obj = this.match(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return obj;

    }

    public boolean delete(int carId) {

        String query = "DELETE FROM public.user WHERE id = ? ";

        try {

            PreparedStatement ps = this.conn.prepareStatement(query);

            ps.setInt(1, carId);

            return ps.executeUpdate() != -1;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;

    }

    public boolean update(User user) {

        String query = "UPDATE public.user SET " +

                "username = ?, " +

                "password = ?, " +

                "role = ? " +

                "WHERE id = ?";

        try {

            PreparedStatement ps = this.conn.prepareStatement(query);

            ps.setString(1, user.getUsername());

            ps.setString(2, user.getPassword());

            ps.setString(3, user.getRole().toString());

            ps.setInt(4,user.getId());

            return ps.executeUpdate() != -1;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;

    }

    public boolean save(User user) {

        String query = "INSERT INTO public.user " +

                "(" +

                "username," +

                "password," +

                "role" +

                ")" +

                "VALUES (?,?,?)";

        try {

            PreparedStatement ps = this.conn.prepareStatement(query);

            ps.setString(1, user.getUsername());

            ps.setString(2, user.getPassword());

            ps.setString(3, user.getRole().toString());

            return ps.executeUpdate() != -1;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;

    }

}