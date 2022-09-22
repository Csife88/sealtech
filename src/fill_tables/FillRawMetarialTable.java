package fill_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.DatabaseCon;
import controller.RowCount;

public class FillRawMetarialTable {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void metarialAdd(JComboBox<String> tipusCombo, JTextField incomingWeight,JComboBox<String> supplierCombo, JTextField textFieldDeliveryNote,JTextField textFieldArriveDate) {
		
		RowCount rowCount = new RowCount(); 
	    int id =rowCount.getCount("rawmetarial");
		
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			PreparedStatement add = con.prepareStatement("insert into pls.rawmetarial values(?,?,?,?,?,?,?)");
			
			
		//	System.out.println(textFieldDeliveryNote.getText());
			System.out.println(incomingWeight.getText());
			
		 	add.setInt(1, id);
			add.setString(2,tipusCombo.getModel().getSelectedItem().toString());
			add.setInt(3, Integer.valueOf(incomingWeight.getText()));
			add.setString(4,supplierCombo.getModel().getSelectedItem().toString());
			add.setString(5, textFieldDeliveryNote.getText());
			add.setDate(6,java.sql.Date.valueOf(textFieldArriveDate.getText()));
			add.setInt(7, Integer.valueOf(incomingWeight.getText()));
			add.executeUpdate();  
			
			JOptionPane.showMessageDialog(null, "Hozzáadás sikeres");
			con.close();			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
	}

}
