package selection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;
import supplier.DatabaseCon;
import supplier.MyTableModel;


public class ReadSelectionTable {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	MyTableModel myTableModel = new  MyTableModel();
	
	public void getSelectionTable(JTable table) {
		
		try {

			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select PartNumber,Date, GoodPart,BadPart,HeatTreatmentNumber from pls.selectionparts where GoodPart>"+0
					+" ORDER BY Date asc");
		//	table_2.setModel(DbUtils.resultSetToTableModel(Rs));
			table.setModel(myTableModel.buildTableModel(Rs, table));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
