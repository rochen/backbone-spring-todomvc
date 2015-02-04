package harbour.spring.web;

import harbour.spring.domain.Todo;
import harbour.spring.service.TodoService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

@Controller
@RequestMapping("/todos")
public class TodoController {
	final static Logger logger = LoggerFactory.getLogger(TodoController.class);
	
	@Autowired
	private TodoService todoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		return path.endsWith("/") ? "redirect:/todos": "todo/index";
	}
	
	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public @ResponseBody List<Todo> list() {
		return todoService.getAllTodo();
	}
	
	@RequestMapping(value = "/api/{id}", method = RequestMethod.PUT)
	public @ResponseBody Todo edit(@PathVariable Long id, @RequestBody Todo todo) {
		todoService.updateTodo(todo);
		return todo;
	}
	
	@RequestMapping(value = "/api/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		todoService.deleteTodo(id);
	}
	
	@RequestMapping(value = "/api", method = RequestMethod.POST)
	public @ResponseBody Todo save(@RequestBody Todo todo) {
		Long id = todoService.createTodo(todo);
		todo.setId(id);
		return todo;
	}
}
