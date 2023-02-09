package delivery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import supplier.DatabaseCon;
import supplier.MyTableModel;

public class ReadDeliveryNoteTable {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	MyTableModel myTableModel = new MyTableModel();
		
	
public void getDatabase(JTable table,JTextField weekTxt,JComboBox<String> yearCombo) {
		try  {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select productNumber,Date,quantity,orderNumber from pls.deliveryNote where week='"+weekTxt.getText()+"'"
			+"and year='"+yearCombo.getModel().getSelectedItem()+"'");
			//table.setModel(DbUtils.resultSetToTableModel(Rs));
			table.setModel(myTableModel.buildTableModel(Rs, table));
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
}	
}
