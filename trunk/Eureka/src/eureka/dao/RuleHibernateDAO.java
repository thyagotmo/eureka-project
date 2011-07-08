package eureka.dao;

import eureka.base.Rule;
import java.util.Collection;

/**
 *
 * @author Marcos
 */
public class RuleHibernateDAO extends HibernateDAO<Long, Rule> implements RuleDAO {

    public RuleHibernateDAO() {
        super(Rule.class);
    }

    @Override
    public Rule findByLabel(String label) {
        return (Rule) getSession().
                createQuery("FROM Rule WHERE label = ?").setString(0, label).
                uniqueResult();
    }

    @Override
    public void addAllRules(Collection<Rule> allRules) {
        for(Rule rule : allRules) {
            saveOrUpdate(rule);
        }
    }

    @Override
    public void remove(String label) {
        remove(findByLabel(label));
    }

}
