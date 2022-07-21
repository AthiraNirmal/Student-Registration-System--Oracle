package regitration_system;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class show_logs extends JFrame {

	ResultSet result;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					show_logs frame = new show_logs();
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
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("log#");
		model.addColumn("User Name");
		model.addColumn("op_time");
		model.addColumn("Table Name");
		model.addColumn("operation");
		model.addColumn("Tuple Keyvalue");
		
		try {
		while(result.next())
		{
			model.addRow(new Object[]
			{
				result.getString("log#"),
				result.getString("User Name"),
				result.getString("op_time"),
				result.getString("Table Name"),
				result.getString("operation"),
				result.getString("Tuple Keyvalue"),
			});
		}
		}catch(Exception exp) {System.out.println(exp);}
	}
	
	   public show_logs() {
	        initComponents();
	    }
		
	    public show_logs(ResultSet res1) {
	        initComponents();
	        result= res1;
	    }

}
