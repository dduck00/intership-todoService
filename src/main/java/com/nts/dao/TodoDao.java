package com.nts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nts.dto.TodoDto;

public class TodoDao {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/PTJ2?serverTimezone=UTC";
	private static final String DB_USER = "root";
	private static final String DB_PASSWD = "0928";

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

	public int addTodo(TodoDto addTodo) {
		int result = 0;
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			PreparedStatement ps = conn.prepareStatement(INSERT_TODO)) {
			ps.setString(1, addTodo.getTitle());
			ps.setString(2, addTodo.getName());
			ps.setInt(3, addTodo.getSequence());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<TodoDto> getTodos() {
		List<TodoDto> data_from_database = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			PreparedStatement ps = conn.prepareStatement(SELECT_TODOS)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					TodoDto data = new TodoDto();
					data.setId(rs.getLong("id"));
					data.setName(rs.getString("name"));
					data.setRegdate(rs.getString("regdate"));
					data.setSequence(rs.getInt("sequence"));
					data.setTitle(rs.getString("title"));
					data.setType(rs.getString("type"));
					data_from_database.add(data);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return data_from_database;
	}

	public int updateTodo(TodoDto updateTodo) {
		int result = 0;
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			PreparedStatement ps = conn.prepareStatement(UPDATE_TODO)) {
			ps.setString(1, updateTodo.getType());
			ps.setLong(2, updateTodo.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
