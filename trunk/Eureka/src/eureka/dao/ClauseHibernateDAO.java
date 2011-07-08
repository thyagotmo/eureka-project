package eureka.dao;

import eureka.base.Clause;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class ClauseHibernateDAO extends HibernateDAO<Long, Clause> implements ClauseDAO {

    public ClauseHibernateDAO() {
        super(Clause.class);
    }

    @Override
    public List<Clause> findByVariable(String variable) {
        return getSession().createQuery("FROM Clause WHERE variable = ?").list();
    }
}
