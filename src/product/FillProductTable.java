package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import supplier.DatabaseCon;
import supplier.ProductAlreadyExist;

public class FillProductTable {
	
	ProductAlreadyExist productAlreadyExist = new ProductAlreadyExist();
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void productAdd(JTextField txtNewPartNumber) {
		
	      if(productAlreadyExist.isExist("product", txtNewPartNumber)) {
	      }else {
	    		try {
	    			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
	    			PreparedStatement add = con.prepareStatement("insert into pls.product values(next value for pls.product_seq,?)");
	    			
	    		 	add.setString(1, txtNewPartNumber.getText());
	    			
	    			add.executeUpdate();
	    			
	    			JOptionPane.showMessageDialog(null, "Hozzáadás sikeres");
	    			con.close();
	    			
	    		}catch (Exception ex){
	    			ex.printStackTrace();
	      }
	     }
		
	}	
}
