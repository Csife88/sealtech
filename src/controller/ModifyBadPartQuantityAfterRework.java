package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;

public class ModifyBadPartQuantityAfterRework {
	
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
	
	public void modifyBadPartQuantity(String PartNumber, JTextField reWorkTxt) {
		
		BadPartQuantitys bpq = new BadPartQuantitys();
		SelectionTableProductIDs selectID = new SelectionTableProductIDs(); 
		
		ArrayList<Integer> ID = selectID.getListID(PartNumber);
		int remaining = 0;
		int extract = Integer.parseInt(reWorkTxt.getText());
		for(int i = 0; i<bpq.getListBadPartQuantity(PartNumber).size()-1;i++) { // -1 az �ppen hozz�adott sort ne frissitse 
			
			
			if(bpq.getListBadPartQuantity(PartNumber).get(i)<= extract) {
			     remaining=0;
			     extract=extract-bpq.getListBadPartQuantity(PartNumber).get(i);
			}else {
				remaining=bpq.getListBadPartQuantity(PartNumber).get(i)- extract;
				extract = 0;
				
			}
		
			
		try {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			String Query = "Update pls.selectionparts set BadPart='" + remaining + "'" + "where ID='" + ID.get(i) + "'";
			Statement Add = con.createStatement();
			Add.executeUpdate(Query);

		} catch (Exception el) {

			el.printStackTrace();

	    	}
		}
	}

}
