package Model;

import java.awt.*;

@SuppressWarnings("serial")
public class JTextField extends javax.swing.JTextField {

    public JTextField(int textSize) {
        super();
        setFont(new Font("SansSerif", Font.BOLD, textSize));
        setHorizontalAlignment(JTextField.CENTER);
        setBorder(null);
    }

}
