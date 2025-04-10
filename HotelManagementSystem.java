package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HotelManagementSystem extends JFrame implements ActionListener {
   HotelManagementSystem(){
//       setSize(1366, 565);
//       setLocation(100, 100);
       setBounds(0, 0, 1600, 1200);
       setLayout(null);
       ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
       JLabel image = new JLabel(i1);
       image.setBounds(0, 0, 1600, 1200);
       add(image);
       
       JLabel text = new JLabel("HOTEL MANAGEMENT SYSTEM");
       text.setBounds(20, 650, 1000, 90);
       text.setForeground(Color.WHITE);
       text.setFont(new Font("serif", Font.PLAIN, 50));
       image.add(text);
       
       JButton next = new JButton("Next");
       next.setBounds(1200, 700, 150, 50);
       next.setBackground(Color.WHITE);
       next.setForeground(Color.MAGENTA);
       next.addActionListener(this);
       next.setFont(new Font("serif", Font.PLAIN, 24));
       
       image.add(next);
       
       
       setVisible(true);
       
       while(true) {
           text.setVisible(false);
           try {
               Thread.sleep(500);
           } catch (Exception e) {
               e.printStackTrace();
           }
           text.setVisible(true);
           try {
               Thread.sleep(500);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       
   }
   
   public void actionPerformed(ActionEvent ae) {
       setVisible(false);
       new Login();
   }
   

    
    public static void main(String[] args) {
        new HotelManagementSystem();
    }
    
}