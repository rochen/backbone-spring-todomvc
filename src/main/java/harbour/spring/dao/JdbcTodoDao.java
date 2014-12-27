package harbour.spring.dao;

import harbour.spring.domain.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
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
		
		return jdbcTemplate.query(sql, new RowMapper<Todo>() {

			@Override
			public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Todo todo = new Todo();
				todo.setId(rs.getLong("id"));
				todo.setTodo(rs.getString("todo"));
				todo.setCompleted(rs.getBoolean("completed"));
				return todo;
			}
			
		} );
	}

	@Override
	public void insert(Todo todo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Todo toto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		if (dataSource == null) {
			throw new BeanCreationException("must set dataSource on TodoDao");
		}
	}

	
}
