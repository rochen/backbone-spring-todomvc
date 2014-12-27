package harbour.spring.dao;

import harbour.spring.domain.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class JdbcTodoDao implements TodoDao, InitializingBean {
	final static Logger logger = LoggerFactory.getLogger(JdbcTodoDao.class);
	
	private DataSource dataSource;
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
	}
	
	@Override
	public List<Todo> findAll() {
		String sql = "select * from Todo";
		
		return jdbcTemplate.query(sql, new TodoMapper());
	}

	@Override
	public void insert(Todo todo) {
		String sql = "insert into Todo (todo, completed) values (:todo, :completed)";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("todo",  todo.getTodo());
		paramMap.put("completed", todo.getCompleted());
		
		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public void update(Todo todo) {
		String sql = "update Todo set todo = :todo, completed = :completed where id = :id";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("todo",  todo.getTodo());
		paramMap.put("completed", todo.getCompleted());
		paramMap.put("id", todo.getId());
		
		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public void delete(Long id) {
		String sql = "delete from Todo where id = :id";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		
		jdbcTemplate.update(sql, paramMap);		
	}
	
	@Override
	public Todo getTodoById(Long id) {
		String sql = "select * from Todo where id = :id";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		
		return jdbcTemplate.queryForObject(sql, paramMap, new TodoMapper());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		if (dataSource == null) {
			throw new BeanCreationException("must set dataSource on TodoDao");
		}
	}
	
	private static final class TodoMapper implements RowMapper<Todo> {

		@Override
		public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Todo todo = new Todo();
			todo.setId(rs.getLong("id"));
			todo.setTodo(rs.getString("todo"));
			todo.setCompleted(rs.getBoolean("completed"));
			
			return todo;
		}
		
	}
}


