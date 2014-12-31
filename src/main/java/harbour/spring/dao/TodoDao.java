package harbour.spring.dao;

import harbour.spring.domain.Todo;

import java.util.List;

public interface TodoDao {

	public List<Todo> findAll();

	public int insert(Todo todo);
	
	public Number insertAndReturnKey(Todo todo);

	public int update(Todo todo);

	public int delete(Long id);
	
	public Todo getTodoById(Long id);
}
