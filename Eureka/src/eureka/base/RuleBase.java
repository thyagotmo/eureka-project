package eureka.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Base de dados que contm todas as regras
 */
public class RuleBase {

    /**
     * Mapa que contm todas as regras mapeadas pelo nome
     */
    private Map<String, Rule> rules = new HashMap<String, Rule>();

    /**
     * Apaga todas as regras da base de regras
     */
    public void reset() {
        rules.clear();
    }

    public void addAllRules(Collection<Rule> rules) {
        for (Rule rule : rules) {
            this.rules.put(rule.getLabel(), rule);
        }
    }

    /**
     * Adiciona uma regra na base de regras
     */
    public void addRule(Rule rule) {
        rules.put(rule.getLabel(), rule);
    }

    /**
     * Remove uma regra da base de regras
     */
    public void removeRule(String ruleLabel) {
        rules.remove(ruleLabel);
    }

    public void removeAllRules(Collection<Rule> rules) {
        for (Rule rule : rules) {
            this.rules.remove(rule.getLabel());
        }
    }

    public void removeAllRulesFromLabels(Collection<String> ruleLabels) {
        for (String rule : ruleLabels) {
            this.rules.remove(rule);
        }
    }

    /**
     * Retrona todas as regras
     */
    public Collection<Rule> getAllRules() {
        return rules.values();
    }
}
