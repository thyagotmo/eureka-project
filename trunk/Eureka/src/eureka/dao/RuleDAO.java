package eureka.dao;

import eureka.base.Rule;
import java.util.Collection;

/**
 *
 * @author Marcos
 */
public interface RuleDAO extends AbstractDAO<Long, Rule> {
    
    void addAllRules(Collection<Rule> rules);
    
    Rule findByLabel(String label);

    void remove(String label);
}
