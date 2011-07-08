package eureka.dao;

import eureka.base.Clause;
import eureka.base.Rule;
import eureka.util.HibernateUtil;

/**
 *
 * @author Marcos
 */
public class Test {

    public static void main(String[] args) {
        RuleDAO ruleDAO = FacadeDAO.getFacadeDAO().getRuleDAO();
//        
//        HibernateUtil.beginTransaction();
//
//        Rule rule = ruleDAO.findByLabel("teste");
//        System.out.println(rule.getLabel());
//
//        ruleDAO.remove(rule);
//        HibernateUtil.commit();
        
        HibernateUtil.beginTransaction();
        
        ruleDAO.saveOrUpdate(new Rule("teste", new Clause[]{}, null));
        
        HibernateUtil.commit();
        

    }
}
