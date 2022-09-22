package read_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.DatabaseCon;
import controller.MyTableModel;
import net.proteanit.sql.DbUtils;


public class ReadRawMetarialTable  {

	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
MyTableModel myTableModel = new MyTableModel();
	
public void getDatabase(JTable table) {
		
		try  {

			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select ID,type,quantity,supplier,deliveryNumber,date,onStock from pls.rawmetarial order by date desc");
			//table.setModel(DbUtils.resultSetToTableModel(Rs));
			table.setModel(myTableModel.buildTableModel(Rs,table));
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
 }


}
