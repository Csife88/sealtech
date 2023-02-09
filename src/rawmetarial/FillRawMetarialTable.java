package rawmetarial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import supplier.DatabaseCon;

public class FillRawMetarialTable {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void metarialAdd(JComboBox<String> tipusCombo, JTextField incomingWeight,JComboBox<String> supplierCombo, JTextField textFieldDeliveryNote,JTextField textFieldArriveDate) {
		
	    
		
		try {

			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			PreparedStatement add = con.prepareStatement("insert into pls.rawmetarial values(next value for pls.rawmetarialid_seq,?,?,?,?,?,?)");
			
			
			add.setString(1,tipusCombo.getModel().getSelectedItem().toString());
			add.setInt(2, Integer.valueOf(incomingWeight.getText()));
			add.setString(3,supplierCombo.getModel().getSelectedItem().toString());
			add.setString(4, textFieldDeliveryNote.getText());
			add.setDate(5,java.sql.Date.valueOf(textFieldArriveDate.getText()));
			add.setInt(6, Integer.valueOf(incomingWeight.getText()));
			add.executeUpdate();  
			
			JOptionPane.showMessageDialog(null, "Hozzáadás sikeres");
			con.close();			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
	}

}
