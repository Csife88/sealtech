package stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import supplier.DatabaseCon;
import supplier.ProductAlreadyExist;

public class FillStockTableForNewProduct {
	
	ProductAlreadyExist productAlreadyExist = new ProductAlreadyExist();
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void productAdd(JTextField txtNewPartNumber,JTextField textSatusz) {
		
	      if(productAlreadyExist.isExistProductAndStatus("stock", txtNewPartNumber, textSatusz)) {
	    	 JOptionPane.showMessageDialog(null, "Ez a termék és státusz már hozzá lett adva korábban");
	      }else {
	  		try {
				con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
				PreparedStatement add = con.prepareStatement("insert into pls.stock values(next value for pls.stock_seq,?,?,?)");
				
			 	add.setString(1, txtNewPartNumber.getText());
			 	add.setInt(2, 0);
				add.setString(3,textSatusz.getText());
				
				add.executeUpdate();
				JOptionPane.showMessageDialog(null, "Hozzáadás sikeres");
				con.close();
				
			}catch (Exception ex){
				ex.printStackTrace();
			}
	      }
	      
	}	

}
