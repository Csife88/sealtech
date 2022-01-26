package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;

import Design.HeatTreatment;

public class UpdataMade {

	QantityCount qc = new QantityCount();
	

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;



	public void UpdataDataMade(JComboBox combo) { // stock tábla frissitése teszt

		
		String number = combo.getModel().getSelectedItem().toString();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			String Query = "Update pls.stock set Quantity='" + qc.madeQuntityCount(combo) + "'" + "where ProductNumber='"
					+ number+ "'" + "AND Status=" + "'Gyártott'";
			Statement Add = con.createStatement();
			Add.executeUpdate(Query);

		} catch (Exception el) {

			el.printStackTrace();
		}

	}
	
	
}
