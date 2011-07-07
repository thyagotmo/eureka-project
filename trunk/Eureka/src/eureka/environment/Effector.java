package eureka.environment;

public abstract class Effector {
	
	private String label;
	
	public Effector(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public abstract void fire();
}
