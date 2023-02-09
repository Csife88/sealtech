package heat.treatment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import supplier.DatabaseCon;
import view.Selection;

   public class  RefressArrivedHeatTreatmentQuantity extends Selection{
	private static final long serialVersionUID = 1L;
	static Connection con = null;
	Statement St = null;
	ResultSet Rs = null;

	public static  void refressArrivedHeatTreatmentQuantity() {

		HeatTreatmentIDs heatTreatmentIDs = new HeatTreatmentIDs();
		ArrivedHeatQuantitys arrive = new ArrivedHeatQuantitys();

		goodPluszBad = Integer.parseInt(goodPartText.getText()) + Integer.parseInt(badPartText.getText());

		int remaining = 0;

		for (int i = 0; i < heatTreatmentIDs.getListID(comboBox).size(); i++) {

			int id = heatTreatmentIDs.getListID(comboBox).get(i);

			if (goodPluszBad >= arrive.getListQuntity(comboBox).get(i)) {

				remaining = 0;
				goodPluszBad = goodPluszBad - arrive.getListQuntity(comboBox).get(i);

			} else {

				remaining = arrive.getListQuntity(comboBox).get(i) - goodPluszBad;
				goodPluszBad = 0;
			}

			try {
				con = DriverManager.getConnection(DatabaseCon.getUrl(), DatabaseCon.getName(),
						DatabaseCon.getPassword());
				String Query = "Update pls.heattreatment set quantity='" + remaining + "'" + "where ID='" + id + "'"
						+ "AND Status='" + "Arrived" + "'";
				Statement Add = con.createStatement();
				Add.executeUpdate(Query);

			} catch (Exception el) {

				el.printStackTrace();

			}

		}
	}
}
