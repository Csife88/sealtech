package stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;
import supplier.DatabaseCon;

public class BeforeHeatTreatmentQuntityForStockTable {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
	public void beferoHeatTreatment(JTable table) {

		try {

			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select ProductNumber,Quantity from pls.stock where status='" + "Gyártott" + "'");
			table.setModel(DbUtils.resultSetToTableModel(Rs));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

}
