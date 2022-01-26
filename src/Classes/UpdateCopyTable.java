package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;

import Design.HeatTreatment;

public class UpdateCopyTable {

	ProductIDs pi = new ProductIDs();
 	ProductQuntitys pq = new ProductQuntitys();
	CalculateProductQuntityMinuszSendHeatTreatment calc = new CalculateProductQuntityMinuszSendHeatTreatment();

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
	public void UpdateColumnToArray(ArrayList list, JComboBox combo) { // teszt

		String productNumber = combo.getModel().getSelectedItem().toString();
		ArrayList calculatedValue = list;
            
		
		
		for (int i = 0; i < calculatedValue.size(); i++) {

			try {

				con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
				String Query = "Update pls.copyproduction set db='" + calculatedValue.get(i) + "'" + "where ID="
						+ pi.getListID(productNumber).get(i);
				Statement Add = con.createStatement();
				Add.executeUpdate(Query);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
