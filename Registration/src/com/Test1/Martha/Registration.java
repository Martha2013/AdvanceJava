package com.Test1.Martha;

import java.awt.BorderLayout;

public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textPhone;
	private JTextField textCourse;
	private JPasswordField textPwd;
	private JComboBox comboBox;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
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
	public Registration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(300, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(27, 11, 46, 14);
		contentPane.add(lblName);
		
		textName = new JTextField();
		textName.setBounds(131, 8, 86, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel lblpwd = new JLabel("Password");
		lblpwd.setBounds(27, 49, 46, 14);
		contentPane.add(lblpwd);
		
		JLabel lblPhone = new JLabel("Phone #");
		lblPhone.setBounds(27, 94, 46, 14);
		contentPane.add(lblPhone);
		
		textPhone = new JTextField();
		textPhone.setBounds(131, 91, 86, 20);
		contentPane.add(textPhone);
		textPhone.setColumns(10);
		
		JLabel lblCourse = new JLabel("Course");
		lblCourse.setBounds(27, 137, 46, 14);
		contentPane.add(lblCourse);
		
		textCourse = new JTextField();
		textCourse.setBounds(131, 134, 86, 20);
		contentPane.add(textCourse);
		textCourse.setColumns(10);
		
		JLabel lblYear = new JLabel("Year of study");
		lblYear.setBounds(27, 181, 78, 20);
		contentPane.add(lblYear);
		
		
		final JComboBox YearCombo = new JComboBox();
		YearCombo.setModel(new DefaultComboBoxModel(new String[] {"1991", "1992", "1993", "1994", "1995", "1996"}));
		
		YearCombo.setBounds(131, 181, 86, 20);
		contentPane.add(YearCombo);
		
		JButton btnComplete = new JButton("Complete");
		btnComplete.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				 getDBInstance();
				   Connection con= getConnection();
				  
				String Name,Pwd,Course,Phone,Year;
				
					Name=textName.getText().toString();
					Pwd=textPwd.getText().toString();
				Course=textCourse.getText().toString();
				Phone=textPhone.getText().toString();
				Year=YearCombo.getSelectedItem().toString();
			
				
				
				    if(Name.length()<=0 || Pwd.length()<=0) {
				    
				    	JOptionPane.showMessageDialog(null,"Please Enter all your Name and Password");
				    }
				    
				    if(Name.length()>0 || Pwd.length()>0){
				      try{
				         Statement st = con.createStatement();

				         st.executeUpdate("INSERT INTO student_info(Name,Password,Course,Phone,Year) values('"+Name+"','"+Pwd+"','"+Course+"','"+Phone+"','"+Year+"')");
				        
				         con.close();
				            }
				         
				 	         
				      
				      catch (SQLException e) {
						// TODO: handle exception
				    	  
					}
				      JOptionPane.showMessageDialog(null,"Added to database");
				    }
				    else{
				    	JOptionPane.showMessageDialog(null,"Fill all the blanks");
				    }
				     
				    	  
				      
				    
				    
				   
			
				      Logging now =new Logging();
				      now.setVisible(true);
				    
				    
			}
				  
			
			
			
			
			private Connection getConnection() {
				// TODO Auto-generated method stub
				try {
			        Connection con = DriverManager.getConnection
			        ("jdbc:mysql://localhost:3306/myinfo","root",""); 
			        return con;
				}
			    catch(SQLException e){
			       System.out.println("SQL exception occured" + e);
			    }
				
				return null;
			}


			private void getDBInstance() {
				// TODO Auto-generated method stub
				 try {
			         Class.forName("com.mysql.jdbc.Driver");
			      }
			      catch(ClassNotFoundException e) {
			         System.out.println("Class not found "+ e);
			      }
				 System.out.println("JDBC Class found");
			}
				
		
			
		});
		btnComplete.setBounds(27, 228, 89, 23);
		contentPane.add(btnComplete);
		
		textPwd = new JPasswordField();
		textPwd.setBounds(131, 46, 86, 20);
		contentPane.add(textPwd);
		
		
		
		
		
		
	}
}
