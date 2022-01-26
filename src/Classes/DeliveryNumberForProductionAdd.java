package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;

public class DeliveryNumberForProductionAdd {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;

	public void getDeliverNumber(JComboBox deliveryCombo){
		
		try  {
				
				con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
				St = con.createStatement();
				Rs = St.executeQuery("Select deliveryNumber from pls.rawmetarial");			
				//table.setModel(DbUtils.resultSetToTableModel(Rs));
				
				deliveryCombo.removeAllItems();
				while(Rs.next()) {
					
					deliveryCombo.addItem(Rs.getString("deliveryNumber"));	
				}
				
			}catch(Exception e ) {
				e.printStackTrace();
			}
			
		}

	
}
