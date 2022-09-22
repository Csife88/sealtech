package read_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JTextField;

import controller.DatabaseCon;
import controller.MyTableModel;
import net.proteanit.sql.DbUtils;

public class ReadDeliveryNoteTable {

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	MyTableModel myTableModel = new MyTableModel();
	
public void getDatabase(JTable table,JTextField weekTxt) {
		
		try  {
			
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select productNumber,Date,quantity,orderNumber from pls.deliveryNote where week='"+weekTxt.getText()+"'");
			//table.setModel(DbUtils.resultSetToTableModel(Rs));
			table.setModel(myTableModel.buildTableModel(Rs, table));
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
}	
}
