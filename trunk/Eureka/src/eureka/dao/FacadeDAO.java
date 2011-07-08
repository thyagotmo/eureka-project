package eureka.dao;

/**
 *
 * @author Marcos
 */
public abstract class FacadeDAO {
    
    private static FacadeDAO instance = new DefaultFacadeDAO();
    
    public static void changeFacade(FacadeDAO facadeDAO) {
        instance = facadeDAO;
    }
    
    public static FacadeDAO getFacadeDAO() {
        return instance;
    }
    
    public abstract RuleDAO getRuleDAO();
    
    public abstract ClauseDAO getClauseDAO();
    
    
}
