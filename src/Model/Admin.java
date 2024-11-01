package Model;
import Controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends User {

    private Operation[] operations = new Operation[] { new AddNewCar() , new ViewCars(), new UpdateCar(), new DeleteCar(), new AddNewAccount(1), new ShowAllRents(), new ShowSpecUserRents(), new EditUserData(), new ChangePassword()};


    private JButton[] btns = new JButton[] {
            new JButton("Add New Car",22),
            new JButton("View Cars",22),
            new JButton("Update Car",22),
            new JButton("Delete Car",22),
            new JButton("Add New Admin",22),
            new JButton("Show All Rents",22),
            new JButton("Show User's Rents",22),
            new JButton("Edit my Data",22),
            new JButton("Change Password",22),
    };

    public Admin(){
        super();
    }

    @Override
    public void showList(Database database, JFrame f) {

        JFrame frame = new JFrame("Admin Panel");
        frame.setSize(400, btns.length*80);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(250, 206, 27));
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome " + getFirstName(), 30);
        title.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(btns.length, 1, 15, 15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int i = 0; i < btns.length; i++) {
            final int j = i;
            JButton button = btns[i];
            panel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    operations[j].operation(database, frame, Admin.this);
                }
            });
        }
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
