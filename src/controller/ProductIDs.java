package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductIDs {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;

	public ArrayList<Integer> getListID(String number) { // ID kiv�tele a copytable-b�l 820

		
		String productNumber = number;
		ArrayList<Integer> tomb = new ArrayList<>();

		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select ID  from pls.copyproduction where PartNumber='" +productNumber+ "'");

			while (Rs.next()) {

				tomb.add(Rs.getInt(1));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return tomb;
	}
	
}
