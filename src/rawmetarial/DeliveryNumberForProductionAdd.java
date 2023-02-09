package rawmetarial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;

import supplier.DatabaseCon;

public class DeliveryNumberForProductionAdd {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;

	public void getDeliverNumber(JComboBox<String> deliveryCombo){
		
		try  {
				
			    con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
				St = con.createStatement();
				Rs = St.executeQuery("Select deliveryNumber from pls.rawmetarial where onStock > 0"+0);			
				deliveryCombo.removeAllItems();
				while(Rs.next()) {
					
					deliveryCombo.addItem(Rs.getString("deliveryNumber"));	
				}
				
			}catch(Exception e ) {
				e.printStackTrace();
			}
			
		}

	
}
