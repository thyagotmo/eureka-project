//package eureka.main;
//
//import java.util.Hashtable;
//
//import javax.swing.JFileChooser;
//import javax.swing.JOptionPane;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
//
//import eureka.base.FactsParser;
//import eureka.base.RuleBase;
//import eureka.base.RuleParser;
//import eureka.base.WorkingMemory;
//import eureka.base.exceptions.InvalidOperatorException;
//import eureka.engine.BackwardChaining;
//import eureka.engine.Engine;
//import eureka.engine.ForwardChaining;
//
//public class Main {
//
//	public static Engine forwardTest(RuleBase rb, WorkingMemory wm) {
//		ForwardChaining f = new ForwardChaining();
//
//		Hashtable<Object, Object> params = new Hashtable<Object, Object>();
//
//		f.init(wm, rb, params);
//
//		return f;
//	}
//
//	public static Engine backwardTest(RuleBase rb, WorkingMemory wm, String variable, String value) {
//		BackwardChaining b = new BackwardChaining();
//
//		Hashtable<Object, Object> params = new Hashtable<Object, Object>();
//		if(variable != null) {
//			params.put(BackwardChaining.GOAL_VARIABLE, variable);
//		}
//		if(value != null) {
//			params.put(BackwardChaining.GOAL_VALUE, value);
//		}
//
//		b.init(wm, rb, params);
//
//		return b;
//	}
//
//	public static void main(String[] args) throws NumberFormatException,
//			InvalidOperatorException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
//
//		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		RuleBase rb = new RuleBase();
//		WorkingMemory wm = new WorkingMemory();
//
//		JOptionPane.showMessageDialog(null, "Por favor escolha o arquivo contendo a definição de regras");
//
//		JFileChooser jc = new JFileChooser();
//
//		int acceptRule = jc.showOpenDialog(null);
//
//		String rules = jc.getSelectedFile().getAbsolutePath();
//
//		JOptionPane.showMessageDialog(null, "Por favor escolha o arquivo contendo os fatos");
//
//		int acceptFacts = jc.showOpenDialog(null);
//
//		String facts = jc.getSelectedFile().getAbsolutePath();
//
//
//		if(acceptRule == JFileChooser.APPROVE_OPTION) {
//			rb.addAllRules(RuleParser.loadRules(rules));
//		}else {
//			JOptionPane.showMessageDialog(null, "São necessárias regras para executar o algoritmo", "Erro", JOptionPane.ERROR_MESSAGE);
//		}
//
//		if(acceptFacts == JFileChooser.APPROVE_OPTION) {
//			wm.addAllVariables(FactsParser.loadFacts(facts));
//		}else {
//			JOptionPane.showMessageDialog(null, "Nenhum Fato adicionado ao banco de fatos", "Erro", JOptionPane.WARNING_MESSAGE);
//		}
//
//		String[] options = {"Backward Chaining", "Forward Chaining"};
//		int option = JOptionPane.showOptionDialog(null, "Escolha o Algoritmo que quer executar", "Algoritmo", JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
//
//		Engine e = null;
//
//		if(option == 0) {
//			String variavel = JOptionPane.showInputDialog(null, "Diga o nome da variavel que deseja saber o valor");
//			String valor = JOptionPane.showInputDialog(null, "Escreva o possvel valor, ou cancele caso não saiba");
//
//			 e = backwardTest(rb, wm, variavel, valor);
//		}else {
//			 e = forwardTest(rb, wm);
//		}
//
//		e.execute();
//
//		wm.printState();
//	}
//}
