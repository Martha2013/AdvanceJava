package com.PrjConnect.Martha;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JButton;

import com.mysql.jdbc.Statement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextArea;

public class ReadThis extends JFrame  {

	private JPanel contentPane;
	private JTextArea textArea;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReadThis frame = new ReadThis();
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
	public ReadThis() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(172, 54, 46, 14);
		contentPane.add(label);
		
		final JButton btnRead = new JButton("READ");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getDBInstance();
				   Connection con= getConnection();
				   //btnRead.getText().toString();
			     
			      try {
			        
			         Statement st = (Statement) con.createStatement();
			         ResultSet rs = st.executeQuery
			         ("SELECT * FROM tbl_employee");
			         
			         while (rs.next()) {
			        	 
			            int id = rs.getInt("id");
			            String emp_name = rs.getString("emp_name");
			            String emp_id = rs.getString("emp_id");
			            String emp_phone = rs.getString("emp_phone");
			            
			           
			       textArea.append(emp_name + "  "  + emp_id +  "  "  +  emp_phone);
			       
			         }
			         
			      }
			      catch(SQLException e){
			         System.out.println("SQL exception occured" + e);
			      }
			   }
			   

			private Connection getConnection() {
				try {
			        Connection con = DriverManager.getConnection
			        ("jdbc:mysql://localhost:3306/myDB","root",""); 
			        return con;
				}
			    catch(SQLException e){
			       System.out.println("SQL exception occured" + e);
			    }
				return null;
			}

			private void getDBInstance() {
				 try {
			         Class.forName("com.mysql.jdbc.Driver");
			      }
			      catch(ClassNotFoundException e) {
			         System.out.println("Class not found "+ e);
			      }
				 System.out.println("JDBC Class found");
			}
		});
		btnRead.setBounds(57, 184, 89, 23);
		contentPane.add(btnRead);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			}
		});
		btnCancel.setBounds(275, 184, 89, 23);
		contentPane.add(btnCancel);
		
		textArea = new JTextArea();
		textArea.setBounds(20, 11, 297, 149);
		contentPane.add(textArea);
			}
}
