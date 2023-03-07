package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Noti {

	public static void getMessage(String strs) {
		JOptionPane.showMessageDialog(new JFrame(), strs, "Dialog", JOptionPane.ERROR_MESSAGE);
	}
}
