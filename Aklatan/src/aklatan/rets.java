

package aklatan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class rets extends JFrame implements ActionListener {
    JPanel pan = new JPanel(new GridLayout(8,2, 4, 4));
   
    JLabel bR = new JLabel("Borrow ID");
    JTextField br = new JTextField();

    JLabel num = new JLabel("Student ID");
    JTextField id = new JTextField();
    
    JLabel tit = new JLabel("Title");
    JTextField title = new JTextField();

    JLabel bd = new JLabel("BorrowDate");
    JLabel bdt = new JLabel();

    JLabel rt = new JLabel("Return Date");
    JLabel rdt = new JLabel();
    
    JLabel blank = new JLabel();
    JButton ret = new JButton("RETURN");
    
    public rets(){
        //Date date = new Date();
       // String Time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        long diff;
        int count = 0;
        int diffy;
        int op = 0;
        
        ret.addActionListener(this);
        
        pan.add(bR);
        pan.add(br);
        
        pan.add(num);
        pan.add(id);
        
        pan.add(tit);
        pan.add(title);
        
               
        pan.add(blank);
        pan.add(ret);
        
        add(pan);
        pan.setBackground(Color.LIGHT_GRAY);
        setVisible(true);
        setTitle("Return");
        setSize(500,250);
        setLocationRelativeTo(null);
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/download.jpg"));
        setIconImage(image);
        
         try{
             
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aklatan", "root", "");
                        Statement s = con.createStatement();
                        Statement z = con.createStatement();
                        Statement st = con.createStatement();
                       // Statement ss = con.createStatement();
                       // Statement s1 = con.createStatement();
                        
                        String counter = "select count(*) as count from borr";
                        ResultSet r = st.executeQuery(counter);
                        
                        if(r.next()){
                            count = r.getInt("count");
                        }
                        
                        int penalties[] = new int[count];
                        int nums[] = new int[count];
                        
                        Date date = new Date();
                        SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                        ResultSet rs = s.executeQuery("select * from borr");
                        
                        
                
                        
                        for(int x = 0; rs.next() ;x++){
                        //op = 0;    
                        long d1 = formater.parse(rs.getString("borrow_date")).getTime();
                        long d2 = formater.parse(time).getTime();

                        long dayz = Math.abs((d1-d2)/(1000*60*60*24));
                        
                            if(dayz > 2){
                                    penalties[x] +=10*dayz;
                                    //JOptionPane.showMessageDialog(null,"PLEASE PAY YOUR PENALTY","PENALTY",JOptionPane.ERROR_MESSAGE);
                                }
                            
                                else{
                                    penalties[x] += 0;
                                }
                                nums[x] = rs.getInt(1);
                        
                        }
                        for(int x =0; x<count;x++){
                            z.execute("update borr set penalties = "+penalties[x]+" where brwID ="+nums[x]+"");             
                        }
                         //ResultSet rss = s1.executeQuery("select id from stu ");
                         //ss.execute("update stu set penalty = "+penalties+" where id = '"+id.getText()+"'");
                            
                        
         }
         catch(Exception ek){
                        JOptionPane.showMessageDialog(null,ek.getMessage(),"OOOPS! SOMETHING WENT WRONG TRY AGAIN",JOptionPane.ERROR_MESSAGE); 
         }
    }
    public void actionPerformed(ActionEvent evt){
        Object ob = evt.getSource();
        
        Date date = new Date();
        long diff;
        int days;
        int diffy;
        int op = 0;
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        
                
                if(ob == ret){
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aklatan", "root", "");
                        
                        Statement s = con.createStatement();
                        Statement st = con.createStatement();
                        
                        
                        s.execute("select * from stu where id = '"+id.getText()+"'");
                        ResultSet rs = s.getResultSet();
                        
                        st.execute("select * from borr where brwID = '"+br.getText()+"'");
                        ResultSet r = st.getResultSet();
                        
                        
                                if(rs.next() && r.next()){
                                   if(r.getInt(5) == 0){ 
                                    s.execute("INSERT into returns (borrowID,studID,title,retDate) values ('"+br.getText()+"','"+id.getText()+"','"+title.getText()+"','"+time+"')");
                                    s.execute(" UPDATE aklat SET borrow = borrow-1 WHERE title ='" + title.getText() + "'");
                                    s.execute("DELETE FROM borr where brwID = '" + br.getText() + "'");

                                     JOptionPane.showMessageDialog(null, "Successfully Returned", "Thank You", JOptionPane.INFORMATION_MESSAGE);
                                     
                                }
                                   else{
                                       JOptionPane.showMessageDialog(null,"PLEASE PAY YOUR PENALTY","PENALTY",JOptionPane.ERROR_MESSAGE);
                                       paytab pt = new paytab();
                                       this.dispose();
                                   }
                                }
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null,e.getMessage(),"OOOPS! SOMETHING WENT WRONG TRY AGAIN",JOptionPane.ERROR_MESSAGE); 
                    }
                }
    }
    
    
}
