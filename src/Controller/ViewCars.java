package Controller;

import Model.Car;
import Model.Database;
import Model.JLabel;
import Model.JTable;
import Model.Operation;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewCars implements Operation {


    @Override
    public void operation(Database database, JFrame f, User user) {

        JFrame frame = new JFrame("Cars");
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(250, 206, 27));
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Cars", 35);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        frame.add(title, BorderLayout.NORTH);

        String[] header = new String[]{
            "ID", "Brand", "Model", "Color", "Year", "Price", "Available"
        };

        String select = "SELECT * FROM `cars`;";
        ArrayList<Car> cars = new ArrayList<>();
        try{
            ResultSet rs = database.getStatement().executeQuery(select);
            while(rs.next()){
                 Car car = new Car();
                 car.setId(rs.getInt("ID"));
                 car.setBrand(rs.getString("Brand"));
                 car.setModel(rs.getString("Model"));
                 car.setColor(rs.getString("Color"));
                 car.setYear(rs.getInt("Year"));
                 car.setPrice(rs.getDouble("Price"));
                 int available = rs.getInt("Available");
                 if(available < 2){
                     car.setAvailable(available);
                     cars.add(car);
                 }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }

        String[][] carsData = new String[cars.size()][7];
        for(int j=0; j<cars.size(); j++){
            Car car = cars.get(j);
            if(car.getAvailable() < 2){
                carsData[j][0] = String.valueOf(car.getId());
                carsData[j][1] = car.getBrand();
                carsData[j][2] = car.getModel();
                carsData[j][3] = car.getColor();
                carsData[j][4] = String.valueOf(car.getYear());
                carsData[j][5] = String.valueOf(car.getPrice()) + " $";
                if(car.getAvailable() == 0){
                    carsData[j][6] = "Available";
                }else if(car.getAvailable() == 1){
                    carsData[j][6] = "Not Available";
                }
            }
        }

        Color color2 = new Color(252, 242, 202);
        JScrollPane panel = new JScrollPane(new JTable(carsData, header, Color.black, color2));
        panel.setBackground(null);
        panel.getViewport().setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
