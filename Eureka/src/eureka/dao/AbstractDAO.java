package eureka.dao;



import java.util.List;

public interface AbstractDAO<ID, E> {
	
	void saveOrUpdate(E e);
	
	void delete(E e);
	
	E findById(ID id);
	
	List<E> findAll();
}
