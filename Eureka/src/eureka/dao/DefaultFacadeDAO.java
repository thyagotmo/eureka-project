/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eureka.dao;

/**
 *
 * @author Marcos
 */
public class DefaultFacadeDAO extends FacadeDAO {
    
    private RuleDAO ruleDAO;
    private ClauseDAO clauseDAO;

    public DefaultFacadeDAO() {
        ruleDAO = new RuleHibernateDAO();
        clauseDAO = new ClauseHibernateDAO();
    }

    @Override
    public RuleDAO getRuleDAO() {
        return ruleDAO;
    }

    @Override
    public ClauseDAO getClauseDAO() {
        return clauseDAO;
    }
    
}
