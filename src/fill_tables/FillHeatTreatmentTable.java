package fill_tables;

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

import controller.BeforeHeatTreatmentQuntityForStockTable;
import controller.DatabaseCon;
import controller.QantityCount;
import controller.RowCount;

public class FillHeatTreatmentTable {
	

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	RowCount rowCount = new RowCount();
	QantityCount quantityCount = new QantityCount();
	BeforeHeatTreatmentQuntityForStockTable beforeHeatTreatmentQuntityForStockTable = new BeforeHeatTreatmentQuntityForStockTable();
	FillCopyHeatTreatmentTable fillCopyHeatTreatmentTable = new  FillCopyHeatTreatmentTable();
	public boolean isSend = true;

	public void sendHeatTreatment(JTextField quantityTxt,JComboBox<String> comboBox_1,JTextField sendDateTxt, JTable table) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int id = rowCount.getCount("heattreatment");

		if (Integer.valueOf(quantityTxt.getText()) > quantityCount.madeQuntityCount(comboBox_1)) {
			JOptionPane.showMessageDialog(null, "Küldés sikertelen");
			isSend = false;

		} else {
			try {
				con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
				PreparedStatement add = con.prepareStatement("insert into pls.heattreatment values(?,?,?,?,?,?,?)");

				String heatnumber = Integer.toString(year) + "/" + id;

				add.setInt(1, id );
				add.setString(2, comboBox_1.getModel().getSelectedItem().toString());
				add.setDate(3, java.sql.Date.valueOf(sendDateTxt.getText())); // java.sql.Date
				add.setInt(4, Integer.valueOf(quantityTxt.getText()));
				add.setDate(5, java.sql.Date.valueOf(sendDateTxt.getText()));
				add.setString(6, heatnumber);
				add.setString(7, "Sent");
				

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
