package harbour.spring.service;

import harbour.spring.dao.TodoDao;
import harbour.spring.domain.Todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	@Autowired
	private TodoDao todoDao;
	
	public List<Todo> getAllTodo() {
		return todoDao.findAll();
	}
	
	public Long createTodo(Todo todo) {
		Number key = todoDao.insertAndReturnKey(todo);
		return key.longValue();
	}
	
	public void updateTodo(Todo todo) {
		todoDao.update(todo);
	}
	
	public void deleteTodo(Long id) {
		todoDao.delete(id);
	}
}
