package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class ReadSendHeatTreatment {
	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	public void getSendHeatTreatment(JTable table_1) {

		try {

			con = DriverManager.getConnection("jdbc:mysql://database-1.c3byo1nvyfgl.eu-central-1.rds.amazonaws.com/pls", "admin", "Szemes1!");
			St = con.createStatement();
			Rs = St.executeQuery(
					"Select ID,productNumber,sentDate,quantity,HeatTreatmentNumber from pls.heattreatment where Status='"
							+ "Sent" + "'");
			table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table_1.setModel(DbUtils.resultSetToTableModel(Rs));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
