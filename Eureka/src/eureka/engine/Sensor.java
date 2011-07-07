package eureka.engine;

import eureka.base.Clause;
import eureka.base.WorkingMemory;

public class Sensor extends Clause{
	
	private String label;
	private String value;
	
	public Sensor(String label) {
		this.label = label;
		this.value = null;
	}
	
	public void update() {
		
	}
	
	public String getLabel(){
		return this.label;
	}
	
	public String getValue() {
		return this.value;
	}
	@Override
	public Boolean check(WorkingMemory wm) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
