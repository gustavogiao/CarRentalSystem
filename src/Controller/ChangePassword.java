package Controller;

import Model.Database;
import Model.JLabel;
import Model.JPasswordField;
import Model.Operation;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class ChangePassword implements Operation {


    @Override
    public void operation(Database database, JFrame f, User user) {

        JFrame frame = new JFrame("Change Password");
        frame.setSize(600, 380);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(250, 206, 27));
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Change Password", 35);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0,0,0));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Old Password:", 22));
        JPasswordField oldPassword = new JPasswordField(22);
        panel.add(oldPassword);
        panel.add(new JLabel("New Password:", 22));
        JPasswordField newPassword = new JPasswordField(22);
        panel.add(newPassword);
        panel.add(new JLabel("Confirm Password:", 22));
        JPasswordField confirmPassword = new JPasswordField(22);
        panel.add(confirmPassword);

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(cancel);
        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(oldPassword.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Old Password is Empty");
                    return;
                }
                if(newPassword.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "New Password is Empty");
                    return;
                }
                if(confirmPassword.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Confirm Password is Empty");
                    return;
                }
                if(!oldPassword.getText().equals(user.getPassword())){
                    JOptionPane.showMessageDialog(frame, "Incorrect Passowrd");
                    return;
                }
                if(!newPassword.getText().equals(confirmPassword.getText())){
                    JOptionPane.showMessageDialog(frame, "Passwords do not match");
                }
                try{
                    String update = "UPDATE `userss` SET `Password`='"+newPassword.getText()+"' WHERE `ID` = '"+user.getID()+"';";
                    database.getStatement().execute(update);
                    JOptionPane.showMessageDialog(frame, "Password updated successfully");
                    user.setPassword(newPassword.getText());
                    frame.dispose();
                }catch(SQLException e1){
                    JOptionPane.showMessageDialog(frame, e1.getMessage());
                }
            }
        });
        panel.add(confirm);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }
}

