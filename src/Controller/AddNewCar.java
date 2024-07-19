package Controller;

import Model.Database;
import Model.JLabel;
import Model.Operation;
import Model.User;
import Model.JButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddNewCar implements Operation {



    @Override
    public void operation(Database database, JFrame f, User user) {

        JFrame frame = new JFrame("Add New Car");
        frame.setSize(600, 525);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(250, 206, 27));
        frame.setLayout(new BorderLayout());

        Model.JLabel title = new JLabel("Add New Car", 35);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0,0,0));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(6,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Brand", 22));
        JTextField brand = new JTextField(22);
        panel.add(brand);
        panel.add(new JLabel("Model", 22));
        JTextField model = new JTextField(22);
        panel.add(model);
        panel.add(new JLabel("Color", 22));
        JTextField color = new JTextField(22);
        panel.add(color);
        panel.add(new JLabel("Year", 22));
        JTextField year = new JTextField(22);
        panel.add(year);
        panel.add(new JLabel("Price per hour", 22));
        JTextField price = new JTextField(22);
        panel.add(price);

        JButton cancel = new JButton("Cancel", 22);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(cancel);
        JButton confirm = new JButton("Add", 22);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(brand.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Please enter a brand");
                    return;
                }
                if(model.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Please enter a model");
                    return;
                }
                if(color.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Please enter a color");
                    return;
                }
                if(year.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Please enter a year");
                    return;
                }
                if(price.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Please enter a price");
                    return;
                }
                int yearInt;
                double priceDouble;
                try{
                    yearInt = Integer.parseInt(year.getText());
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(frame, "Year must be Int");
                    return;
                }
                try{
                    priceDouble = Double.parseDouble(price.getText());
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(frame, "Price must be Double");
                    return;
                }
                int available = 0;
                try{
                    ResultSet rs = database.getStatement().executeQuery("SELECT COUNT(*) FROM `cars`;");
                    rs.next();
                    int ID = rs.getInt("COUNT(*)");
                    String insert = "INSERT INTO `cars`(`ID`, `Brand`, `Model`, `Color`, `Year`, `Price`, `Available`) VALUES ('"+ID+"','"+brand.getText()+"', '"+model.getText()+"', '"+color.getText()+"', '"+yearInt+"', '"+priceDouble+"', '"+available+"')";
                    database.getStatement().execute(insert);
                    JOptionPane.showMessageDialog(frame, "Car Added Successfully");
                    frame.dispose();
                }catch(SQLException e3){
                    JOptionPane.showMessageDialog(frame, e3.getMessage());
                }
            }
        });
        panel.add(confirm);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}