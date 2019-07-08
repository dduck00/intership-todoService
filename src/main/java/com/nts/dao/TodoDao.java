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

	private static final String SELECT_TODOS = "select id, title, name, sequence, type, regdate "
		+ "from todo "
		+ "order by regdate desc ";

	private static final String UPDATE_TODO = "update todo "
		+ "set type = ? "
		+ "where id = ?; ";

	private static final String INSERT_TODO = "insert into todo(title, name, sequence) "
		+ "values(?, ?, ?);";

	public TodoDao() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("CLASS Load FAIL");
		}

	}

	public int addTodo(TodoDto addTodo) throws SQLException {
		int result = 0;

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			PreparedStatement ps = conn.prepareStatement(INSERT_TODO)) {
			ps.setString(1, addTodo.getTitle());
			ps.setString(2, addTodo.getName());
			ps.setInt(3, addTodo.getSequence());

			result = ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException();
		}

		return result;
	}

	public List<TodoDto> getTodos() throws SQLException {
		List<TodoDto> listTodos = new ArrayList<>();

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

					listTodos.add(todo);
				}

			} catch (SQLException e) {
				throw new SQLException();
			}

		} catch (SQLException ex) {
			throw new SQLException();
		}

		return listTodos;
	}

	public int updateTodo(TodoDto updateTodo) throws SQLException {
		int result = 0;

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			PreparedStatement ps = conn.prepareStatement(UPDATE_TODO)) {
			ps.setString(1, updateTodo.getType());
			ps.setLong(2, updateTodo.getId());

			result = ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException();
		}

		return result;
	}
}
