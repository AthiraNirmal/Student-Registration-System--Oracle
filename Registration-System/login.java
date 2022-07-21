package regitration_system;

import java.sql.*;
import java.sql.*;
import oracle.jdbc.*;
import java.math.*;
import java.io.*;
import java.awt.*;
import oracle.jdbc.pool.OracleDataSource;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*; 
public class login extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JTextField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(100, 149, 237)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
		panel.setBackground(new Color(169, 169, 169));
		panel.setForeground(new Color(119, 136, 153));
		panel.setBounds(6, 6, 438, 31);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("STUDENT REGISTRATION SYSTEM");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel.setForeground(new Color(0, 0, 255));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setBounds(114, 107, 87, 16);
		contentPane.add(lblNewLabel_1);
		
		user = new JTextField();
		user.setBounds(194, 102, 130, 26);
		contentPane.add(user);
		user.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setForeground(new Color(0, 0, 255));
		lblNewLabel_2.setBounds(114, 149, 68, 16);
		contentPane.add(lblNewLabel_2);
		
		pass = new JTextField();
		pass.setBounds(194, 144, 130, 26);
		contentPane.add(pass);
		pass.setColumns(10);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
					ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
					String username= user.getText();
					String password= pass.getText();
					Connection conn = ds.getConnection(username, password);
					if (!conn.isClosed()) {
		                    setVisible(false);
		                    JFrame f = new JFrame();
		                    JOptionPane.showMessageDialog(f,"Login Successful");  
		                    new regitration_system(conn).setVisible(true);
		                }
					
				} catch(Exception exp) {
					if (exp.toString().contains("invalid username/password")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"Invalid username/password"); 
						System.out.println(exp);
						}
				}
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.setBounds(157, 209, 117, 29);
		contentPane.add(btnNewButton);
	}

}
