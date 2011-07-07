package eureka.base;

public abstract class Clause extends Subject{

	private Boolean truth;
	private String variable;
	
	public void setVariable(String variableLabel) {
		this.variable = variableLabel;
	}
	
	/**Configura o valor de veracidade da clusula*/
	public void setTruth(Boolean truth) {
		this.truth = truth;
		notifyAllObservers();
	}
	
	/**Retorna o nome da varivel referenciada na clusula*/
	public String getVariableLabel() {
		return variable;
	}
	
	/**Verifica se a clusula  veradeira checando os valores na Working Memory*/
	public abstract Boolean check(WorkingMemory wm);
	
	/**Retorna <i>true</i> se a clusula  verdadeira*/
	public Boolean getTruth() {
		return truth;
	}
}
