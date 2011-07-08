package eureka.base;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import eureka.environment.Effector;
import javax.persistence.Column;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Representa uma regra com um conjunto de cl�usulas antecedentes e um 
 * cl�usula consequente
 */
@Entity
public class Rule implements Observer {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(unique = true)
    private String label;//nome da regra
    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Clause> antecedent;//lista de cl�usulas antecedentes(predicado)
    @OneToOne
    private BooleanClause consequent;//cl�usula consequente
    private Boolean truth;//valor que indica se todos os precedentes s�o verdade
    @ManyToMany
    private List<Effector> effectors;//Atuadores, ainda n�o implementado
    private boolean fired;

    protected Rule() {
        // para o hibernate!
    }

    /**
     * Uma regra possui um identificador, uma lista de antecedentes e 
     * um consequente
     */
    public Rule(String label, Clause[] antecedent, BooleanClause consequent) {
        this.label = label;
        this.antecedent = new ArrayList<Clause>();
        this.consequent = consequent;
        for (Clause clause : antecedent) {
            this.antecedent.add(clause);
            clause.addObserver(this);
        }
        this.effectors = new ArrayList<Effector>();
        setFired(false);
    }

    /**
     * Retorna o nome da Regra
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Cadastra um atudador para ser disparado quando a regra � ativada
     */
    public void subscribeEffector(Effector effector) {
        if (!effectors.contains(effector)) {
            effectors.add(effector);
        }
    }

    /**
     * Retrona a lista de cl�usulas no predicado da regra
     */
    public List<Clause> getAntecedent() {
        return this.antecedent;
    }

    /**
     * Retorna a cl�usula consequente da regra
     */
    public BooleanClause getConsequent() {
        return this.consequent;
    }

    /**
     * Retorna <i>true</i> se todas as cl�usulas no predicado da
     * regra s�o satisfeitos, <i>false</i> caso pelo menos uma das
     * regras seja falsa ou <i>null</i> se ainda n�o est� definido
     */
    public Boolean getTruth() {
        return this.truth;
    }

    @Override
    public void update() {
        boolean truth = true;
        boolean allClauses = true;
        for (Clause clause : antecedent) {
            if (clause.getTruth() == null) {
                allClauses = false;
            } else if (!clause.getTruth()) {
                truth = false;
            }
        }
        if (!truth) {
            this.truth = false;
        } else if (truth && allClauses) {
            this.truth = truth;
        }
    }

    public void setFired(boolean fired) {
        this.fired = fired;
    }

    public boolean hasFired() {
        return fired;
    }

    /**
     * ativa o consequente da regra e dispara os atuadores cadastrados a ela
     */
    public void fire(WorkingMemory wm) {
        if (consequent.getOperator().equals(EOperator.RECEIVE)) {
            wm.addVariable(new RuleVariable(consequent.getVariableLabel(), 
                    consequent.getTargetValue()));
        }
        for (Effector effector : effectors) {
            effector.fire();
        }
        setFired(true);
    }
}
