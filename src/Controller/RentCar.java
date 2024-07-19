package Controller;

import Model.*;
import Model.JButton;
import Model.JLabel;

import javax.swing.*;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class RentCar implements Operation {

    private JTextField brand, model, color, year, price;
    private Database database = new Database();
    private JFrame frame;

    @Override
    public void operation(Database database, JFrame f, User user) {

        this.database = database;
        this.frame = new JFrame("Rent Car");
        frame.setSize(600, 650);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(250, 206, 27));
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Rent Car", 35);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(8, 2, 15, 15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("ID", 22));
        String[] ids;
        ArrayList<Integer> idsArray = new ArrayList<>();
        try {
            ResultSet rs0 = database.getStatement().executeQuery("SELECT `ID`, `Available` FROM `cars`;");
            while (rs0.next()) {
                if (rs0.getInt("Available") < 2) {
                    idsArray.add(rs0.getInt("ID"));
                }
            }
        } catch (Exception e0) {
            JOptionPane.showMessageDialog(frame, e0.getMessage());
            frame.dispose();
        }
        ids = new String[idsArray.size() + 1];
        ids[0] = " ";
        for (int i = 0; i < idsArray.size(); i++) {
            ids[i+1] = String.valueOf(idsArray.get(i));
        }

        Model.JComboBox id = new Model.JComboBox(ids, 22);
        id.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDate(id.getSelectedItem().toString());
            }
        });
        panel.add(id);

        panel.add(new JLabel("Brand", 22));
        brand = new JTextField(22);
        brand.setEditable(false);
        panel.add(brand);
        panel.add(new JLabel("Model", 22));
        model = new JTextField(22);
        model.setEditable(false);
        panel.add(model);
        panel.add(new JLabel("Color", 22));
        color = new JTextField(22);
        color.setEditable(false);
        panel.add(color);
        panel.add(new JLabel("Year", 22));
        year = new JTextField(22);
        year.setEditable(false);
        panel.add(year);
        panel.add(new JLabel("Price per hour", 22));
        price = new JTextField(22);
        price.setEditable(false);
        panel.add(price);

        panel.add(new JLabel("Hours:", 22));
        JTextField hours = new JTextField(22);
        panel.add(hours);

        JButton showCars = new JButton("Show All Cars", 22);
        showCars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewCars().operation(database, frame, user);
            }
        });
        panel.add(showCars);

        JButton confirm = new JButton("Confirm", 22);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(id.getSelectedItem().toString().equals(" ")){
                    JOptionPane.showMessageDialog(frame, "Please enter a ID");
                    return;
                }
                if(hours.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Please enter a hour");
                }
                int hoursInt;
                try{
                    hoursInt = Integer.parseInt(hours.getText());
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(frame, "Hours must be Int");
                    return;
                }
                try{
                    ResultSet rs0 = database.getStatement().executeQuery("SELECT * FROM `cars` WHERE `ID` = '"+id.getSelectedItem().toString()+"';");
                    rs0.next();
                    Car car = new Car();
                    car.setId(rs0.getInt("ID"));
                    car.setBrand(rs0.getString("Brand"));
                    car.setModel(rs0.getString("Model"));
                    car.setColor(rs0.getString("Color"));
                    car.setYear(rs0.getInt("Year"));
                    car.setPrice(rs0.getDouble("Price"));
                    car.setAvailable(rs0.getInt("Available"));
                if(car.getAvailable() != 0){
                    JOptionPane.showMessageDialog(frame, "Car isn't available");
                    return;
                }

                ResultSet rs1 = database.getStatement().executeQuery("SELECT COUNT(*) FROM `rents`;");
                rs1.next();
                int ID = rs1.getInt("COUNT(*)");
                double total = car.getPrice()*hoursInt;
                Rent rent = new Rent();
                String insert = "INSERT INTO `rents`(`ID`, `User`, `Car`, `DateTime`, `Hours`, `Total`, `Status`) VALUES ('"+ID+"','"+user.getID()+"','"+car.getId()+"','"+rent.getDateTime()+"','"+hoursInt+"','"+total+"','0');";
                database.getStatement().execute(insert);
                JOptionPane.showMessageDialog(frame, "Rent successfully" + "\nTotal = " +total+"$");
                frame.dispose();
                }catch(SQLException exe){
                    JOptionPane.showMessageDialog(frame, exe.getMessage());
                }
            }
        });
        panel.add(confirm);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.requestFocus();
    }

    private void updateDate(String ID) {

        if (ID.equals("")) {
            brand.setText(" ");
            model.setText(" ");
            color.setText(" ");
            year.setText(" ");
            price.setText(" ");
        } else {
            try {
                ResultSet rs1 = database.getStatement().executeQuery("SELECT * FROM `cars` WHERE `ID` = '" + ID + "';");
                rs1.next();
                Car car = new Car();
                car.setId(rs1.getInt("ID"));
                brand.setText(rs1.getString("Brand"));
                model.setText(rs1.getString("Model"));
                color.setText(rs1.getString("Color"));
                year.setText(String.valueOf(rs1.getInt("Year")));
                price.setText(String.valueOf(rs1.getDouble("Price")) + " $");
            }catch(Exception e){
                JOptionPane.showMessageDialog(frame, e.getMessage());
                frame.dispose();
            }
        }
    }

}
