package fill_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import controller.DatabaseCon;
import controller.RowCount;

public class FillCopyTable {
	
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	RowCount rowCount = new RowCount();
	
	public void CopyproductionAdd(JComboBox<String> comboBox_1 ,JComboBox<String> workerCombo, JComboBox<String> deliveryCombo, JTextField dátumTxt, JTextField dbText ) {
		
		
	    int id = rowCount.getCount("production");
		
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			PreparedStatement add = con.prepareStatement("insert into pls.copyproduction values(?,?,?,?,?,?,?,?)");
			
			
			
		 	add.setInt(1, id+1);
			add.setString(2,comboBox_1.getModel().getSelectedItem().toString());
			add.setDate(3,java.sql.Date.valueOf(dátumTxt.getText())); // java.sql.Date
			add.setString(4,workerCombo.getModel().getSelectedItem().toString());
			add.setInt(5, Integer.valueOf(dbText.getText()));
			add.setString(6, "Gyártott");
			add.setString(7, deliveryCombo.getModel().getSelectedItem().toString());
			add.setString(8, "false");
			
			add.executeUpdate();
			con.close();
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
	}

}
