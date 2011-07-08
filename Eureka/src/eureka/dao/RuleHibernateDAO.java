package eureka.dao;

import eureka.base.Rule;

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

}
