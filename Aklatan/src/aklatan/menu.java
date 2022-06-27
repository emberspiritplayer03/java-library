package aklatan;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class menu extends JFrame implements ActionListener {

    JMenuBar title = new JMenuBar();

    JMenu file = new JMenu("File");
    JMenuItem studs = new JMenuItem("Students");
    JMenuItem book = new JMenuItem("Books");

    JMenu acc = new JMenu("Accounts");
    JMenuItem bor = new JMenuItem("Borrow");
    JMenuItem ret = new JMenuItem("Return");
    JMenuItem vpen = new JMenuItem("View Students with/without Penalties");

    JMenuItem log = new JMenuItem("Log Out");

    public menu() {
        studs.addActionListener(this);
        book.addActionListener(this);

        bor.addActionListener(this);
        ret.addActionListener(this);
        vpen.addActionListener(this);
        log.addActionListener(this);

        Panel pan = new Panel(new GridLayout(4, 3, 0, 0));
        pan.setBackground(Color.black);
        

        title.add(file);
        title.add(acc);
        title.add(log);

        file.add(studs);
        file.add(book);

        acc.add(bor);
        acc.add(ret);
        acc.add(vpen);
        setJMenuBar(title);

        add(pan);

        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/download.jpg"));
        setIconImage(image);

        setVisible(true);
        
        setSize(300, 300);
        setLocationRelativeTo(null);

    }

    public void actionPerformed(ActionEvent evt) {

        Object o = evt.getSource();

        if (o == studs) {

            this.dispose();
            student s = new student();
            s.show();

        } else if (o == book) {
            books b = new books();
        } else if (o == bor) {
            borrow bo = new borrow();
        } else if (o == ret) {
            rets r = new rets();
        } else if (o == log) {

            Aklatan a = new Aklatan();
            a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            a.setTitle("Library System");
            a.setSize(300, 150);
            a.setLocationRelativeTo(null);
            a.show();

            this.dispose();
        } else if(o == vpen){
            penlist pen = new penlist();
            pen.show();
        }
        

    }

}
