package student.management.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import student.management.model.Login;
import student.management.model.User;

public class StudentUserDaoImpl implements StudentUserDao {

	  @Autowired
	  DataSource datasource;

	  @Autowired
	  JdbcTemplate jdbcTemplate;

	  public int reg(User user) {
	    String sql = "insert into registration values(?,?,?)";

	    return jdbcTemplate.update(sql, new Object[] { user.getUsername(), user.getPassword(), user.getFirstname()
	         });
	  }

	  public User implUser(Login login) {
	    String sql = "select * from registration where username='" + login.getUsername() + "' and password='" + login.getPassword()
	        + "'";
	    List<User> users = jdbcTemplate.query(sql, new UserMapper());

	    return users.size() > 0 ? users.get(0) : null;
	  }

	}

	class UserMapper implements RowMapper<User> {

	  public User mapRow(ResultSet rs, int arg1) throws SQLException {
	    User user = new User();

	    user.setUsername(rs.getString("username"));
	    user.setPassword(rs.getString("password"));
	    user.setFirstname(rs.getString("firstname"));
	    
	   
	    return user;
	  }
	}