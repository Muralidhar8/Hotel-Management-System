package hotelmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UpdateCheck extends JFrame {
    private JPanel contentPane;
    private JTextField txtRoom, txtName, txtCheckedIn, txtDeposit, txtPending;
    private Choice c1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UpdateCheck frame = new UpdateCheck();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UpdateCheck() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 950, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Check-In Details");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitle.setBounds(124, 11, 222, 25);
        contentPane.add(lblTitle);

        JLabel lblID = new JLabel("Customer ID:");
        lblID.setBounds(25, 88, 100, 14);
        contentPane.add(lblID);
        
        c1 = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT number FROM customer");
            while (rs.next()) {
                c1.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(248, 85, 140, 20);
        contentPane.add(c1);

        JLabel lblRoom = new JLabel("Room Number:");
        lblRoom.setBounds(25, 129, 107, 14);
        contentPane.add(lblRoom);

        txtRoom = new JTextField();
        txtRoom.setBounds(248, 126, 140, 20);
        contentPane.add(txtRoom);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(25, 174, 97, 14);
        contentPane.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(248, 171, 140, 20);
        contentPane.add(txtName);

        JLabel lblCheckedIn = new JLabel("Checked-in:");
        lblCheckedIn.setBounds(25, 216, 107, 14);
        contentPane.add(lblCheckedIn);

        txtCheckedIn = new JTextField();
        txtCheckedIn.setBounds(248, 216, 140, 20);
        contentPane.add(txtCheckedIn);

        JLabel lblDeposit = new JLabel("Amount Paid (Rs):");
        lblDeposit.setBounds(25, 261, 150, 14);
        contentPane.add(lblDeposit);

        txtDeposit = new JTextField();
        txtDeposit.setBounds(248, 258, 140, 20);
        contentPane.add(txtDeposit);

        JLabel lblPending = new JLabel("Pending Amount (Rs):");
        lblPending.setBounds(25, 302, 150, 14);
        contentPane.add(lblPending);

        txtPending = new JTextField();
        txtPending.setBounds(248, 299, 140, 20);
        contentPane.add(txtPending);
        
        JButton btnCheck = new JButton("Check");
        btnCheck.addActionListener(e -> {
            try {
                Conn c = new Conn();
                String customerID = c1.getSelectedItem();

                PreparedStatement pst1 = c.c.prepareStatement("SELECT * FROM customer WHERE number = ?");
                pst1.setString(1, customerID);
                ResultSet rs1 = pst1.executeQuery();

                if (rs1.next()) {
                    txtRoom.setText(rs1.getString("room"));
                    txtName.setText(rs1.getString("name"));
                    txtCheckedIn.setText(rs1.getString("checked_in"));
                    txtDeposit.setText(rs1.getString("deposite"));
                }

                String roomNumber = txtRoom.getText();
                PreparedStatement pst2 = c.c.prepareStatement("SELECT price FROM room WHERE roomnumber = ?");
                pst2.setString(1, roomNumber);
                ResultSet rs2 = pst2.executeQuery();

                if (rs2.next()) {
                    int price = Integer.parseInt(rs2.getString("price"));
                    int paid = Integer.parseInt(txtDeposit.getText());
                    int pending = price - paid;
                    txtPending.setText(String.valueOf(pending));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btnCheck.setBounds(56, 378, 100, 23);
        btnCheck.setBackground(Color.BLACK);
        btnCheck.setForeground(Color.WHITE);
        contentPane.add(btnCheck);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> {
            try {
                Conn c = new Conn();
                String customerID = c1.getSelectedItem();
                String room = txtRoom.getText();
                String name = txtName.getText();
                String checkedIn = txtCheckedIn.getText();
                String deposit = txtDeposit.getText();

                String query = "UPDATE customer SET room = ?, name = ?, checked_in = ?, deposite = ? WHERE number = ?";
                PreparedStatement pst = c.c.prepareStatement(query);
                pst.setString(1, room);
                pst.setString(2, name);
                pst.setString(3, checkedIn);
                pst.setString(4, deposit);
                pst.setString(5, customerID);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                new Reception().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btnUpdate.setBounds(168, 378, 100, 23);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        contentPane.add(btnUpdate);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
        btnBack.setBounds(281, 378, 100, 23);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        getContentPane().setBackground(Color.WHITE);
    }
}