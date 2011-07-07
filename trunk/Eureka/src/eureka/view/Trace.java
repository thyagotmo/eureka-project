package eureka.view;

import javax.swing.JOptionPane;

/**Classe que manipula o Trace*/
public class Trace {
	
	public static void appendMessage(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);//por enquanto só um println, mas pode ser mudado para JTextBox
	}
}
