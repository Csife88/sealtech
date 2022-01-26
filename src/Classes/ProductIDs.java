package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductIDs {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;

	public ArrayList<Integer> getListID(String number) { // ID kivétele a copytable-bõl 820

		
		String productNumber = number;
		ArrayList<Integer> tomb = new ArrayList<>();

		try {
			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
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
