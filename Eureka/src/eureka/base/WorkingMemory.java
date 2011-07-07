package eureka.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import eureka.view.Trace;

/**
 * Classe que mant�m as vari�veis e seus valores na mem�ria
 */
public class WorkingMemory {
	private Map<String, RuleVariable> variables = new HashMap<String, RuleVariable>();
	private Set<String> referredVariables = new HashSet<String>();
	public WorkingMemory(){}
	
	/**Remove todas as vari�veis da Working Memory*/
	public void reset() {
		variables.clear();
		referredVariables.clear();
	}
	
	/**Faz referencia a variavel com o nome especificado, utilizado no processo de inferencia*/
	public void referTo(String variable) {
		referredVariables.add(variable);
	}
	/**Verifica se uma variavel foi referenciada durante o processo de inferencia*/
	public boolean wasReferred(String variable) {
		return referredVariables.contains(variable);
	}
	/**Adiciona uma vari�vel � mem�ria*/
	public void addVariable(RuleVariable variable) {
		variables.put(variable.getLabel(), variable);
	}
	public void addAllVariables(Collection<RuleVariable> variables) {
		for(RuleVariable variable : variables) {
			this.variables.put(variable.getLabel(), variable);
		}
	}
	public void removeAllVariables(Collection<RuleVariable> variables) {
		for(RuleVariable variable : variables) {
			this.variables.remove(variable.getLabel());
		}
	}
	public void removeAllVariablesFromLabels(Collection<String> variableLabels) {
		for(String label : variableLabels) {
			this.variables.remove(label);
		}
	}
	/**Retorna a varia�vel com o nome especificado*/
	public RuleVariable getVariable(String label) {
		return variables.get(label);
	}
	/**Retorna o conjunto de todas as vari�veis na mem�ria*/
	public Collection<RuleVariable> getAllVariables() {
		return variables.values();
	}
	/**Imprime o estado atual da mem�ria*/
	public void printState() {
		String s = "";
		for(RuleVariable variable : variables.values()) {
			s += (variable.getLabel() + " = " + variable.getValue() + System.getProperty("line.separator"));
		}
		Trace.appendMessage("Working Memory State", s);
	}
}
