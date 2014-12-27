package harobur.spring.dao;

import static org.junit.Assert.assertEquals;
import harbour.spring.dao.TodoDao;
import harbour.spring.domain.Todo;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testContext.xml")
public class TodoDaoTest extends AbstractJUnit4SpringContextTests {
	final static Logger logger = LoggerFactory.getLogger(TodoDaoTest.class);
	
	@Resource
	private TodoDao todoDao;
	
	@Test
	public void insertTest() {
		int before = todoDao.findAll().size();
		
		Todo todo = new Todo();
		todo.setTodo("junit insert test");
		todo.setCompleted(Boolean.FALSE);		
		todoDao.insert(todo);
		
		int after = todoDao.findAll().size();
		logger.info("before:{}, after:{}", before, after);
		assertEquals(1, after - before);
	}
	
	@Test
	public void insertAndReturnKeyTest() {
		int before = todoDao.findAll().size();
		
		Todo todo = new Todo();
		todo.setTodo("junit insert and return key test");
		todo.setCompleted(Boolean.FALSE);		
		Number key = todoDao.insertAndReturnKey(todo);
		logger.info("key:[{}]", key);
		
		int after = todoDao.findAll().size();	
		logger.info("before:{}, after:{}", before, after);
		assertEquals(1, after - before);
	}
	
	@Test
	public void deleteTest() {
		List<Todo> todos = todoDao.findAll();
		Todo todo = todos.get(0);
		Long id = todo.getId();
		int before = todos.size();
			
		todoDao.delete(id);
		
		int after = todoDao.findAll().size();
		logger.info("before:{}, after:{}", before, after);
		assertEquals(1, before - after);
	}
	
	@Test
	public void updateTest() {
		List<Todo> todos = todoDao.findAll();
		Todo before = todos.get(0);
		Long id = before.getId();
		
		Todo todo = new Todo();
		todo.setId(id);
		todo.setTodo("updateupdate");
		todo.setCompleted(Boolean.FALSE);		
		todoDao.update(todo);
		
		Todo after = todoDao.getTodoById(id);
		logger.info("after:{}", after.getTodo());
		assertEquals(id, after.getId());
		assertEquals("updateupdate", after.getTodo());
	}
	
	@Test
	public void findAllTest() {
		List<Todo> todos = todoDao.findAll();
		for(Todo todo: todos) {
			logger.info("{}", todo);
		}
	}
	
}
