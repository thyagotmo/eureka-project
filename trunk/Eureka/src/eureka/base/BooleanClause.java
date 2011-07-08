package eureka.base;

import javax.persistence.Entity;

import eureka.base.exceptions.InvalidOperatorException;
import javax.persistence.Column;

/**
 * Representa uma clusula da regra que pode ser uma condio
 * ou uma ao dependendo do operador
 */
@Entity
public class BooleanClause extends Clause {

    private EOperator op;//operador
    private String value;//valor usado pelo operador

    public BooleanClause() {
    }

    /**
     * Uma clusula contm uma varivel um operador e um valor<br>
     * ex.: veiculo = minivan<br>
     * verifica se valor da varivel carro na WorkingMemory  igual a minivan
     */
    public BooleanClause(String variable, EOperator op, String value) {
        setVariable(variable);
        this.op = op;
        this.value = value;
    }

    /**
     * Constri a clusula usando uma string no formato correto<br>
     * ex.: veiculo <- minivan<br>
     * constriu uma clusula com varivel veiculo, operador de atribuio <-<br>
     * e valor minivan
     */
    public BooleanClause(String clause) {
        for (EOperator e : EOperator.getOperators()) {

            if (clause.matches("(.)*" + e.getSymbol() + "(.)*")) {
                op = e;
                setVariable(clause.split(e.getSymbol())[0].trim());
                value = clause.split(e.getSymbol())[1].trim();
            }

        }
    }

    public EOperator getOperator() {
        return op;
    }

    /**
     * Retorna o valor do lado direito da clusula
     */
    public String getTargetValue() {
        return value;
    }

    /**
     * Verifica se a clusula  veradeira checando os valores na Working Memory
     */
    @Override
    public Boolean check(WorkingMemory wm) {
        //recebe o valor da varivel na working memory
        RuleVariable variable = wm.getVariable(getVariableLabel());
        Boolean truth = getTruth();
        if (variable == null) {//varivel ainda no possui valor
            truth = null;
        } else {
            if (op.equals(EOperator.RECEIVE)) {//operao de atribuio no precisa ser avaliada
                truth = true;
            } else if (op.equals(EOperator.EQUALS)) {
                truth = variable.getValue().equals(value);

            } else if (op.equals(EOperator.NOT_EQUALS)) {
                truth = !variable.getValue().equals(value);
            } else {//caso seja um operador relacional, os valores devem ser numricos
                Double leftHandSide = Double.valueOf(variable.getValue());
                Double rightHandSide = Double.valueOf(value);
                switch (op) {
                    case GREATER_OR_EQUAL:
                        truth = leftHandSide >= rightHandSide;
                        break;
                    case GREATHER_THAN:
                        truth = leftHandSide > rightHandSide;
                        break;
                    case LESS_OR_EQUAL:
                        truth = leftHandSide <= rightHandSide;
                        break;
                    case LESS_THAN:
                        truth = leftHandSide < rightHandSide;
                        break;
                    default:
                        throw new InvalidOperatorException();
                }
            }
        }
        notifyAllObservers();//notifica as regras que contm a clusula
        return truth;
    }

    @Override
    public String toString() {
        return getVariableLabel() + " " + op.getSymbol() + " " + value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BooleanClause) {
            BooleanClause c = (BooleanClause)obj;
            if(c.getOperator().equals(getOperator()) &&
                    c.getVariableLabel().equals(getVariableLabel()) &&
                    c.getTargetValue().equals(getTargetValue())) {
            return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.op != null ? this.op.hashCode() : 0);
        hash = 17 * hash + (this.value != null ? this.value.hashCode() : 0);
        hash += getVariableLabel().hashCode();
        return hash;
    }

}
