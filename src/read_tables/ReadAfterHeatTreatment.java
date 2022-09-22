package read_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;

import controller.DatabaseCon;
import controller.MyTableModel;
import net.proteanit.sql.DbUtils;

public class ReadAfterHeatTreatment {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	MyTableModel myTableModel = new MyTableModel();
	
	public void readAfterHeatTreatment(JTable table) { // beérkezés táblázat feltöltése adatokkal

		try {

			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery(
					"Select ID,productNumber,arrivDate,quantity,HeatTreatmentNumber from pls.heattreatment where Status='"
							+ "Arrived" + "'"+ "AND quantity >'"+0+"'"
							+" ORDER BY arrivDate asc");
			//table_2.setModel(DbUtils.resultSetToTableModel(Rs));
			table.setModel(myTableModel.buildTableModel(Rs, table));
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
