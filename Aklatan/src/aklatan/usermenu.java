package aklatan;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;


public class usermenu extends JFrame implements ActionListener {

    JPanel pan = new JPanel(new GridLayout(6, 2, 4, 4));
    JMenuBar mb = new JMenuBar();

    JMenuItem log = new JMenuItem("Log Out");
    JMenuItem view = new JMenuItem("View Books");
    JMenuItem ser = new JMenuItem("Search");

    JLabel id = new JLabel("BOOK ID");
    JTextField ID = new JTextField();

    JLabel bok = new JLabel("Book");
    JTextField book = new JTextField();

    JLabel cat = new JLabel("Category");
    String[] cate = {"Novel-Mystery", "Novel-Lovestory", "Mathematics", "Literature", "Science and Technology", "History"};
    JComboBox category = new JComboBox(cate);

    public usermenu() {
        log.addActionListener(this);
        view.addActionListener(this);
        ser.addActionListener(this);

        mb.add(ser);
        mb.add(view);
        mb.add(log);
        setJMenuBar(mb);

        pan.add(Box.createRigidArea(new Dimension(0, 8)));
        pan.add(Box.createRigidArea(new Dimension(0, 8)));
        pan.setBackground(Color.black);
        pan.add(id);
        pan.add(ID);

        pan.add(bok);
        pan.add(book);

        pan.add(cat);
        pan.add(category);

        add(pan);

        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/download.jpg"));
        setIconImage(image);
        
        
        show();
        setLocation(500, 250);
        setSize(300, 300);

    }

    public void actionPerformed(ActionEvent evt) {
        Object ob = evt.getSource();

        if (ob == ser) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aklatan", "root", "");

                Statement s = con.createStatement();
                s.executeQuery("select * from aklat where title = '" + book.getText() + "'");
                ResultSet rs = s.getResultSet();

                if (rs.next()) {
                    ID.setText(rs.getString(1));
                    book.setText(rs.getString(2));
                    category.setSelectedItem(rs.getString(3));
                    JOptionPane.showMessageDialog(null, "Record Found", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "Record Not Found", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "OOOPS! SOMETHING WENT WRONG TRY AGAIN", JOptionPane.ERROR_MESSAGE);
                book.setText("");
                ID.setText("");

            }
        } else if (ob == log) {

            Aklatan a = new Aklatan();
            a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            a.setTitle("Library System");
            a.setSize(300, 300);
            a.setLocationRelativeTo(null);
            a.show();

            this.dispose();
        } else if (ob == view) {
            buklist b = new buklist();
            
           
        }

    }

}
