package updata_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;

import controller.DatabaseCon;
import controller.QantityCount;

public class UpdataMade {

	QantityCount qc = new QantityCount();
	

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;



	public void UpdataDataMade(JComboBox<String> combo) { 

		
		String number = combo.getModel().getSelectedItem().toString();
		
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			String Query = "Update pls.stock set Quantity='" + qc.madeQuntityCount(combo) + "'" + "where ProductNumber='"
					+ number+ "'" + "AND Status=" + "'Gyártott'";
			Statement Add = con.createStatement();
			Add.executeUpdate(Query);

		} catch (Exception el) {

			el.printStackTrace();
		}

	}
	
	
}
