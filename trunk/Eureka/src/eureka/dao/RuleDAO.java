package eureka.dao;

import eureka.base.Rule;

/**
 *
 * @author Marcos
 */
public interface RuleDAO extends AbstractDAO<Long, Rule> {
    
    Rule findByLabel(String label);
}
