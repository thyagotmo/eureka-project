package eureka.base;

import java.util.ArrayList;
import java.util.List;

import eureka.environment.Effector;

/**Representa uma regra com um conjunto de cláusulas antecedentes e um cláusula consequente*/
public class Rule implements Observer {
	private String label;//nome da regra
	private List<Clause> antecedent;//lista de cláusulas antecedentes(predicado)
	private BooleanClause consequent;//cláusula consequente
	private Boolean truth;//valor que indica se todos os precedentes são verdade
	private List<Effector> effectors;//Atuadores, ainda não implementado
	private boolean fired;
	
	/**Uma regra possui um identificador, uma lista de antecedentes e um consequente*/
	public Rule (String label, Clause[] antecedent, BooleanClause consequent) {
		this.label = label;
		this.antecedent = new ArrayList<Clause>();
		this.consequent = consequent;
		for(Clause clause : antecedent) {
			this.antecedent.add(clause);
			clause.addObserver(this);
		}
		this.effectors = new ArrayList<Effector>();
		setFired(false);
	}
	/**Retorna o nome da Regra*/
	public String getLabel() {
		return this.label;
	}
	/**Cadastra um atudador para ser disparado quando a regra é ativada*/
	public void subscribeEffector(Effector effector) {
		if(!effectors.contains(effector)) {
			effectors.add(effector);
		}
	}
	
	/**Retrona a lista de cláusulas no predicado da regra*/
	public List<Clause> getAntecedent() {
		return this.antecedent;
	}
	/**Retorna a cláusula consequente da regra*/
	public BooleanClause getConsequent() {
		return this.consequent;
	}
	/**Retorna <i>true</i> se todas as cláusulas no predicado da
	 * regra são satisfeitos, <i>false</i> caso pelo menos uma das
	 * regras seja falsa ou <i>null</i> se ainda não está definido*/
	public Boolean getTruth() {
		return this.truth;
	}
	
	@Override
	public void update() {
		boolean truth = true;
		boolean allClauses = true;
		for(Clause clause : antecedent) {
			if(clause.getTruth() == null) {
				allClauses = false;
			}
			else if(!clause.getTruth()) {
				truth = false;
			}
		}
		if(!truth) {
			this.truth = false;
		}
		else if(truth && allClauses) {
			this.truth = truth;
		}
	}
	public void setFired(boolean fired) {
		this.fired = fired;
	}
	public boolean hasFired() {
		return fired;
	}
	
	/**ativa o consequente da regra e dispara os atuadores cadastrados a ela*/
	public void fire(WorkingMemory wm) {
		if(consequent.getOperator().equals(EOperator.RECEIVE)) {
			wm.addVariable(new RuleVariable(consequent.getVariableLabel(), consequent.getTargetValue()));
		}
		for(Effector effector : effectors) {
			effector.fire();
		}
		setFired(true);
	}
}
