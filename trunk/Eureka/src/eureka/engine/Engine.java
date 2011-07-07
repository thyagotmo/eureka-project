package eureka.engine;

import java.util.Hashtable;

import eureka.base.RuleBase;
import eureka.base.WorkingMemory;

public interface Engine{
	
	public void init (WorkingMemory wm, RuleBase rb, Hashtable<Object, Object> params);
	public void execute();
}
