package updata_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;

import controller.DatabaseCon;
import controller.QantityCount;

public class UpdataSentHeatTreatment {
	
QantityCount qantityCount = new QantityCount();
	

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;



	public void UpdataSentQuantity(JComboBox<String> combo) { // stock t�bla frissit�se teszt

		
		String number = combo.getModel().getSelectedItem().toString();
		
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			String Query = "Update pls.stock set Quantity='" + qantityCount.sendHeatQuntityCount(combo) + "'" + "where ProductNumber='"
					+ number+ "'" + "AND Status=" + "'Hőkezelőben'";
			Statement Add = con.createStatement();
			Add.executeUpdate(Query);

		} catch (Exception el) {

			el.printStackTrace();
		}

	}
}
