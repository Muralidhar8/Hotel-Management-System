package hotelmanagementsystem;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class UpdateRoom extends JFrame {
    private JPanel contentPane;
    private JTextField txt_Ava;
    private JTextField txt_Status;
    private JTextField txt_Room;
    Choice c1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UpdateRoom frame = new UpdateRoom();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UpdateRoom() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 1000, 450);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);

        JLabel lblUpdateRoomStatus = new JLabel("Update Room Status");
        lblUpdateRoomStatus.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUpdateRoomStatus.setBounds(85, 11, 206, 34);
        contentPane.add(lblUpdateRoomStatus);

        JLabel lblNewLabel = new JLabel("Guest ID:");
        lblNewLabel.setBounds(27, 87, 90, 14);
        contentPane.add(lblNewLabel);

        c1 = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                c1.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(160, 84, 140, 20);
        contentPane.add(c1);

        JLabel lblRoomId = new JLabel("Room Number:");
        lblRoomId.setBounds(27, 133, 100, 14);
        contentPane.add(lblRoomId);

        txt_Room = new JTextField();
        txt_Room.setBounds(160, 130, 140, 20);
        contentPane.add(txt_Room);
        txt_Room.setColumns(10);

        JLabel lblAvailability = new JLabel("Availability:");
        lblAvailability.setBounds(27, 187, 90, 14);
        contentPane.add(lblAvailability);

        txt_Ava = new JTextField();
        txt_Ava.setBounds(160, 184, 140, 20);
        contentPane.add(txt_Ava);
        txt_Ava.setColumns(10);

        JLabel lblCleanStatus = new JLabel("Cleaning Status:");
        lblCleanStatus.setBounds(27, 240, 90, 14);
        contentPane.add(lblCleanStatus);

        txt_Status = new JTextField();
        txt_Status.setBounds(160, 237, 140, 20);
        contentPane.add(txt_Status);
        txt_Status.setColumns(10);

        JButton b1 = new JButton("Check");
        b1.addActionListener(e -> {
            try {
                Conn c = new Conn();
                String s1 = c1.getSelectedItem();
                ResultSet rs1 = c.s.executeQuery("SELECT * FROM customer WHERE number = '" + s1 + "'");
                if (rs1.next()) {
                    txt_Room.setText(rs1.getString("room"));
                }

                ResultSet rs2 = c.s.executeQuery("SELECT * FROM room WHERE roomnumber = '" + txt_Room.getText() + "'");
                if (rs2.next()) {
                    txt_Ava.setText(rs2.getString("availability"));
                    txt_Status.setText(rs2.getString("cleaning_status"));
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        });
        b1.setBounds(120, 315, 89, 23);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        contentPane.add(b1);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> {
            try {
                Conn c = new Conn();
                String query = "UPDATE room SET cleaning_status = '" + txt_Status.getText() + "' WHERE roomnumber = '" + txt_Room.getText() + "'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Update Successful");
                new Reception().setVisible(true);
                setVisible(false);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        });
        btnUpdate.setBounds(60, 355, 89, 23);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        contentPane.add(btnUpdate);

        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
        btnExit.setBounds(180, 355, 89, 23);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i3 = i1.getImage().getScaledInstance(550, 250, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(400, 80, 600, 250);
        add(l1);
    }
}