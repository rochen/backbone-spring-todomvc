package harbour.spring.web;

import harbour.spring.domain.Todo;
import harbour.spring.service.TodoService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/todos")
public class TodoController {
	final static Logger logger = LoggerFactory.getLogger(TodoController.class);
	
	@Autowired
	private TodoService todoService;

//	@RequestMapping(method = RequestMethod.GET)
//	public String index() {
//		logger.info("index()");
//		return "todo/index";
//	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Todo> list() {
		logger.info("Listing todos");
		return todoService.getAllTodo();
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public @ResponseBody Todo edit(@PathVariable Long id, @RequestBody Todo todo) {
		logger.info("edit todos");
		todoService.updateTodo(todo);
		return todo;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		logger.info("delete todos");
		todoService.deleteTodo(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Todo save(@RequestBody Todo todo) {
		logger.info("save todos");
		Long id = todoService.createTodo(todo);
		todo.setId(id);
		return todo;
	}
}
