package supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;

import view.Selection;

public class QantityCount {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;


	public int madeQuntityCount(JComboBox<String> comboBox) {

		String partNumber = comboBox.getModel().getSelectedItem().toString();
		int productionQuntity = 0;
		try {

			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select db from pls.copyproduction where status='" + "Gyártott" + "'"
					+ "AND PartNumber='" + partNumber + "'" + "AND used='" + "false" + "'");

			for (; Rs.next();) {

				productionQuntity = productionQuntity + (Integer) Rs.getObject(1);

				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productionQuntity;
	}
	public int sendHeatQuntityCount(JComboBox<String> combo) { 

		String box = combo.getModel().getSelectedItem().toString();
		int sentQuntity = 0;
		try {
			
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select quantity from pls.heattreatment where Status='" + "Sent" + "'"
					+ "AND productNumber='" + box + "'");

			for (; Rs.next();) {

				sentQuntity = sentQuntity + (Integer) Rs.getObject(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sentQuntity;
	}
	public int arrivedHeatQuntityCount(JComboBox<String> combo) { //  hőkezelőből visszaérkezett mennyiségek számlolása 

		String box = combo.getModel().getSelectedItem().toString();
		int sentQuntity = 0;
		try {

			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select quantity from pls.heattreatment where Status='" + "Arrived" + "'"
					+ "AND productNumber='" + box + "'");

			for (; Rs.next();) {

				sentQuntity = sentQuntity + (Integer) Rs.getObject(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sentQuntity;
	}	
	public int selectionQuntity(JComboBox<String> comboBox) { // késztermék számlálása  

		String box = comboBox.getModel().getSelectedItem().toString() ;
		int selectionQuntity = 0;
		try {

			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select GoodPart from pls.selectionparts where PartNumber='" + box + "'");

			for (; Rs.next();) {

				selectionQuntity = selectionQuntity + (Integer) Rs.getObject(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectionQuntity;
	}
	
	public int badPartQuntity() { // selejt számlálása

		String part = Selection.getPartNumber;
		
		int selectionQuntity = 0;
		try {

			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select BadPart from pls.selectionparts where PartNumber='" + part + "'");

			for (; Rs.next();) {

				selectionQuntity = selectionQuntity + (Integer) Rs.getObject(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectionQuntity;
	}
	
	
}
