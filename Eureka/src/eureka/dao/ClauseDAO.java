package eureka.dao;

import eureka.base.Clause;
import java.util.List;

/**
 *
 * @author Marcos
 */
public interface ClauseDAO extends AbstractDAO<Long, Clause> {
    
    List<Clause> findByVariable(String variable);
}
