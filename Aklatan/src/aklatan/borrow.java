package aklatan;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class borrow extends JFrame implements ActionListener {

    JPanel pan = new JPanel(new GridLayout(4, 2, 4, 4));

    JLabel num = new JLabel("Student ID");
    JTextField id = new JTextField();

    JLabel bookId = new JLabel("BOOK ID");
    JLabel book_ID = new JLabel();

    JLabel tit = new JLabel("Title");
    JTextField title = new JTextField();

    JLabel blank = new JLabel();
    JButton bor = new JButton("Borrow");

    public borrow() {
        bor.addActionListener(this);

        pan.add(num);
        pan.add(id);

        pan.add(bookId);
        pan.add(book_ID);

        pan.add(tit);
        pan.add(title);

        pan.add(blank);
        pan.add(bor);

        add(pan);

        pan.setBackground(Color.LIGHT_GRAY);

        setVisible(true);
        setTitle("Borrow");
        setSize(500, 250);
        setLocationRelativeTo(null);
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/download.jpg"));
        setIconImage(image);
    }

    public void actionPerformed(ActionEvent evt) {
        Object ob = evt.getSource();

        Date date = new Date();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        String searchBook = "select * from aklat where title = '" + title.getText() + "'";
        String searchID = "select * from stu where id = '" + id.getText() + "'";

        if (ob == bor) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aklatan", "root", "");

                Statement s = con.createStatement();
                Statement z = con.createStatement();
                Statement st = con.createStatement();

                s.execute(searchBook);
                ResultSet rs = s.getResultSet();

                z.execute(searchID);
                ResultSet r = z.getResultSet();

                if (rs.next()) {
                    if (r.next()) {
                        String tots = rs.getString(4);
                        String num = rs.getString(5);
                        if (!num.equalsIgnoreCase(tots)) {
                            st.execute("UPDATE aklat set borrow = borrow + 1 WHERE title ='" + title.getText() + "'");
                            st.execute("INSERT into borr(title,studID,borrow_date) values ('" + title.getText() + "','" + id.getText() + "','" + time + "')");

                            JOptionPane.showMessageDialog(null, "Successfuly Borrowed", "Borrowed", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Student not Found", "NOT FOUND", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Book Not Found", "NOT FOUND", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "OOOPS! SOMETHING WENT WRONG TRY AGAIN", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
