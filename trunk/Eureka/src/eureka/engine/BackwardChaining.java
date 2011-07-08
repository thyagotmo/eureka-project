package eureka.engine;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

import eureka.base.BooleanClause;
import eureka.base.Clause;
import eureka.base.EOperator;
import eureka.base.Rule;
import eureka.base.RuleBase;
import eureka.base.RuleVariable;
import eureka.base.WorkingMemory;
import eureka.base.exceptions.InvalidOperatorException;
import eureka.dao.FacadeDAO;
import eureka.dao.RuleDAO;
import eureka.view.MessengerPopup;

/**
 * Algoritmo de inferncia Backward chaining, dispara as regras certas a fim de
 * encontrar o valor de uma varivel
 */
public class BackwardChaining implements Engine {

    public static final String GOAL_VARIABLE = "GOAL_VARIABLE";
    public static final String GOAL_VALUE = "GOAL_VALUE";
        
    private static final String INFO_REQUEST = Messages.getString("BackwardChaining.INFO_REQUEST");
    private static final String INFO_REQUEST_TITLE = Messages.getString("BackwardChaining.INFO_REQUEST_TITLE");

    private WorkingMemory wm;
    private RuleDAO rb;
    private Hashtable<Object, Object> params;

    /**
     * Retorna as Regras que atribui o valor <i>value</i> a variavel
     * <i>variable</i> ou s as regras que possui <i>variable</i> no consequente
     * caso <i>valu</i> seja null
     */
    private List<Rule> goalRules(String variable, String value) {

        rb = FacadeDAO.getFacadeDAO().getRuleDAO();
        
        List<Rule> goalRules = new ArrayList<Rule>();
        for (Rule rule : rb.findAll()) {
            if (rule.getConsequent().getVariableLabel().equals(variable)) {
                if (value != null) {
                    if (rule.getConsequent().getTargetValue().equals(value)) {
                        goalRules.add(rule);
                    }
                } else {
                    goalRules.add(rule);
                }
            }
        }
        return goalRules;
    }

    /**
     * inicializa o algoritmo adicionando WorkingMemory os fatos no arquivo
     * factsPath(ou no faz nada se factsPath for null)<br>
     * e Adiciona as regras do arquivo rulesPath(ou no faz nada se rulesPath for
     * null)
     */
    @Override
    public void init(WorkingMemory wm,
            Hashtable<Object, Object> params) {
        this.params = params;
        this.wm = wm;
    }

    /**
     * Executa o algoritmo tentando verificar se o valor da <i>varivel</i>
     * <i>value<i><br>
     * ou qual o valor da varivel caso <i>value</i> seja null
     */
    @Override
    public void execute() {
        execute((String) params.get(GOAL_VARIABLE),
                (String) params.get(GOAL_VALUE));
    }

    private void execute(String variable, String value) {
        // pilha de regras que alteram a varivel objetivo
        Stack<Rule> goalStack = new Stack<Rule>();

        List<Rule> goalRules = goalRules(variable, value);

        goalStack.addAll(goalRules);

        if (!goalStack.isEmpty()) {
            wm.referTo(variable);
        }
        while (!goalStack.isEmpty() && wm.getVariable(variable) == null) {

            Rule rule = goalStack.peek();
            List<Clause> undefinedClauses = new ArrayList<Clause>();

            try {
                for (Clause c : rule.getAntecedent()) {
                    if (rule.getTruth() == null) {
                        c.check(wm);
                        if (c.getTruth() == null) {
                            undefinedClauses.add(c);
                        }
                    }
                }

                for (Clause c : undefinedClauses) {
                    rule.update();
                    if (rule.getTruth() == null) {

                        execute(c.getVariableLabel(), null);

                        c.check(wm);

                        if (c.getTruth() == null) {

                            // se nao houver regras referindo-se a essa variavel no consequente
                            if (!wm.wasReferred(c.getVariableLabel())) {

                                // pergunta ao usuario se a clausula e verdadeira
                                boolean isTrue = askValidity(c);

                                if (isTrue) {

                                    c.setTruth(true);
                                    if (c instanceof BooleanClause) {
                                        BooleanClause booleanClause = (BooleanClause) c;
                                        // se a clausula era uma verificacao de igualdade e for verdade
                                        if (booleanClause.getOperator().equals(EOperator.EQUALS)) {
                                            // atribui o valor Working memory
                                            wm.addVariable(new RuleVariable(
                                                    booleanClause.getVariableLabel(),
                                                    booleanClause.getTargetValue()));
                                        }
                                    }
                                } else {
                                    c.setTruth(false);
                                    // pergunta qual o valor ao variavel
                                    String typedValue = requestValue(c.getVariableLabel());

                                    // se o usuario souber, atribui o novo valor
                                    if (typedValue != null) {
                                        wm.addVariable(new RuleVariable(c.getVariableLabel(), typedValue));
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
            } catch (InvalidOperatorException e1) {
                e1.printStackTrace();
            }

            rule.update();
            //se a regra e verdadeira, dispara o consequente
            if (rule.getTruth() != null && rule.getTruth()) {
                rule.fire(wm);
            }
            goalStack.pop();
        }
    }

    private boolean askValidity(Clause c) {
        int option = MessengerPopup.optionMessage(c + " ?", INFO_REQUEST_TITLE,
                MessengerPopup.YES_NO_OPTIONS);
        return option == MessengerPopup.YES;
    }

    private String requestValue(String variable) {
        return MessengerPopup.inputMessage(INFO_REQUEST + variable,
                INFO_REQUEST_TITLE);
    }
}
