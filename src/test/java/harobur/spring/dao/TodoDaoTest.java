package harobur.spring.dao;

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
	public void findAllTest() {
		List<Todo> todos = todoDao.findAll();
		for(Todo todo: todos) {
			logger.info("{}", todo);
		}
	}
}
