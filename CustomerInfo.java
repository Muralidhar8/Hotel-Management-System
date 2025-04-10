package hotelmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class CustomerInfo extends JFrame {

    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CustomerInfo frame = new CustomerInfo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CustomerInfo() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 900, 600);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(30, 11, 100, 14);
        contentPane.add(lblId);

        JLabel lblNumber = new JLabel("Number");
        lblNumber.setBounds(130, 11, 100, 14);
        contentPane.add(lblNumber);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(230, 11, 100, 14);
        contentPane.add(lblName);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(330, 11, 100, 14);
        contentPane.add(lblGender);

        JLabel lblCountry = new JLabel("Country");
        lblCountry.setBounds(430, 11, 100, 14);
        contentPane.add(lblCountry);

        JLabel lblRoom = new JLabel("Room");
        lblRoom.setBounds(530, 11, 100, 14);
        contentPane.add(lblRoom);

        JLabel lblStatus = new JLabel("Check-in Status");
        lblStatus.setBounds(630, 11, 120, 14);
        contentPane.add(lblStatus);

        JLabel lblDeposit = new JLabel("Deposit");
        lblDeposit.setBounds(760, 11, 100, 14);
        contentPane.add(lblDeposit);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 40, 885, 450);
        contentPane.add(scrollPane);

        JButton btnLoadData = new JButton("Load Data");
        btnLoadData.setBounds(300, 510, 120, 30);
        btnLoadData.setBackground(Color.BLACK);
        btnLoadData.setForeground(Color.WHITE);
        contentPane.add(btnLoadData);

        btnLoadData.addActionListener(e -> {
            try {
                Conn c = new Conn();
                String query = "SELECT * FROM customer";
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to load data");
            }
        });

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(450, 510, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });

        getContentPane().setBackground(Color.WHITE);
    }
}