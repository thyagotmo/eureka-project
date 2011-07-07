package eureka.engine;

import java.util.List;

import eureka.base.Rule;

/**Uma implementação do Forward Chanining*/
public class FirstElementStrategy implements ConflictResolutionStrategy{


	@Override
	public Rule resolve(List<Rule> conflictSet) {
		if(!conflictSet.isEmpty()) {
			return conflictSet.get(0);//retorna o primeiro elemento 
		}
		return null;//ou null se não houver elementos
	}
}
