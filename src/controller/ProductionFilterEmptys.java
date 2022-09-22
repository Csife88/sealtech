package controller;

import javax.swing.JComboBox;
import javax.swing.JTextField;


public class ProductionFilterEmptys {

	
	
	protected String partEmptyBoxes(JComboBox<String> comboBox_1) {
		
		if(comboBox_1.getModel().getSelectedItem().toString().equals("")) {
			return " where PartNumber NOT LIKE '%?%'"; // kérdőjellel biztosan nem kezdődik cikkszám 
		}
		return " where PartNumber='"+comboBox_1.getModel().getSelectedItem().toString()+"'";
	}
	protected String workerEmptyBoxes(JComboBox<String> workerComboBox) {
		
		if(workerComboBox.getModel().getSelectedItem().toString().equals("")) {
			return " AND worker NOT LIKE '%?%'";
		}
		return " AND worker='"+workerComboBox.getModel().getSelectedItem().toString()+"'";
	}
	protected String rawMetarialEmptyBoxes(JComboBox<String> deliveryCombo) {
		
		if(deliveryCombo.getModel().getSelectedItem().toString().equals("")) {
			return " AND deliveryNote NOT LIKE '%?%'";
		}
		return " AND deliveryNote='"+deliveryCombo.getModel().getSelectedItem().toString()+"'";
	}
	protected String fromToAtisEmptyBoxes(JTextField textFieldForm, JTextField textFieldAt) {
		
		 if(textFieldForm.getText().equals("") && textFieldAt.getText().equals("") ) {
			 return " AND date NOT LIKE '%?%'";
		 }else if(textFieldAt.getText().equals("")) {
			 return " AND date >='"+textFieldForm.getText()+"'";
		 }else if(textFieldForm.getText().equals("")) {
			 return " AND date <='"+textFieldAt.getText()+"'";
		 }
		 return " AND date >='"+textFieldForm.getText()+"'"+" AND date <='"+textFieldAt.getText()+"'";
		
	}
	
}
