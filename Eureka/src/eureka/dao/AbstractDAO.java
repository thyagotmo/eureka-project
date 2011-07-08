package eureka.dao;

import java.util.Collection;

public interface AbstractDAO<ID, E> {
	
	void saveOrUpdate(E e);
	
	void remove(E e);

        void removeAll(Collection<E> all);
	
	E findById(ID id);
	
	Collection<E> findAll();

}
