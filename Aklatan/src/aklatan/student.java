package aklatan;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class student extends JFrame implements ActionListener {

    JPanel pan = new JPanel(new GridLayout(10, 2, 4, 4));
    JLabel id = new JLabel("Student ID");
    JLabel ID = new JLabel();

    JLabel us = new JLabel("Username");
    JTextField uname = new JTextField();

    JLabel pas = new JLabel("Password");
    JPasswordField pass = new JPasswordField();

    JLabel pas2 = new JLabel("Enter Password Again");
    JPasswordField pass2 = new JPasswordField();

    JLabel n = new JLabel("First Name");
    JTextField name = new JTextField();

    JLabel ln = new JLabel("Last Name");
    JTextField last = new JTextField();

    JLabel co = new JLabel("Course");
    String[] cour = {"BSIT", "ACCOUNTANCY", "HRDM", "EDUC-MATH", "EDUC-ENGLISH"};
    JComboBox course = new JComboBox(cour);

    JLabel lv = new JLabel("Year Level");
    String[] lev = {"First Year", "Second Year", "Third Year", "Fourth Year"};
    JComboBox level = new JComboBox(lev);

    JMenuBar mb = new JMenuBar();

    JMenu opt = new JMenu("Options");
    JMenuItem add = new JMenuItem("ADD");
    JMenuItem del = new JMenuItem("DELETE");
    JMenuItem ser = new JMenuItem("SEARCH");
    JMenuItem up = new JMenuItem("UPDATE");

    JMenuItem tab = new JMenuItem("STUDENT LIST");

    JMenuItem home = new JMenuItem("HOME");
    JMenuItem booky = new JMenuItem("BOOKS PAGE");
    JMenuItem log = new JMenuItem("LOG OUT");
    JMenuItem clear = new JMenuItem("CLEAR AND ENABLE");

    public student() {
        add.addActionListener(this);
        del.addActionListener(this);
        ser.addActionListener(this);
        up.addActionListener(this);
        home.addActionListener(this);
        booky.addActionListener(this);
        log.addActionListener(this);
        clear.addActionListener(this);
        tab.addActionListener(this);
        opt.add(add);
        opt.add(del);
        opt.add(ser);
        opt.add(up);

        /*
         if you want to add a space just place
         pnlMain.add(Box.createRigidArea(new Dimension(0, 5)));
         (x,y)
         */
        mb.add(opt);
        mb.add(tab);
        mb.add(home);
        mb.add(booky);
        mb.add(clear);
        mb.add(log);
        setJMenuBar(mb);
        pan.add(Box.createRigidArea(new Dimension(0, 10)));
        pan.add(Box.createRigidArea(new Dimension(0, 10)));
        pan.add(id);
        pan.add(ID);

        pan.add(us);
        pan.add(uname);

        pan.add(pas);
        pan.add(pass);

        pan.add(pas2);
        pan.add(pass2);

        pan.add(n);
        pan.add(name);

        pan.add(ln);
        pan.add(last);

        pan.add(co);
        pan.add(course);

        pan.add(lv);
        pan.add(level);

        add(pan);
        pan.setBackground(Color.LIGHT_GRAY);
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/download.jpg"));
        setIconImage(image);

        setTitle("Student Page");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        
        
    }

    public void actionPerformed(ActionEvent evt) {
        Object ob = evt.getSource();

        int count;
        //Checker for passwords
        //for first pass
        char[] paser = pass.getPassword();

        String key = "";
        for (int b = 0; b < paser.length; b++) {
            key += paser[b];
        }

        //for second pass
        char[] passer = pass2.getPassword();
        String key2 = "";
        for (int b = 0; b < passer.length; b++) {
            key2 += passer[b];
        }

        String c = course.getSelectedItem().toString();
        String l = level.getSelectedItem().toString();

        String furname = "com.mysql.jdbc.Driver";

        String counter = "select count(*) as count from stu";

        String SEARCH = "select * from stu where username = '" + uname.getText() + "' or name = '" + name.getText() + "' and surname = '" + last.getText() + "'";

        String DELETE = "DELETE from stu where username = '" + uname.getText() + "' or name = '" + name.getText() + "' and surname = '" + last.getText() + "'";
        String UPDATE = "UPDATE stu set username = '" + uname.getText() + "',passwordx = PASSWORD('" + pass.getText() + "'), name = '" + name.getText() + "',surnemae = '" + last.getText() + "',course = '" + c + "',year = '" + l + "'";

        if (ob == add) {
            try {
                Class.forName(furname);
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aklatan", "root", "");
                Statement s = con.createStatement();
                Statement z = con.createStatement();

                s.execute(counter);
                ResultSet rs = s.getResultSet();

                z.execute(SEARCH);
                ResultSet r = z.getResultSet();

                if (key.equalsIgnoreCase(key2)) {
                    if (!r.next()) {
                        if (rs.next()) {

                            count = rs.getInt("count") + 1;

                            ID.setText("PUP" + "-" + c + "-" + count);

                            s.execute("INSERT into stu (id,username,passwordx,name,surname,course,level) values ('" + ID.getText() + "','" + uname.getText() + "',PASSWORD('" + pass.getText() + "'),'" + name.getText() + "','" + last.getText() + "','" + c + "','" + l + "')");
                            JOptionPane.showMessageDialog(null, "STUDENT SUCCESSFULY ADDED", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Existing User", "Invalid User", JOptionPane.ERROR_MESSAGE);
                        uname.setText("");
                        name.setText("");
                        pass.setText("");
                        pass2.setText("");
                        last.setText("");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Password doesn't match", "Check you Password", JOptionPane.ERROR_MESSAGE);
                    uname.setText("");
                    pass.setText("");
                    pass2.setText("");
                    name.setText("");
                    last.setText("");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "OOOPS! SOMETHING WENT WRONG TRY AGAIN", JOptionPane.ERROR_MESSAGE);
                uname.setText("");
                pass.setText("");
                pass2.setText("");
                name.setText("");
                last.setText("");
            }

        } else if (ob == ser) {
            try {
                Class.forName(furname);
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aklatan", "root", "");

                Statement s = con.createStatement();
                s.executeQuery(SEARCH);
                ResultSet rs = s.getResultSet();

                if (rs.next()) {
                    ID.setText(rs.getString(1));
                    uname.setText(rs.getString(2));
                    name.setText(rs.getString(4));
                    last.setText(rs.getString(5));
                    course.setSelectedItem(rs.getString(6));
                    level.setSelectedItem(rs.getString(7));
                    JOptionPane.showMessageDialog(null, "Record Found", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                    uname.disable();
                    name.disable();
                    pass.disable();
                    pass2.disable();
                    last.disable();
                    course.disable();
                    level.disable();

                } else {
                    JOptionPane.showMessageDialog(null, "Record not Found", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    uname.setText("");
                    name.setText("");
                    pass.setText("");
                    pass2.setText("");
                    last.setText("");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "OOOPS! SOMETHING WENT WRONG TRY AGAIN", JOptionPane.ERROR_MESSAGE);
                uname.setText("");
                pass.setText("");
                pass2.setText("");
                name.setText("");
                last.setText("");
            }

        } else if (ob == del) {
            try {
                Class.forName(furname);
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aklatan", "root", "");

                Statement s = con.createStatement();
                Statement z = con.createStatement();
                s.execute(SEARCH);
                ResultSet r = s.getResultSet();

                if (r.next()) {
                    z.execute(DELETE);
                    JOptionPane.showMessageDialog(null, "Successfuly Deleted", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Record not Found", "ERROR 404", JOptionPane.ERROR_MESSAGE);
                    uname.setText("");
                    name.setText("");
                    pass.setText("");
                    pass2.setText("");
                    last.setText("");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "OOOPS! SOMETHING WENT WRONG TRY AGAIN", JOptionPane.ERROR_MESSAGE);
                uname.setText("");
                pass.setText("");
                pass2.setText("");
                name.setText("");
                last.setText("");
            }
        } else if (ob == up) {
            try {
                Class.forName(furname);
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aklatan", "root", "");
                Statement s = con.createStatement();
                Statement z = con.createStatement();
                s.executeQuery(SEARCH);
                ResultSet r = s.getResultSet();

                if (key.equalsIgnoreCase(key2)) {
                    if (!r.next()) {
                        z.execute(UPDATE);
                        JOptionPane.showMessageDialog(null, "Record Successfully Updated", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Existing User", "Invalid User", JOptionPane.ERROR_MESSAGE);
                        uname.setText("");
                        name.setText("");
                        pass.setText("");
                        pass2.setText("");
                        last.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Password doesn't match", "Check you Password", JOptionPane.ERROR_MESSAGE);
                    uname.setText("");
                    pass.setText("");
                    pass2.setText("");
                    name.setText("");
                    last.setText("");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "OOOPS! SOMETHING WENT WRONG TRY AGAIN", JOptionPane.ERROR_MESSAGE);
                uname.setText("");
                pass.setText("");
                pass2.setText("");
                name.setText("");
                last.setText("");
            }
        } else if (ob == home) {
            menu m = new menu();
            this.dispose();
        } else if (ob == booky) {
            books b = new books();
            this.dispose();
        } else if (ob == log) {
            Aklatan a = new Aklatan();
            a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            a.setTitle("Library System");
            a.setSize(300, 150);
            a.setLocationRelativeTo(null);
            a.show();

            this.dispose();
        } else if (ob == clear) {
            ID.setText("");
            uname.setText("");
            name.setText("");
            pass.setText("");
            pass2.setText("");
            last.setText("");

            uname.enable();
            name.enable();
            last.enable();
            course.enable();
            level.enable();
            pass.enable();
            pass2.enable();
        } else if (ob == tab) {
            studlist std = new studlist();
            std.show();
        }
    }

}
