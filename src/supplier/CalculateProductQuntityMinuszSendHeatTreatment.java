package supplier;

import java.util.ArrayList;

import javax.swing.JComboBox;

import heat.treatment.SendHeatTreatmentQuantitys;

public class CalculateProductQuntityMinuszSendHeatTreatment {

	SendHeatTreatmentQuantitys sendHeatTreatmentQuantitys = new SendHeatTreatmentQuantitys();
	ProductQuntitysForStat productQuntitysForStat = new ProductQuntitysForStat();
	

	public ArrayList<Integer> ProdQuntityMinuszSendHeatTreatment(JComboBox<String> combo)  
	{ 
		String prodNumber = combo.getModel().getSelectedItem().toString();
	
		ArrayList<Integer> calculate = new ArrayList<>();

		int heatTreatmentQuantity = sendHeatTreatmentQuantitys.SendHeatTreatmentQuantity();
		
		calculate = productQuntitysForStat.getListQuntity(prodNumber);
		int helper = 0;

		for (int i = 0; i < productQuntitysForStat.getListQuntity(prodNumber).size(); i++) {

			if (heatTreatmentQuantity >= calculate.get(i)) 
			{
				helper = calculate.get(i);
				calculate.set(i, calculate.get(i) - calculate.get(i));

				heatTreatmentQuantity = heatTreatmentQuantity - helper;

			} else {

				calculate.set(i, (calculate.get(i) - heatTreatmentQuantity));
				heatTreatmentQuantity = 0;
			}
		}
		return calculate;

	}

}
