package harbour.spring.dao;

import harbour.spring.domain.Todo;

import java.util.List;

public interface TodoDao {

	List<Todo> findAll();
	void insert(Todo todo);
	void update(Todo toto);
	void delete(Long id);
}
