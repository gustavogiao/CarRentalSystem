package Model;

import javax.swing.*;
import javax.swing.JLabel;
import java.awt.*;

@SuppressWarnings({"rawtypes", "serial"})
public class JComboBox extends javax.swing.JComboBox {

    @SuppressWarnings("unchecked")
    public JComboBox(String itens[], int fontSize) {
        super(itens);
        setFont(new Font("SansSerif", Font.BOLD, fontSize));
        setBackground(Color.white);
        ((JLabel) getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);


    }

}
