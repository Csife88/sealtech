package read_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;

import controller.DatabaseCon;
import net.proteanit.sql.DbUtils;

public class ReadPorductionDatabase {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
public void getDatabase(JTable table ,String tableName) {
	
	
		try  {
			
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select PartNumber,date,worker,db,deliveryNote from pls."+tableName+" where status='"+"Gyártott"+"'"
			+"ORDER BY date desc");
			table.setModel(DbUtils.resultSetToTableModel(Rs));
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
}	

}
