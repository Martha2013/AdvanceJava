package com.PrjConnect.Martha;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;

public class AlterDB extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterDB frame = new AlterDB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AlterDB() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 getDBInstance();
		   Connection con= getConnection();
		   final Statement stmt = con.createStatement();
		
		   
		   
		   
		   
		   
		   final JButton btnNewButton = new JButton("CREATE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query ="CREATE TABLE students(id INTEGER PRIMARY KEY,first_name CHAR(50),last_name CHAR(75))";
			      try {
					stmt.execute(query);
					JOptionPane.showMessageDialog(btnNewButton, "Student table created",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
			      catch(Exception e){
				
			}
			}
		});
		btnNewButton.setBounds(53, 44, 89, 23);
		contentPane.add(btnNewButton);
		
		final JButton btnNewButton_1 = new JButton("ADD ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query1 = "ALTER TABLE students ADD address CHAR(100) ";
			      String query2 = "ALTER TABLE students DROP COLUMN last_name";
				try{
					stmt.execute(query1);
					stmt.execute(query2);
				
				JOptionPane.showMessageDialog(btnNewButton_1, "Address column added to the table & last_name column removed from the table",
						"Information", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception e){
			}
			}
		});
		btnNewButton_1.setBounds(53, 110, 89, 23);
		contentPane.add(btnNewButton_1);
		
		final JButton btnNewButton_2 = new JButton("DROP");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query3 = "DROP table students";
				try{
					stmt.execute(query3);
				
				JOptionPane.showMessageDialog(btnNewButton_2, "students table removed",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
				catch(Exception e1){
					
				}
			}
		});
		btnNewButton_2.setBounds(53, 185, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("TABLE QUERYING");
		lblNewLabel.setBounds(155, 0, 118, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_3 = new JButton("CANCEL");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_3.setBounds(317, 185, 89, 23);
		contentPane.add(btnNewButton_3);
	}

	private Connection getConnection() {
		// TODO Auto-generated method stub
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
