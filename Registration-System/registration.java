package regitration_system;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;


import oracle.jdbc.pool.OracleDataSource;
import oracle.net.nt.ConnectDescription;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


import java.awt.event.KeyEvent;

import oracle.jdbc.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.pool.OracleDataSource;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class regitration_system extends JFrame {

	private JPanel contentPane;
	private JTextField classid;
	private JTextField courseno;
	
    public String username;
    public String password;
    Connection conn;
    private JTextField deptcode;
    private JTextField Bnum;
    private JTextField classid1;
    private JTextField bnum1;
    private JTextField classid2;
    private JTextField bnum2;
    
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					regitration_system frame = new regitration_system();
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

	@SuppressWarnings("unchecked")
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 439);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		panel.setForeground(new Color(176, 224, 230));
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(178, 34, 34), null));
		panel.setBounds(6, 6, 736, 36);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("STUDENT REGISTRATION SYSTEM");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(178, 34, 34));
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Recent Logs");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
					ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
					Connection conn = ds.getConnection(username, password);
					if (!conn.isClosed()) {
		                    setVisible(false);
		                    CallableStatement call = conn.prepareCall("begin table.show_logs(?); end;");
		                    call.registerOutParameter(1, OracleTypes.CURSOR);
		                    call.execute();
		                    ResultSet rs = (ResultSet) call.getObject(1);
		                    new show_logs(rs).setVisible(true);
		                }
					conn.close();
					
				} catch(Exception exp) {System.out.println(exp);}
				
			}
		});
		btnNewButton.setForeground(new Color(178, 34, 34));
		btnNewButton.setBounds(315, 351, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton show_students = new JButton("Student List");
		show_students.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
					ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
					Connection conn = ds.getConnection(username, password);
					if (!conn.isClosed()) {
		                    setVisible(false);
		                    CallableStatement call = conn.prepareCall("begin student_operations.showTuples(?); end;");
		                    call.registerOutParameter(1, OracleTypes.CURSOR);
		                    call.execute();
		                    ResultSet rs = (ResultSet) call.getObject(1);
		                    new show_students(rs).setVisible(true);
		                }
					conn.close();
				} catch(Exception exp) {System.out.println(exp);}
			}
		});
		show_students.setForeground(new Color(178, 34, 34));
		show_students.setBackground(new Color(220, 20, 60));
		show_students.setBounds(315, 61, 117, 29);
		contentPane.add(show_students);
		
		JButton enroll = new JButton("Enroll Student");
		enroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String  bnum= Bnum.getText();
				String classid = classid1.getText();
				try {
					OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
					ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
					Connection conn = ds.getConnection(username, password);
					if (!conn.isClosed()) {
		                    setVisible(false);
		                    CallableStatement call = conn.prepareCall("begin table.enroll_student(?,?); end;");
		                    call.setString(1, bnum);
		                    call.setString(2, classid);
		                    call.executeUpdate();
					}
					conn.close();
				} catch(Exception exp) {
					if (exp.toString().contains("The B# is invalid")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"The B# is invalid"); 
						System.out.println(exp);}
					else if (exp.toString().contains("This is not a graduate student")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"This is not a graduate student"); 
						System.out.println(exp);
						}
					else if (exp.toString().contains("The classid is invalid")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"The classid is invalid"); 
						System.out.println(exp);
						}
					else if (exp.toString().contains("Cannot enroll into a class from a previous semester")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"Cannot enroll into a class from a previous semester"); 
						System.out.println(exp);
						}
					else if (exp.toString().contains("The class is already full")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"The class is already full"); 
						System.out.println(exp);
						}
					else if (exp.toString().contains("The student is already in the class")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"The student is already in the class"); 
						System.out.println(exp);
						}
					else if (exp.toString().contains("Students cannot be enrolled in more than five classes in the same semester")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"Students cannot be enrolled in more than five classes in the same semester"); 
						System.out.println(exp);
						}
					else if (exp.toString().contains("Prerequisite not satisfied")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"Prerequisite not satisfied"); 
						System.out.println(exp);
						}
					}
			}
		});
		enroll.setForeground(new Color(178, 34, 34));
		enroll.setBounds(142, 113, 134, 29);
		contentPane.add(enroll);
		
		JButton deleteS = new JButton("Delete Student");
		deleteS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String  bnum= bnum2.getText();
				try {
					OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
					ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
					Connection conn = ds.getConnection(username, password);
					if (!conn.isClosed()) {
		                    setVisible(false);
		                    CallableStatement call = conn.prepareCall("begin table.delete_student(?); end;");
		                    call.setString(1, bnum);
		                    call.executeUpdate();
					}
					conn.close();
				} catch(Exception exp) {
					if (exp.toString().contains("The B# is invalid")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"The B# is invalid"); 
						System.out.println(exp);
						}
					System.out.println(exp);}
			}
		});
		deleteS.setForeground(new Color(178, 34, 34));
		deleteS.setBounds(142, 284, 134, 29);
		contentPane.add(deleteS);
		
		JButton drop = new JButton("Drop Enrollment");
		drop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String  bnum= bnum1.getText();
				String classid = classid2.getText();
				try {
					OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
					ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
					Connection conn = ds.getConnection(username, password);
					if (!conn.isClosed()) {
		                    setVisible(false);
		                    CallableStatement call = conn.prepareCall("begin table.drop_stud_enrollment(?,?); end;");
		                    call.setString(1, bnum);
		                    call.setString(2, classid);
		                    call.executeUpdate();
					}
					conn.close();
				} catch(Exception exp) {
					if (exp.toString().contains("The B# is invalid")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"The B# is invalid"); 
						System.out.println(exp);}
					else if (exp.toString().contains("This is not a graduate student")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"This is not a graduate student"); 
						System.out.println(exp);
						}
					else if (exp.toString().contains("The classid is invalid")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"The classid is invalid"); 
						System.out.println(exp);
						}
					else if (exp.toString().contains("The student is not enrolled in the class")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"The student is not enrolled in the class"); 
						System.out.println(exp);
						}
					else if (exp.toString().contains("Only enrollment in the current semester can be dropped")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"Only enrollment in the current semester can be dropped"); 
						System.out.println(exp);
						}
					else if (exp.toString().contains("This is the only class for this student in Spring 2021 and cannot be dropped")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"This is the only class for this student in Spring 2021 and cannot be dropped"); 
						System.out.println(exp);
						}
					}
			}
		});
		drop.setForeground(new Color(178, 34, 34));
		drop.setBounds(142, 157, 134, 29);
		contentPane.add(drop);
		
		JButton class_list = new JButton("Class List");
		class_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cid = classid.getText();
				try {
					
					OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
					ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
					Connection conn = ds.getConnection(username, password);
					if (!conn.isClosed()) {
		                    setVisible(false);
		                    CallableStatement call = conn.prepareCall("begin student_operations.showClassDetails(?,?); end;");
		                    call.registerOutParameter(2, OracleTypes.CURSOR);
		                    call.setString(1, cid);
		                    call.execute();
		                    ResultSet rs = (ResultSet) call.getObject(1);
		                    while(rs.next())
		                    {
		                    	System.out.println("Hellop");
		                    }
		                    DefaultTableModel model = new DefaultTableModel();
		            		model.addColumn("B#");
		            		model.addColumn("First Name");
		            		model.addColumn("Last Name");
		            		try {
		            		while(rs.next())
		            		{
		            			model.addRow(new Object[]
		            			{
		            				rs.getString("B#"),
		            				rs.getString("first_name"),
		            				rs.getString("last_name")
		            			});
		            			System.out.println("Hellop");
		            		}
		            		}catch(Exception exp) {System.out.println(exp);}
		                    new show_classes(rs).setVisible(true);
					}
					conn.close();
				} catch(Exception exp) {
					if (exp.toString().contains("The classid is invalid")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"The classid is invalid"); 
						System.out.println(exp); }
					}
				
			}
		});
		class_list.setForeground(new Color(178, 34, 34));
		class_list.setBounds(142, 198, 134, 29);
		contentPane.add(class_list);
		
		classid = new JTextField();
		classid.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (classid.getText().equals("Enter classid")) {
					classid.setText(" ");
		        }
			}
		});
		classid.setText("Enter classid");
		classid.setBounds(330, 198, 261, 26);
		contentPane.add(classid);
		classid.setColumns(10);
		
		JButton prerequisite = new JButton("Prerequisite");
		prerequisite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String  dept_code= deptcode.getText();
				String course = courseno.getText();
				try {
					OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
					ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
					Connection conn = ds.getConnection(username, password);
					if (!conn.isClosed()) {
		                    setVisible(false);
		                    CallableStatement call = conn.prepareCall("begin table.show_prerequisites(?,?,?); end;");
		                    call.registerOutParameter(1, OracleTypes.CURSOR);
		                    call.setString(2, dept_code);
		                    call.setString(3, course);
		                    call.execute();
		                    ResultSet rs = (ResultSet) call.getObject(1);
		                    new show_prerequisite(rs).setVisible(true);
					}
					conn.close();
				} catch(Exception exp) {
					if (exp.toString().contains("dept_code || course# does not exist")) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"dept_code || course# does not exist"); 
						System.out.println(exp); }
					}
			}
		});
		prerequisite.setForeground(new Color(178, 34, 34));
		prerequisite.setBounds(142, 243, 134, 29);
		contentPane.add(prerequisite);
		
		courseno = new JTextField();
		courseno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (courseno.getText().equals("Enter course#")) {
					courseno.setText(" ");
		        }
			}
		});
		courseno.setText("Enter course#");
		courseno.setBounds(468, 236, 123, 26);
		contentPane.add(courseno);
		courseno.setColumns(10);
		
		deptcode = new JTextField();
		deptcode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			
			if (deptcode.getText().equals("Enter dept code")) {
				deptcode.setText(" ");
	        }
			}
		});
		deptcode.setText("Enter dept code");
		deptcode.setColumns(10);
		deptcode.setBounds(330, 236, 123, 26);
		contentPane.add(deptcode);
		
		Bnum = new JTextField();
		Bnum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Bnum.getText().equals("Enter B#")) {
					Bnum.setText(" ");
		        }
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if (Bnum.getText().equals("Enter B#")) {
					Bnum.setText(" ");
		        }
				
			}
		});
		Bnum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
//				if (Bnum.getText().equals("Enter B# here")) {
//					Bnum.setText(" ");
//		        }
			}
		});
		Bnum.setText("Enter B#");
		Bnum.setColumns(10);
		Bnum.setBounds(325, 113, 123, 26);
		contentPane.add(Bnum);
		
		classid1 = new JTextField();
		classid1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (classid1.getText().equals("Enter classid")) {
					classid1.setText(" ");
		        }
			}
		});
		classid1.setText("Enter classid");
		classid1.setColumns(10);
		classid1.setBounds(468, 113, 123, 26);
		contentPane.add(classid1);
		
		bnum1 = new JTextField();
		bnum1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (bnum1.getText().equals("Enter B#")) {
					bnum1.setText(" ");
		        }
			}
		});
		bnum1.setText("Enter B#");
		bnum1.setColumns(10);
		bnum1.setBounds(330, 157, 123, 26);
		contentPane.add(bnum1);
		
		classid2 = new JTextField();
		classid2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (classid2.getText().equals("Enter classid")) {
					classid2.setText(" ");
		        }
			}
		});
		classid2.setText("Enter classid");
		classid2.setColumns(10);
		classid2.setBounds(468, 157, 123, 26);
		contentPane.add(classid2);
		
		bnum2 = new JTextField();
		bnum2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (bnum2.getText().equals("Enter B#")) {
					bnum2.setText(" ");
		        }
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (bnum2.getText().equals("Enter B#")) {
					bnum2.setText(" ");
		        }
			}
		});
		bnum2.setText("Enter B#");
		bnum2.setColumns(10);
		bnum2.setBounds(330, 284, 261, 26);
		contentPane.add(bnum2);
	}
	   public regitration_system() {
	        initComponents();
	    }
		
	    public regitration_system(String user1, String pass1) {
	        initComponents();
	        username = user1;
	        password = pass1;
	    }
}

