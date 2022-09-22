package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BadPartQuantitys {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;


	public ArrayList<Integer> getListBadPartQuantity(String productNumber) {  

		
		ArrayList<Integer> tomb = new ArrayList<>();

		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select BadPart from pls.selectionparts where PartNumber='" + productNumber+"'");

			while (Rs.next()) {

				tomb.add(Rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return tomb;
	}
}