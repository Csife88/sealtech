package heat.treatment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import stock.BeforeHeatTreatmentQuntityForStockTable;
import supplier.DatabaseCon;
import supplier.QantityCount;

public class FillHeatTreatmentTable {
	

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	QantityCount quantityCount = new QantityCount();
	BeforeHeatTreatmentQuntityForStockTable beforeHeatTreatmentQuntityForStockTable = new BeforeHeatTreatmentQuntityForStockTable();
	FillCopyHeatTreatmentTable fillCopyHeatTreatmentTable = new  FillCopyHeatTreatmentTable();
	public boolean isSend = true;

	public void sendHeatTreatment(JTextField quantityTxt,JComboBox<String> comboBox_1,JTextField sendDateTxt, JTable table) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

		
		if (Integer.valueOf(quantityTxt.getText()) > quantityCount.madeQuntityCount(comboBox_1)) {
			JOptionPane.showMessageDialog(null, "Küldés sikertelen");
			isSend = false;

		} else {
			try {
				con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
				PreparedStatement add = con.prepareStatement("insert into pls.heattreatment values(next value for pls.heatreatment_seq,?,?,?,?,?,?)");

				String heatnumber = Integer.toString(year) + "/"+Integer.toString(month)+"/"+Integer.toString(day);

				add.setString(1, comboBox_1.getModel().getSelectedItem().toString());
				add.setDate(2, java.sql.Date.valueOf(sendDateTxt.getText()));
				add.setInt(3, Integer.valueOf(quantityTxt.getText()));
				add.setDate(4, java.sql.Date.valueOf(sendDateTxt.getText()));
				add.setString(5, heatnumber);
				add.setString(6, "Sent");
				

				add.executeUpdate();
				JOptionPane.showMessageDialog(null, "Küldés sikeres");
				con.close();

		     	beforeHeatTreatmentQuntityForStockTable.beferoHeatTreatment(table);
				isSend = true;
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			fillCopyHeatTreatmentTable.FillCopyTable(comboBox_1, sendDateTxt, quantityTxt);
		}

	}
	
}
