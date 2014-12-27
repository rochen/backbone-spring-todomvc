package harbour.spring.dao;

import harbour.spring.domain.Todo;

import java.util.List;

public interface TodoDao {

	public List<Todo> findAll();

	public void insert(Todo todo);

	public void update(Todo todo);

	public void delete(Long id);
	
	public Todo getTodoById(Long id);
}
