package core;

import javax.swing.*;

/**
 * @author Nida Başer
 * April 2024
 */

public class Helper {

    public static void setTheme() {

        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {

            if ("Nimbus".equals(info.getName())) {

                try {

                    UIManager.setLookAndFeel(info.getClassName());

                } catch (Exception e) {

                    System.out.println(e.getMessage());

                }
            }
        }
    }

    public static boolean isFieldEmpty (JTextField field) {

        return field.getText().trim().isEmpty();

    }

    public static void showMsg(String message) {

        JOptionPane.showMessageDialog(null,

                message,

                "Info",

                JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String string) {

        optionPaneTR();

        String message;

        if (string.equals("Sure")) {

            message = "Are you sure ?";

        } else {

            message = string;

        }

        return JOptionPane.showConfirmDialog(null,message,"Confirm Delete", JOptionPane.YES_NO_OPTION) == 0;

    }

    public static void optionPaneTR() {

        UIManager.put("OptionPane.okButtonText" , "OK");

        UIManager.put("OptionPane.yesButtonText" , "Yes");

        UIManager.put("OptionPane.noButtonText" , "No");
    }

    public static boolean isFieldListEmpty(JTextField[] jTextFields) {

        for (JTextField field : jTextFields) {

            if (isFieldEmpty(field)) {

                return true;

            }

        }

        return false;

    }
}