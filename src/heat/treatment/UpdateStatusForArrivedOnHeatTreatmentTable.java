package heat.treatment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import supplier.DatabaseCon;

public class UpdateStatusForArrivedOnHeatTreatmentTable {
	
	Connection con = null;
	Statement St = null;
	
	public void afterHeatTreatment(String heatId) {

		try {

			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			String Query = "Update pls.heattreatment set Status='" + "Arrived" + "'" + "where ID=" + heatId;
			Statement Add = con.createStatement();
			Add.executeUpdate(Query);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
