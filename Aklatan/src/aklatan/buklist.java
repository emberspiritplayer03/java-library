

package aklatan;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;

public class buklist extends JFrame {
        public buklist(){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aklatan", "root", "");

            String q = "SELECT COUNT(*) AS num from aklat";
            String query = "SELECT * from aklat";
            Statement s = con.createStatement();
            s.executeQuery(q);

            ResultSet r = s.getResultSet();

            int row = 0;

            while(r.next()){
                row = r.getInt("num");
            }

            Object[][] val = new Object[row][5];
            r = s.executeQuery(query);

            int ruw = 0;
            while(r.next()) {
                val[ruw][0] = r.getObject(1);
                val[ruw][1] = r.getObject(2);
                val[ruw][2] = r.getObject(3);
                val[ruw][3] = r.getObject(4);
                val[ruw][4] = r.getObject(5);
                
                

                ruw++;
            }
            
            Object[] colName = new Object[5];

            colName[0] = "BOOK ID";
            colName[1] = "TITLE";
            colName[2] = "CATEGORY";
            colName[3] = "BORROWED";            
            colName[4] = "TOTAL";
            

            JPanel pan = new JPanel();
            pan.setBackground(Color.LIGHT_GRAY);
            JTable tbl = new JTable(val,colName);
            JScrollPane sp = new JScrollPane(tbl);
            tbl.setFillsViewportHeight(true);
            

            TableColumn col = null;
            col = tbl.getColumnModel().getColumn(0);
            col.setPreferredWidth(1000);

            col = tbl.getColumnModel().getColumn(1);
            col.setPreferredWidth(1000);

            col = tbl.getColumnModel().getColumn(2);
            col.setPreferredWidth(1000);

            col = tbl.getColumnModel().getColumn(3);
            col.setPreferredWidth(1000);
            
            col = tbl.getColumnModel().getColumn(4);
            col.setPreferredWidth(1000);
            
            
            pan.add(sp);

            add(pan);
            
            setLocationRelativeTo(null);
            
            Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/download.jpg"));
            setIconImage(image);
        }
        catch(Exception ek){
           
        }
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700,500);
        setLocationRelativeTo(null);
        setTitle("Book List");
        show();
    }
    
}
