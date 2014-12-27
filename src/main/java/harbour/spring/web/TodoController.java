package harbour.spring.web;

import harbour.spring.domain.Todo;
import harbour.spring.service.TodoService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {
	final static Logger logger = LoggerFactory.getLogger(TodoController.class);
	
	@Autowired
	private TodoService todoService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Todo> list() {
		logger.info("Listing todos");
		return todoService.getAllTodo();
	}
}
