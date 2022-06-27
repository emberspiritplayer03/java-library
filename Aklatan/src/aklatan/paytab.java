package aklatan;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class paytab extends JFrame implements ActionListener {

    JPanel pan = new JPanel(new GridLayout(8, 2, 4, 4));

    JLabel id = new JLabel("BORROW ID");
    JTextField txtID = new JTextField();

    JLabel ID = new JLabel("STUDENT ID");
    JTextField stxtID = new JTextField();

    JLabel tit = new JLabel("TITLE");
    JTextField title = new JTextField();

    JLabel amt = new JLabel("AMOUNT");
    JTextField amount = new JTextField();

    JLabel blank = new JLabel();
    JButton ret = new JButton("RETURN");

    public paytab() {
        ret.addActionListener(this);

        pan.add(id);
        pan.add(txtID);

        pan.add(ID);
        pan.add(stxtID);

        pan.add(tit);
        pan.add(title);

        pan.add(amt);
        pan.add(amount);
        pan.add(ret);
        add(pan);
        pan.setBackground(Color.LIGHT_GRAY);
        setVisible(true);
        setTitle("Payment and Return");
        setSize(400, 200);
        setLocationRelativeTo(null);
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/download.jpg"));
        setIconImage(image);

    }

    public void actionPerformed(ActionEvent evt) {
        Object ob = evt.getSource();
        Date date = new Date();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        if (ob == ret) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aklatan", "root", "");

                Statement s = con.createStatement();
                Statement st = con.createStatement();

                s.execute("select * from stu where id = '" + stxtID.getText() + "'");
                ResultSet rs = s.getResultSet();

                st.execute("select * from borr where brwID = '" + txtID.getText() + "'");
                ResultSet r = st.getResultSet();

                if (rs.next() && r.next()) {

                    int p = r.getInt(5);
                    int pen = Integer.parseInt(amount.getText());
                    if (p <= pen) {
                        s.execute("INSERT into returns (borrowID,studID,title,retDate) values ('" + txtID.getText() + "','" + stxtID.getText() + "','" + title.getText() + "','" + time + "')");
                        s.execute(" UPDATE aklat SET borrow = borrow-1 WHERE title ='" + title.getText() + "'");
                        s.execute("DELETE FROM borr where brwID = '" + txtID.getText() + "'");

                        JOptionPane.showMessageDialog(null, "Successfully Returned", "Thank You", JOptionPane.INFORMATION_MESSAGE);
                        txtID.setText("");
                        stxtID.setText("");
                        title.setText("");
                        amount.setText("");

                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient Payment", "Need more money", JOptionPane.INFORMATION_MESSAGE);
                        txtID.setText("");
                        stxtID.setText("");
                        title.setText("");
                        amount.setText("");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Record not Found", "Record not Found", JOptionPane.ERROR_MESSAGE);
                    txtID.setText("");
                    stxtID.setText("");
                    title.setText("");
                    amount.setText("");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "OOOPS! SOMETHING WENT WRONG TRY AGAIN", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
