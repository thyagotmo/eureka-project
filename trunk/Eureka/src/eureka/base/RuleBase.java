package eureka.base;

import eureka.dao.RuleDAO;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Base de dados que contm todas as regras
 */
public class RuleBase implements RuleDAO {

    /**
     * Mapa que contm todas as regras mapeadas pelo nome
     */
    private Map<String, Rule> rules = new HashMap<String, Rule>();

    @Override
    public void addAllRules(Collection<Rule> rules) {
        for (Rule rule : rules) {
            this.rules.put(rule.getLabel(), rule);
        }
    }

    @Override
    public Rule findByLabel(String label) {
        return rules.get(label);
    }
    /**
     * Adiciona uma regra na base de regras
     */
    @Override
    public void saveOrUpdate(Rule rule) {
        rules.put(rule.getLabel(), rule);
    }

    /**
     * Remove uma regra da base de regras
     */
    @Override
    public void remove(String ruleLabel) {
        rules.remove(ruleLabel);
    }

    @Override
    public void remove(Rule e) {
        remove(e.getLabel());
    }

    @Override
    public void removeAll(Collection<Rule> rules) {
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
    @Override
    public Collection<Rule> findAll() {
        return rules.values();
    }

    @Override
    public Rule findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
