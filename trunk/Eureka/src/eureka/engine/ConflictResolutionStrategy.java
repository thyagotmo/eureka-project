package eureka.engine;

import java.util.List;

import eureka.base.Rule;

public interface ConflictResolutionStrategy {
	public Rule resolve(List<Rule> conflictSet);
}
