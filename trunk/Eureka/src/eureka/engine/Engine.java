package eureka.engine;

import java.util.Hashtable;

import eureka.base.RuleBase;
import eureka.base.WorkingMemory;

public interface Engine {

    void init(WorkingMemory wm, RuleBase rb, Hashtable<Object, Object> params);

    void execute();
}
