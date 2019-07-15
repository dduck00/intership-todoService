package com.nts.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nts.todo.dto.TodoDto;

/**
 * 데이터베이스에 접근하는 클래스
 * @author 이상덕
 * @version 1.0
 */
public class TodoDao {
	private static final String DB_URL = "jdbc:mysql://10.113.116.52:13306/user9";
	private static final String DB_USER = "user9";
	private static final String DB_PASSWD = "user9";

	private static final String SELECT_TODOS = "SELECT id, title, name, sequence, type, regdate "
		+ "FROM todo "
		+ "ORDER BY regdate DESC ";

	private static final String UPDATE_TODO = "UPDATE todo "
		+ "SET type = ? "
		+ "WHERE id = ?; ";

	private static final String INSERT_TODO = "INSERT INTO todo(title, name, sequence) "
		+ "VALUES(?, ?, ?);";

	public TodoDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 데이터베이스에 데이터를 저장한다.
	 * @author 이상덕
	 * @exception SQLException
	 * @param TodoDto
	 * @return int
	 */
	public int addTodo(TodoDto todo) throws SQLException {
		int result = 0;

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			PreparedStatement ps = conn.prepareStatement(INSERT_TODO)) {

			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());

			result = ps.executeUpdate();
		}

		return result;
	}

	/**
	 * 데이터베이스에서 Todo 리스트를 가져온다.
	 * @author 이상덕
	 * @exception SQLException, NullPointerException
	 * @return List<TodoDto>
	 */
	public List<TodoDto> getTodos() throws SQLException {
		List<TodoDto> listTodo = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			PreparedStatement ps = conn.prepareStatement(SELECT_TODOS)) {

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					listTodo.add(makeTodo(rs));
				}

			}

		}

		return listTodo;
	}

	/**
	 * 데이터베이스 SELECT 쿼리 결과를 바탕으로 TodoDto 객체 생성
	 * @author 이상덕
	 * @exception SQLException, NullPointerException
	 * @param ResultSet
	 * @return TodoDto
	 */
	public TodoDto makeTodo(ResultSet rs) throws SQLException {
		TodoDto todo = new TodoDto();

		todo.setId(rs.getLong("id"));
		todo.setName(rs.getString("name"));
		todo.setSequence(rs.getInt("sequence"));
		todo.setTitle(rs.getString("title"));
		todo.setType(rs.getString("type"));

		if (rs.getTimestamp("regdate") != null) {
			todo.setRegdate(rs.getTimestamp("regdate"));
		}

		return todo;
	}

	/**
	 * TodoDto를 바탕으로 데이터베이스를 갱신한다.
	 * @author 이상덕
	 * @exception SQLException
	 * @param TodoDto
	 * @return int
	 */
	public int updateTodo(TodoDto todo) throws SQLException {
		int result = 0;

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			PreparedStatement ps = conn.prepareStatement(UPDATE_TODO)) {
			ps.setString(1, todo.getType());
			ps.setLong(2, todo.getId());

			result = ps.executeUpdate();
		}

		return result;
	}
}
