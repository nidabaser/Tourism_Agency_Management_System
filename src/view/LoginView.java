package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

/**
 * @author Nida Başer
 * April 2024
 */

public class LoginView extends Layout {

    private JPanel container;

    private JLabel lbl_welcome_1;

    private JLabel lbl_welcome_2;

    private JLabel lbl_username;

    private JTextField fld_username;

    private JLabel lbl_password;

    private JButton btn_login;

    private JPasswordField fld_password;

    private final UserManager userManager;

    public LoginView() {

        this.userManager = new UserManager();

        this.add(container);

        this.guiInitialize(500, 500);

        btn_login.addActionListener(e -> {

            if (Helper.isFieldEmpty(this.fld_username) || Helper.isFieldEmpty(this.fld_password)) {

                Helper.showMsg("Please fill in all fields !");

            } else {

                User loginUser = this.userManager.findByLogin(this.fld_username.getText(), this.fld_password.getText());

                if (loginUser == null) {

                    Helper.showMsg("Wrong username or password");

                } else {

                    if (loginUser.getRole().toString().equals("Admin") || loginUser.getRole().toString().equals("ADMIN") || loginUser.getRole().toString().equals("admin")) {

                        AdminView adminView = new AdminView(loginUser);

                        adminView.setVisible(true);

                        dispose();

                    } else {

                        EmployeeView employeeView = new EmployeeView(loginUser);

                        employeeView.setVisible(true);

                        dispose();

                    }
                }
            }

        });
    }

}
