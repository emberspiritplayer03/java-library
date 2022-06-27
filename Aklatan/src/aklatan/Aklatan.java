package aklatan;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Aklatan extends JFrame implements ActionListener {

    JLabel name = new JLabel("Username");
    JTextField uname = new JTextField();

    JLabel pasy = new JLabel("Password");
    JPasswordField pass = new JPasswordField();

    JCheckBox loger = new JCheckBox("Log in as user");
    JButton login = new JButton("LogIn");

    public Aklatan() {
        login.addActionListener(this);
        
        Panel pan = new Panel(new GridLayout(3, 2, 0, 0));
        
        pan.add(name).setBackground(Color.LIGHT_GRAY);
        pan.add(uname).setBackground(Color.LIGHT_GRAY);

        pan.add(pasy).setBackground(Color.LIGHT_GRAY);
        pan.add(pass).setBackground(Color.LIGHT_GRAY);
        
        pan.add(loger).setBackground(Color.LIGHT_GRAY);
        pan.add(login).setBackground(Color.DARK_GRAY);

        add(pan);
        
        
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/download.jpg"));
        setIconImage(image);
        pan.setBackground(Color.LIGHT_GRAY);
    }

    public void actionPerformed(ActionEvent evt) {

        Object o = evt.getSource();

        if (o == login) {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aklatan", "root", "");

                Statement s = con.createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM admin where username  = '" + uname.getText() + "' and passwordx = password('" + pass.getText() + "')");

                Statement st = con.createStatement();
                ResultSet r = st.executeQuery("SELECT * FROM stu where username = '" + uname.getText() + "' and passwordx = password('" + pass.getText() + "')");

                if (rs.next() && !loger.isSelected()) {
                    menu m = new menu();
                    this.dispose();
                } else {

                    if (r.next() && loger.isSelected()) {
                        usermenu um = new usermenu();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "LOG IN FAILED", "Invalid ID", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "LOG IN FAILED" + e.getMessage(), "Please Check your inputs", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public static void main(String[] args) {
        Aklatan a = new Aklatan();
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setTitle("Library System");
        a.setSize(300, 150);
        a.setLocationRelativeTo(null);
        a.show();
    }

}
