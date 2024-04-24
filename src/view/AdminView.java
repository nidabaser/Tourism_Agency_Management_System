package view;

import core.ComboItem;
import core.Helper;
import entity.*;
import business.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class AdminView extends Layout {

    private JPanel container;

    private JComboBox cmb_users;

    private JTabbedPane tabbedPane1;

    private JScrollPane scroll_user;

    private JTable table_user;

    private JButton btn_exit;

    private JButton btn_clear;

    private JLabel lbl_welcome;

    private User user;

    private DefaultTableModel tableModel_user;

    private UserManager userManager;

    private JPopupMenu user_menu;

    private Object[] col_user;

    public AdminView(User user) {

        this.user = user;

        this.userManager = new UserManager();

        this.tableModel_user = new DefaultTableModel();

        this.add(container);

        this.guiInitialize(1000, 500);

        if (this.user == null) {
            dispose();
        }

        this.lbl_welcome.setText(" WELCOME ADMINISTRATOR " + this.user.getUsername().toUpperCase());

        // List of Users
        loadUserTable();

        // User Components
        loadUserComponent();

        // Searching by role of users
        loadRoleFilter();

    }

    public void loadUserTable() {

        col_user = new Object[]{"ID", "USERNAME", "PASSWORD", "ROLE"};

        ArrayList<Object[]> userList = this.userManager.getForTable(col_user.length, this.userManager.findAll());

        createTable(this.tableModel_user, this.table_user, col_user, userList);

    }

    public void loadUserComponent() {

        tableRowSelect(this.table_user);

        this.user_menu = new JPopupMenu();

        this.user_menu.add("New").addActionListener(e -> {

            UserView userView = new UserView(new User());

            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    loadUserTable();

                }
            });
        });

        this.user_menu.add("Update").addActionListener(e -> {

            int selectModelId = this.getTableSelectedRow(table_user, 0);

            UserView userView = new UserView(this.userManager.getById(selectModelId));

            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    loadUserTable();

                }
            });

            loadUserTable();

        });

        this.user_menu.add("Delete").addActionListener(e -> {

            if (Helper.confirm("sure")) {

                int selectCarId = this.getTableSelectedRow(table_user, 0);

                if (this.userManager.delete(selectCarId)) {

                    Helper.showMsg("Done");

                    loadUserTable();

                } else {

                    Helper.showMsg("Error");
                }
            }
        });

        this.table_user.setComponentPopupMenu(this.user_menu);

        this.cmb_users.addActionListener(e -> {

            entity.User.Role selectedRole = (entity.User.Role) cmb_users.getSelectedItem();

            if (selectedRole != null) {

                if (selectedRole == entity.User.Role.ADMIN) {

                    ArrayList<User> userList = this.userManager.findByAdmin();

                    loadUsersByRole(userList);

                } else if (selectedRole == entity.User.Role.EMPLOYEE) {

                    ArrayList<User> userList = this.userManager.findByEmployee();

                    loadUsersByRole(userList);

                }
            }
        });

        btn_clear.addActionListener(e -> {

            this.cmb_users.setSelectedItem(null);

            loadUserTable();

        });

        btn_exit.addActionListener(e -> {

            LoginView loginView = new LoginView();

            dispose();

        });
    }

    public void loadRoleFilter() {

        this.cmb_users.setModel(new DefaultComboBoxModel<>(User.Role.values()));

        this.cmb_users.setSelectedItem(null);

    }

    private void loadUsersByRole(ArrayList<User> userList) {

        tableModel_user.setRowCount(0);

        for (User user : userList) {

            Object[] rowData = {user.getId(), user.getUsername(), user.getPassword(), user.getRole()};

            tableModel_user.addRow(rowData);

        }

        table_user.setModel(tableModel_user);

    }
}