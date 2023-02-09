package heat.treatment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import supplier.DatabaseCon;

public class SendHeatTreatmentQuantitys {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;

	public int SendHeatTreatmentQuantity() {  

		int sendQuantity = 0;
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select quantity  from pls.heattreatment where productNumber=" + "productNumber"); 

			while (Rs.next()) {

				sendQuantity = Rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sendQuantity;
	}

}
