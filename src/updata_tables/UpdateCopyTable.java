package updata_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;

import controller.CalculateProductQuntityMinuszSendHeatTreatment;
import controller.DatabaseCon;
import controller.ProductIDs;
import controller.ProductQuntitys;

public class UpdateCopyTable {

	ProductIDs pi = new ProductIDs();
 	ProductQuntitys pq = new ProductQuntitys();
	CalculateProductQuntityMinuszSendHeatTreatment calc = new CalculateProductQuntityMinuszSendHeatTreatment();

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
	public void UpdateColumnToArray(ArrayList<Integer> list, JComboBox<String> combo) { 

		String productNumber = combo.getModel().getSelectedItem().toString();
		ArrayList<Integer> calculatedValue = list;
            
		
		
		for (int i = 0; i < calculatedValue.size(); i++) {

			try {
				con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
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
