package Classes;

import javax.swing.JTextField;

public class NextWeek {
	
	public void nextWeek(JTextField weekChangeTxt) {
		
		int week = Integer.parseInt(weekChangeTxt.getText());
		
		int weekPlusz1= week+1;
		
		weekChangeTxt.setText(String.valueOf(weekPlusz1));
		
	}

}
