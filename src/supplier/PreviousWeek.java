package supplier;

import javax.swing.JTextField;

public class PreviousWeek {
	
	public void prewWeek(JTextField weekChangeTxt) {
		
		int week = Integer.parseInt(weekChangeTxt.getText());
		
		int weekMinusz1= week-1;
		
		weekChangeTxt.setText(String.valueOf(weekMinusz1));
		
	}

}
