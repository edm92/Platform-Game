package std;

import javax.swing.JOptionPane;

public class debug {
	public static boolean DEBUG_MODE = true;
	
	public static String endl = "\n";	// Replace with java system based newline eventually
	
	public static void print(String displayDetails){
		String threadName = Thread.currentThread().getName();
		if(DEBUG_MODE){
			// CMD line based output
			
			System.out.format("%s: %s%n", threadName, displayDetails);
		}else{
			popup(displayDetails);
		}
	}
	
	public static void popup(String displayDetails){
		String threadName = Thread.currentThread().getName();
		JOptionPane.showMessageDialog(null, displayDetails, "Thread:" + threadName,JOptionPane.WARNING_MESSAGE);
	}
	
}
