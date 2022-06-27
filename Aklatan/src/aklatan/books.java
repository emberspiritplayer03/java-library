package aklatan;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class books extends JFrame implements ActionListener {

    JPanel pan = new JPanel(new GridLayout(6, 2, 4, 4));

    JLabel id = new JLabel("Book ID");
    JLabel ID = new JLabel();

    JLabel tit = new JLabel("Title");
    JTextField title = new JTextField();

    JLabel tot = new JLabel("Total of Books");
    JTextField total = new JTextField();

    JLabel cat = new JLabel("Category");
    String[] cate = {"Novel-Mystery", "Novel-Lovestory", "Mathematics", "Literature", "Science and Technology", "History","General Information","Fiction","Manga","Comics"};
    JComboBox category = new JComboBox(cate);

    JMenuBar mb = new JMenuBar();

    JMenu opt = new JMenu("Options");
    JMenuItem add = new JMenuItem("ADD");
    JMenuItem del = new JMenuItem("DELETE");
    JMenuItem ser = new JMenuItem("SEARCH");
    JMenuItem up = new JMenuItem("UPDATE");

    JMenuItem tab = new JMenuItem("BOOK LIST");

    JMenuItem home = new JMenuItem("HOME");
    JMenuItem stud = new JMenuItem("STUDENTS PAGE");
    JMenuItem log = new JMenuItem("LOG OUT");

    public books() {
        add.addActionListener(this);
        del.addActionListener(this);
        ser.addActionListener(this);
        up.addActionListener(this);
        home.addActionListener(this);
        stud.addActionListener(this);
        log.addActionListener(this);
        tab.addActionListener(this);

        opt.add(add);
        opt.add(del);
        opt.add(ser);
        opt.add(up);

        mb.add(opt);
        mb.add(tab);
        mb.add(home);
        mb.add(stud);
        mb.add(log);
        setJMenuBar(mb);

        pan.add(Box.createRigidArea(new Dimension(0, 8)));
        pan.add(Box.createRigidArea(new Dimension(0, 8)));

        pan.add(id);
        pan.add(ID);

        pan.add(tit);
        pan.add(title);

        pan.add(cat);
        pan.add(category);

        pan.add(tot);
        pan.add(total);

        add(pan);

        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/download.jpg"));
        setIconImage(image);
        pan.setBackground(Color.LIGHT_GRAY);
        setTitle("Book Page");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        Object ob = evt.getSource();

        int count;

        String categ = category.getSelectedItem().toString();
        String furname = "com.mysql.jdbc.Driver";

        String counter = "select count(*) as count from aklat";

        String DELETE = "DELETE FROM stu where title = '" + title.getText() + "'";
        String UPDATE = "UPDATE aklat set title = '" + title.getText() + "', category = '" + categ + "' where bookID ='"+ID.getText()+"'" ;

        if (ob == add) {
            try {
                Class.forName(furname);
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aklatan", "root", "");
                Statement s = con.createStatement();
                Statement z = con.createStatement();
                Statement st = con.createStatement();

                s.execute(counter);
                ResultSet rs = s.getResultSet();

                z.execute("select * from aklat where title = '" + title.getText() + "'");
                ResultSet r = z.getResultSet();

                if (!r.next()) {
                    if (rs.next()) {
                        count = rs.getInt("count");
                        ID.setText("PUP" + "-" + categ + "-" + count);
                       // String ADD = "INSERT into aklat(bookID,title,category,borrow,balik,total) values ('" + ID.getText() + "','" + title.getText() + "','" + categ + "','" + 0 + "','" + 0 + "','" + total.getText() + "')";
                        z.execute("INSERT into aklat(bookID,title,category,borrow,total) values ('" + ID.getText() + "','" + title.getText() + "','" + categ + "','"+0+"','" + total.getText() + "')");                        
                        JOptionPane.showMessageDialog(null, "Book Succesfully Added", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Existing User", "Invalid User", JOptionPane.ERROR_MESSAGE);
                    title.setText("");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "OOOPS! SOMETHING WENT WRONG TRY AGAIN", JOptionPane.ERROR_MESSAGE);
                title.setText("");
                total.setText("");

            }

        } else if (ob == ser) {
            try {
                Class.forName(furname);
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aklatan", "root", "");

                Statement s = con.createStatement();
                s.executeQuery("select * from aklat where title = '" + title.getText() + "'");
                ResultSet rs = s.getResultSet();

                if (rs.next()) {
                    ID.setText(rs.getString(1));
                    title.setText(rs.getString(2));
                    category.setSelectedItem(rs.getString(3));
                    total.setText(rs.getString(5));

                    JOptionPane.showMessageDialog(null, "Record Found", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Record Not Found", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    title.setText("");
                    total.setText("");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "OOOPS! SOMETHING WENT WRONG TRY AGAIN", JOptionPane.ERROR_MESSAGE);
                title.setText("");
                total.setText("");
            }
        } else if (ob == del) {
            try {
                Class.forName(furname);
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aklatan", "root", "");

                Statement s = con.createStatement();
                s.execute("select * from aklat where title = '" + title.getText() + "'");
                ResultSet r = s.getResultSet();

                if (r.next()) {
                    s.execute(DELETE);
                    JOptionPane.showMessageDialog(null, "Successfuly Deleted", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Record not Found", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    title.setText("");
                    total.setText("");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "OOOPS! SOMETHING WENT WRONG TRY AGAIN", JOptionPane.ERROR_MESSAGE);
                title.setText("");
                total.setText("");
            }
        } else if (ob == up) {
            try {
                Class.forName(furname);
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aklatan", "root", "");
                Statement s = con.createStatement();
                s.executeQuery("select * from aklat where title = '" + title.getText() + "'");
                ResultSet r = s.getResultSet();

                if (!r.next()) {
                    s.execute(UPDATE);
                    JOptionPane.showMessageDialog(null, "Record Successfully Updated", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Existing Book", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    title.setText("");
                    total.setText("");

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "OOOPS! SOMETHING WENT WRONG TRY AGAIN", JOptionPane.ERROR_MESSAGE);
                title.setText("");
                total.setText("");
            }
        } else if (ob == home) {
            menu m = new menu();
            this.dispose();
        } else if (ob == stud) {
            student s = new student();
            this.dispose();
        } else if (ob == log) {
            Aklatan a = new Aklatan();
            a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            a.setTitle("Library System");
            a.setSize(300, 150);
            a.setLocationRelativeTo(null);
            a.show();

            this.dispose();
        } else if (ob == tab) {
            buklist b = new buklist();
        }

    }
}
