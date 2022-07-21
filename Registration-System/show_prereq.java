package regitration_system;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class show_prerequisite extends JFrame {

	private JPanel contentPane;
	ResultSet result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					show_prerequisite frame = new show_prerequisite();
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
//		model.addColumn("B#");
//		model.addColumn("First Name");
//		model.addColumn("Last Name");
//		model.addColumn("Student Level");
//		model.addColumn("GPA");
//		model.addColumn("email");
//		model.addColumn("Birth date");
		try {
		while(result.next())
		{
			model.addRow(new Object[]
			{
//				result.getString("B#"),
//				result.getString("first_name"),
//				result.getString("last_name"),
//				result.getString("st_level"),
//				result.getString("gpa"),
//				result.getString("email"),
//				result.getString("bdate")
			});
		}
		}catch(Exception exp) {System.out.println(exp);}
		
	}
	
	
	   public show_prerequisite() {
	        initComponents();
	    }
		
	    public show_prerequisite(ResultSet res1) {
	        initComponents();
	        result= res1;

	        // @@ - getting username & password from Login class

	    }

}
