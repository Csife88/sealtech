package selection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;

import supplier.DatabaseCon;

public class UpdataSelectionTable {

	
	SelectionTableProductIDs selectionTableProductIDs = new SelectionTableProductIDs();
	SelectionProductQuntitys selectionProductQuntitys = new SelectionProductQuntitys();

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
	public void UpdateColumnToArray(ArrayList<Integer> list, JComboBox<String> combo) { // teszt

		String productNumber = combo.getModel().getSelectedItem().toString();
		ArrayList<Integer> calculatedValue = list;
            
		
		
		for (int i = 0; i < calculatedValue.size(); i++) {

			try {
				con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
				String Query = "Update pls.selectionparts set GoodPart='" + calculatedValue.get(i) + "'" + "where ID="
						+ selectionTableProductIDs.getListID(productNumber).get(i);
				Statement Add = con.createStatement();
				Add.executeUpdate(Query);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
