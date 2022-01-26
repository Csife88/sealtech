package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SendDeliveryQuantity {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;

	public int SendDeliveryQuantity(String number) { 

		String productNumber = number;
		int sendQuantity = 0;

		try {
			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			St = con.createStatement();
			Rs = St.executeQuery("Select quantity  from pls.deliveryNote where productNumber=" + "productNumber");

			while (Rs.next()) {

				sendQuantity = Rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sendQuantity;
	}

}
