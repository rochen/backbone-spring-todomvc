package harbour.spring.dao;

import harbour.spring.domain.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class JdbcTodoDao implements TodoDao, InitializingBean {
	final static Logger logger = LoggerFactory.getLogger(JdbcTodoDao.class);
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Todo> findAll() {
		String sql = "select * from Todo";
		
		return jdbcTemplate.query(sql, new TodoMapper());
	}

	@Override
	public int insert(Todo todo) {
		String sql = "insert into Todo (title, completed) values (:title, :completed)";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("title",  todo.getTitle());
		paramMap.put("completed", todo.getCompleted());
		
		return jdbcTemplate.update(sql, paramMap);
	}
	
	@Override
	public Number insertAndReturnKey(Todo todo) {
		String sql = "insert into Todo (title, completed) values (:title, :completed)";
		
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(todo);
		
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		int count = jdbcTemplate.update(sql, paramSource, generatedKeyHolder);
		
		return count == 1? generatedKeyHolder.getKey(): -1;
	}

	@Override
	public int update(Todo todo) {
		String sql = "update Todo set title = :title, completed = :completed where id = :id";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("title",  todo.getTitle());
		paramMap.put("completed", todo.getCompleted());
		paramMap.put("id", todo.getId());
		
		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public int delete(Long id) {
		String sql = "delete from Todo where id = :id";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		
		return jdbcTemplate.update(sql, paramMap);		
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
		if (jdbcTemplate == null) {
			throw new BeanCreationException("must set jdbcTemplate in TodoDao");
		}
	}
	
	private static final class TodoMapper implements RowMapper<Todo> {

		@Override
		public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Todo todo = new Todo();
			todo.setId(rs.getLong("id"));
			todo.setTitle(rs.getString("title"));
			todo.setCompleted(rs.getBoolean("completed"));
			
			return todo;
		}
		
	}
}


