package com.Test1.Martha;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class Logging extends JFrame  {

	private JPanel contentPane;
	private JTextField txtName;
	private JPasswordField txtPwd;
	JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logging frame = new Logging();
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
	public Logging() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(61, 42, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(61, 100, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setBounds(169, 39, 86, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		getDBInstance();
		final Connection con=getConnection();
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Nam,Pwd,user,paswd;
				Nam=txtName.getText().toString();
				Pwd=txtPwd.getText().toString();
				user="";
				paswd="";
				if(Nam.length()<=0 || Pwd.length()<=0) {
			    	
			    	JOptionPane.showMessageDialog(null,"Please Enter all your Name and Password");
			    }
			    
			    
			    if(Nam.length()>0 || Pwd.length()>0){
			      try{
			         Statement st = con.createStatement();
			         
			         ResultSet rs =st.executeQuery("SELECT * FROM myinfo.student_info WHERE (Name='"+Nam+"' AND Password='"+Pwd+"')");
			         while(rs.next()){
			                
			                user=rs.getString("Name");
			                paswd=rs.getString("Password");
			                
			                
				           
			            }
			         
				       
			         
			         if (user.equals(Nam) && paswd.equals(Pwd)){
			        	 JOptionPane.showMessageDialog(null,"Welcome "+Nam +  "\n" + "  " + "Check your details","_"+Nam,JOptionPane.INFORMATION_MESSAGE);
			        	 try{
			        		 ResultSet rs1=st.executeQuery("select * from student_info where Name = '" +Nam+"' and Password='"+Pwd+"'");
			        	 
			        	
			        	 while (rs1.next()) {
					             Nam= rs1.getString("Name");
					             String Phone = rs1.getString("Phone");
					            String Pasword=rs1.getString("Password");
						        String Courses=rs1.getString("Course");
						        String Year=rs1.getString("Year");
					            
					            
					            textArea.append("Name;" + Nam + " " +"Phone;" + Phone + " " +"Pwd;" + Pasword + " " + "Course;" + Courses + " "  + "Year;" + Year);
					            
			        	 }
			        	
			        	 }
			        	 
			        	 catch(SQLException e){
			        		 
			        	 }
					         }
			      
			         
			        
			         
			         
			         
			         
			         else if(user!=(Nam) &&(paswd!=(Pwd)) ){
			        	 JOptionPane.showMessageDialog(null,"Wrong Password OR Name \n Register First");
			        	 
			        	 Registration next=new Registration();
							next.setVisible(true);
			         }
			      }
			      catch (SQLException e) {
					// TODO: handle exception
			      }	  
				}
			    
				
			}
		});
		btnLogin.setBounds(61, 159, 89, 23);
		contentPane.add(btnLogin);
		
		txtPwd = new JPasswordField();
		txtPwd.setBounds(169, 97, 86, 20);
		contentPane.add(txtPwd);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setColumns(8);
		textArea.setBackground(SystemColor.menu);
		textArea.setForeground(Color.PINK);
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		textArea.setEditable(false);
		textArea.setRows(8);
		textArea.setBounds(292, 11, 132, 171);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(217, 159, 65, 23);
		contentPane.add(btnNewButton);
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
	}

