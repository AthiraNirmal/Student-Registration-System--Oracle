package regitration_system;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.KeyEvent;
import java.sql.*;
import oracle.jdbc.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.pool.OracleDataSource;



public class show_students extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					show_students frame = new show_students();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	ResultSet result;

	/**
	 * Create the frame.
	 */
	public void initComponents2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("B#");
		model.addColumn("First Name");
		model.addColumn("Last Name");
		model.addColumn("Student Level");
		model.addColumn("GPA");
		model.addColumn("email");
		model.addColumn("Birth date");
		try {
		while(result.next())
		{
			model.addRow(new Object[]
			{
				result.getString("B#"),
				result.getString("first_name"),
				result.getString("last_name"),
				result.getString("st_level"),
				result.getString("gpa"),
				result.getString("email"),
				result.getString("bdate")
			});
		}
		}catch(Exception exp) {System.out.println(exp);}
		
		
	}
	
	   public show_students() {
	        initComponents2();
	    }
		
	    public show_students(ResultSet res1) {
	        initComponents2();
	        result= res1;

	        // @@ - getting username & password from Login class

	    }

}
