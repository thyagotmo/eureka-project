package eureka.engine;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import eureka.base.Clause;
import eureka.base.Rule;
import eureka.base.RuleBase;
import eureka.base.WorkingMemory;
import eureka.base.exceptions.InvalidOperatorException;

/**
 * Algoritmo que tenta extrair o mximo de informao de um conjunto de fatos
 * na Working memory
 */
public class ForwardChaining implements Engine {
	
    public static final String RULES_PATH = "RULES_PATH";
    public static final String FACTS_PATH = "FACTS_PATH";
	
    private ConflictResolutionStrategy strategy;
    private List<Rule> conflictSet;
    private List<Rule> excludedSet;
    private WorkingMemory wm;
    private RuleBase rb;
	
    public ForwardChaining() {
        this.conflictSet = new ArrayList<Rule>();
        this.excludedSet = new ArrayList<Rule>();
        this.strategy = new FirstElementStrategy();
    }

    /**
     * Verifica as regras que podem ser ativadas
     */
    private List<Rule> match() {
        List<Rule> conflictSet = new ArrayList<Rule>();
        //Para cada regra no banco de regras
        for (Rule rule : rb.findAll()) {

            //para cada precedente
            for (Clause precedent : rule.getAntecedent()) {
                try {
                    //verifica o precedente
                    precedent.check(wm);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (InvalidOperatorException e) {
                    e.printStackTrace();
                }
            }
            //se a regra pode ser acionada, adiciona do grupo de conflito
            if (rule.getTruth() != null && rule.getTruth() && !rule.hasFired()) {

                conflictSet.add(rule);
            }
        }
        return conflictSet;
    }
    
    /**
     * Escolhe uma regra do grupo de regras para avaliar
     */
    private Rule conflictResolution(List<Rule> conflictSet) {
        return this.strategy.resolve(conflictSet);
    }

    /**
     * Inicializa o algoritmo com o arquivo contendo a defino das regras
     * e o conjunto de fatos
     */
    @Override
    public void init(WorkingMemory wm, RuleBase rb, Hashtable<Object, Object> params) {
        this.wm = wm;
        this.rb = rb;
        conflictSet.clear();
        excludedSet.clear();
    }
	
    /**
     * Executa o algoritmo
     */
    @Override
    public void execute() {
        Rule selected = null;
        do {
            conflictSet.clear();
            conflictSet.addAll(match());

            selected = conflictResolution(conflictSet);

            if (selected != null) {
                selected.fire(wm);
            }
        } while (!conflictSet.isEmpty());
    }
}
