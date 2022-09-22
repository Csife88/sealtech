package read_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.DatabaseCon;
import controller.ProductionFilterEmptys;
import net.proteanit.sql.DbUtils;

public class FilterProductionTable extends ProductionFilterEmptys {
	

	Connection con = null;
	Statement St = null;
	ResultSet Rs = null;
	
	
	public void getDatabase(JTable table ,String tableName,JComboBox<String> comboBox_1,JComboBox<String> workerComboBox,
			JComboBox<String> deliveryCombo,JTextField textFieldForm, JTextField textFieldAt) {
		
		
		try  {
			con = DriverManager.getConnection(DatabaseCon.getUrl(),DatabaseCon.getName(),DatabaseCon.getPassword());
			St = con.createStatement();
			Rs = St.executeQuery("Select PartNumber,date,worker,db,deliveryNote from pls."+
			tableName+partEmptyBoxes(comboBox_1)
			+workerEmptyBoxes(workerComboBox)
			+rawMetarialEmptyBoxes(deliveryCombo)
			+fromToAtisEmptyBoxes(textFieldForm, textFieldAt)
			+"ORDER BY date desc");
			table.setModel(DbUtils.resultSetToTableModel(Rs));
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
}	


}
