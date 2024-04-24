package business;

import core.Helper;
import dao.UserDao;
import entity.User;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class UserManager {

    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User getById(int id){
        return this.userDao.getByID(id);
    }

    public User findByLogin(String username, String password) {
        return this.userDao.findByLogin(username, password);
    }

    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    public ArrayList<User>findByAdmin(){
        return this.userDao.findByAdmin();
    }

    public ArrayList<User>findByEmployee(){
        return this.userDao.findByEmployee();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> userList) {

        ArrayList<Object[]> userObjectList = new ArrayList<>();

        for (User object : userList) {

            int i = 0;

            Object[] rowObject = new Object[size];

            rowObject[i++] = object.getId();

            rowObject[i++] = object.getUsername();

            rowObject[i++] = object.getPassword();

            rowObject[i++] = object.getRole();

            userObjectList.add(rowObject);

        }

        return userObjectList;

    }

    public boolean delete(int id) {

        if (this.getById(id) == null) {

            Helper.showMsg("Error: User with ID " + id + " not found for deletion.");

            return false;

        }

        return this.userDao.delete(id);

    }

    public boolean update(User user) {

        if (this.getById(user.getId()) == null) {

            Helper.showMsg("Error: User with ID " + user.getId() + " not found for update.");

            return false;

        }

        return this.userDao.update(user);

    }

    public boolean save(User user) {

        if (this.getById(user.getId()) != null) {

            Helper.showMsg("Error: User with ID " + user.getId() + " already exists.");

            return false;

        }

        return this.userDao.save(user);

    }

}