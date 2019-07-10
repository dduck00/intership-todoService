package com.nts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nts.dto.TodoDto;

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
		} catch (ClassNotFoundException e1) {
			throw new RuntimeException();
		}

	}

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

	public List<TodoDto> getTodos() throws SQLException {
		List<TodoDto> listTodo = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			PreparedStatement ps = conn.prepareStatement(SELECT_TODOS)) {

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					TodoDto todo = new TodoDto();
					todo.setId(rs.getLong("id"));
					todo.setName(rs.getString("name"));
					todo.setRegdate(rs.getTimestamp("regdate"));
					todo.setSequence(rs.getInt("sequence"));
					todo.setTitle(rs.getString("title"));
					todo.setType(rs.getString("type"));
					listTodo.add(todo);
				}

			}

		}

		return listTodo;
	}

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
