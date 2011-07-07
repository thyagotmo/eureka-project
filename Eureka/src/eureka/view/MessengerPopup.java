package eureka.view;

import javax.swing.JOptionPane;

/**Classe que reutiliza o JOptionPane para mostrar prompts ao usuário*/
public class MessengerPopup {
	public static final int OK_CANCEL_OPTIONS = JOptionPane.OK_CANCEL_OPTION;
	public static final int YES_NO_OPTIONS = JOptionPane.YES_NO_OPTION;
	public static final int YES_NO_CANCEL_OPTIONS = JOptionPane.YES_NO_CANCEL_OPTION;
	public static final int OK = JOptionPane.OK_OPTION;
	public static final int CANCEL = JOptionPane.NO_OPTION;
	public static final int YES = JOptionPane.YES_OPTION;
	public static final int NO = JOptionPane.NO_OPTION;
	
	public static void errorMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}
	public static void infoMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	public static String inputMessage(String message, String title) {
		return JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	public static int optionMessage(String message, String title, int options) {
		return JOptionPane.showConfirmDialog(null, message, title, options);
	}
}
