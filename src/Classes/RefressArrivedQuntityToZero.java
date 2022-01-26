package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RefressArrivedQuntityToZero {

	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void refressToZero(String name) {
		
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			String Query = "Update pls.heattreatment set quantity='" + 0 + "'" + "where productNumber='" + name + "'";
			Statement Add = con.createStatement();
			Add.executeUpdate(Query);

		} catch (Exception el) {

			el.printStackTrace();

		}
	}
}
