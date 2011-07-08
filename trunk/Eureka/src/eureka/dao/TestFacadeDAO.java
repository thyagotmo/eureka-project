package eureka.dao;

import eureka.base.Clause;
import eureka.base.RuleBase;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class TestFacadeDAO extends FacadeDAO {

    private RuleDAO ruleBase;
    private ClauseDAO clauseDAO;

    public TestFacadeDAO() {
        ruleBase = new RuleBase();
        clauseDAO = new ClauseDAO() {

            @Override
            public List<Clause> findByVariable(String variable) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void saveOrUpdate(Clause e) {
            }

            @Override
            public void remove(Clause e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void removeAll(Collection<Clause> all) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Clause findById(Long id) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Collection<Clause> findAll() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }

    @Override
    public RuleDAO getRuleDAO() {
        return ruleBase;
    }

    @Override
    public ClauseDAO getClauseDAO() {
        return clauseDAO;
    }

}
