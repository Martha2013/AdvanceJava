package com.PrjConnect.Martha;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import com.mysql.jdbc.Statement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;

public class SearchDB extends JFrame {

	private JPanel contentPane;
	private JTextField txtInput;
	
	private JTextArea textArea1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchDB frame = new SearchDB();
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
	public SearchDB() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getDBInstance();
		   final Connection con =getConnection();
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Word = txtInput.getText().toString();
				Word = Word + "%";
				try{
				 Statement stmt = (Statement) con.createStatement();
			      String query[] ={ 
			      
			      "select emp_name,emp_id from tbl_employee where emp_name like'" +Word+"'"};
			      for(String q : query){
			         ResultSet rs = stmt.executeQuery(q);
			         
			         while (rs.next()) {
			            String emp_name = rs.getString("emp_name");
			            String emp_id = rs.getString("emp_id");
		
			            textArea1.append(emp_name+"  " + emp_id + " ");
			         }
			      } 
				}
			
				catch(Exception e){
					System.out.println("SQL exception occured" + e);
				}
			}
		});
		btnNewButton.setBounds(93, 114, 89, 23);
		contentPane.add(btnNewButton);
		
		txtInput = new JTextField();
		txtInput.setBounds(69, 48, 133, 29);
		contentPane.add(txtInput);
		txtInput.setColumns(10);
		
		textArea1 = new JTextArea();
		textArea1.setBounds(29, 165, 264, 86);
		contentPane.add(textArea1);
		
		JButton btnNewButton_1 = new JButton("CLOSE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(322, 213, 89, 23);
		contentPane.add(btnNewButton_1);
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
}
