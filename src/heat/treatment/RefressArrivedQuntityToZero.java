package heat.treatment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import supplier.DatabaseCon;

public class RefressArrivedQuntityToZero {

	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void refressToZero(String name) {
		
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			String Query = "Update pls.heattreatment set quantity='" + 0 + "'" + "where productNumber='" + name + "'"+"AND Status='"+"Arrived"+"'";
			Statement Add = con.createStatement();
			Add.executeUpdate(Query);

		} catch (Exception el) {

			el.printStackTrace();

		}
	}
}
