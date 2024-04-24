package view;

import business.UserManager;
import entity.User;
import core.ComboItem;
import core.Helper;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class UserView extends Layout {

    private JPanel container;

    private JLabel lbl_user;

    private JTextField fld_username;

    private JLabel lbl_username;

    private JLabel lbl_password;

    private JTextField fld_password;

    private JLabel lbl_role;

    private JComboBox cmb_role;

    private JButton btn_save;

    private final User user;

    private UserManager userManager;

    public UserView(User user){

        this.user = user;

        this.userManager = new UserManager();

        this.add(container);

        this.guiInitialize(500, 500);

        if (user != null) {

            fld_username.setText(user.getUsername());

            fld_password.setText(user.getPassword());

            User.Role selectedItem = (user.getRole());

            cmb_role.getModel().setSelectedItem(selectedItem);

        }

        this.cmb_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));

        this.cmb_role.setSelectedItem(User.Role.EMPLOYEE);

        btn_save.addActionListener(e -> {

            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_username, this.fld_password})) {

                Helper.showMsg("Fill all blanks");

            } else {

                boolean result;

                this.user.setRole((User.Role) this.cmb_role.getSelectedItem());

                this.user.setUsername(this.fld_username.getText());

                this.user.setPassword(this.fld_password.getText());

                if (this.user.getId() != 0) {

                    result = this.userManager.update(this.user);

                } else {

                    result = this.userManager.save(this.user);

                }

                if (result) {

                    Helper.showMsg("DONE");

                    dispose();

                } else {

                    Helper.showMsg("ERROR");

                }

            }

        });

    }

}