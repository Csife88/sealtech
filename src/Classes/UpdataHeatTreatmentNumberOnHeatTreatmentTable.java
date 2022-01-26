package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;

public class UpdataHeatTreatmentNumberOnHeatTreatmentTable {


	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
	public void  heatNumber(String heatId,JTextField HeatNumberText) { // Status value modósitása Arrivedre heatID

		try {

			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			String Query = "Update pls.heattreatment set HeatTreatmentNumber='" + HeatNumberText.getText() + "'" + "where ID=" + heatId;
			Statement Add = con.createStatement();
			Add.executeUpdate(Query);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
