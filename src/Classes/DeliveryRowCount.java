package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeliveryRowCount {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
public int getCount() {
     

	
	int count=1;
	
	try  {
		con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
		St = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		Rs = St.executeQuery("Select ID from pls.deliveryNote");
	   
		while(Rs.next()) {
			
			count=Rs.getInt(1)+1;
		}
	    	
	}catch(Exception e ) {
		e.printStackTrace();
	}
	
	
  	return count;

 }

	

}
