package updata_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;

import controller.DatabaseCon;

public class UpdataDateArriveHeatTreatment {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void UpdataData(String heatId,JTextField arrivText) { // 

		try {

			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			String Query = "Update pls.heattreatment set arrivDate='" + arrivText.getText() + "'" + "where ID=" + heatId;
			Statement Add = con.createStatement();
			Add.executeUpdate(Query);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
