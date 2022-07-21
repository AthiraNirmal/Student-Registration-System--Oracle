package regitration_system;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;

public class show_classes extends JFrame {

	private JPanel contentPane;
	ResultSet result;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					show_classes frame = new show_classes();
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
	public void initComponents () {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("B#");
		model.addColumn("First Name");
		model.addColumn("Last Name");
		try {
		while(result.next())
		{
			model.addRow(new Object[]
			{
				result.getString("B#"),
				result.getString("first_name"),
				result.getString("last_name")
			});
			System.out.println("Hellop");
		}
		}catch(Exception exp) {System.out.println(exp);}
	}
	
	   public show_classes() {
	        initComponents();
	    }
		
	    public show_classes(ResultSet res1) {
	        initComponents();
	        result= res1;

	        // @@ - getting username & password from Login class

	    }
}
