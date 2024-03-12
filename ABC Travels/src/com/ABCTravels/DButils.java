package com.ABCTravels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DButils {
	
	public static void database(ABCTravelUser atu) throws SQLException {
		String link="connectionLink";
		String username="root";
		String password="Your Password";
		Connection con=DriverManager.getConnection(link,username,password);
		String query="insert into Travellers(FirstName,LastName,mobileNumber,gender,gmail,password) values(?,?,?,?,?,?)";
		
		PreparedStatement statement=con.prepareStatement(query);
		statement.setString(1, atu.getFirstName());
		statement.setString(2, atu.getLastName());
		statement.setString(3, atu.getMobileNumber());
		statement.setString(4, atu.getGender());
		statement.setString(5, atu.getGmail());
		statement.setString(6, atu.getPassword());
		statement.execute();
		
	}
	public static String validation(String username, String password) throws SQLException {
		String link="connectionLink";
		String username="root";
		String password="Your Password";
		int i=0;
		Connection con=DriverManager.getConnection(link,Username,Password);
		String query="select count(*) from travellers where gmail=? and password=? ";
		PreparedStatement statement=con.prepareStatement(query);
		statement.setString(1, username);
		statement.setString(2, password);
		ResultSet rs=statement.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt(1));
			if(rs.getInt(1)==1) {
				
				i=1;
			}
		}
		if(i==1) {
			return "present";
		}
		else {
			return "not present";
		}
		
	}

}
