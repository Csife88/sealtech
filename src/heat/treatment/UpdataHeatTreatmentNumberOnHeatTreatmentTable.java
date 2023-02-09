package heat.treatment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;

import supplier.DatabaseCon;

public class UpdataHeatTreatmentNumberOnHeatTreatmentTable {


	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
	public void  heatNumber(String heatId,JTextField HeatNumberText) { // Status value mod�sit�sa Arrivedre heatID

		try {

			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			String Query = "Update pls.heattreatment set HeatTreatmentNumber='" + HeatNumberText.getText() + "'" + "where ID=" + heatId;
			Statement Add = con.createStatement();
			Add.executeUpdate(Query);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
