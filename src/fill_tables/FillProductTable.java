package fill_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.DatabaseCon;
import controller.ProductAlreadyExist;
import controller.RowCount;

public class FillProductTable {
	
	RowCount rowCount = new RowCount(); 
	ProductAlreadyExist productAlreadyExist = new ProductAlreadyExist();
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void productAdd(JTextField txtNewPartNumber) {
		
	      int id = rowCount.getCount("product");
	      if(productAlreadyExist.isExist("product", txtNewPartNumber)) {
	    	 // JOptionPane.showMessageDialog(null, "A termék már létezik");
	      }else {
	    	  
	    		try {
	    			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
	    			PreparedStatement add = con.prepareStatement("insert into pls.product values(?,?)");
	    			
	    		 	add.setInt(1, id+1);
	    		 	add.setString(2, txtNewPartNumber.getText());
	    			
	    			add.executeUpdate();
	    			
	    			JOptionPane.showMessageDialog(null, "Hozzáadás sikeres");
	    			con.close();
	    			
	    		}catch (Exception ex){
	    			ex.printStackTrace();
	      }
	     }
		
	}	
}
