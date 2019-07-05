package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.TodoDto;

public class TodoDao {
	private static String dburl = "jdbc:mysql://localhost:3306/PTJ2?serverTimezone=UTC";
	private static String dbuser = "root";
	private static String dbpasswd = "0928";

	private static String SELECT_SQL_QU = "select id, title, name, sequence, type, regdate "
		+ "from todo "
		+ "order by regdate desc ";

	private static String UPDATE_SQL_QU = "update todo "
		+ "set type = ? "
		+ "where id = ?; ";

	private static String INSERT_SQL_QU = "insert into todo(title, name, sequence) "
		+ "values(?, ?, ?);";

	public TodoDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("CLASS Load FAIL");
		}
	}

	public int addTodo(TodoDto input_data) {
		int result = 0;
		try (Connection conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(INSERT_SQL_QU)) {
			ps.setString(1, input_data.getTitle());
			ps.setString(2, input_data.getName());
			ps.setInt(3, input_data.getSequence());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<TodoDto> getTodos() {
		List<TodoDto> data_from_database = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(SELECT_SQL_QU)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					TodoDto data = new TodoDto(
						rs.getLong("id"),
						rs.getString("name"),
						rs.getString("regdate"),
						rs.getInt("sequence"),
						rs.getString("title"),
						rs.getString("type"));
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

	public int updateTodo(TodoDto update_data) {
		int result = 0;
		try (Connection conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(UPDATE_SQL_QU)) {
			ps.setString(1, update_data.getType());
			ps.setLong(2, update_data.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
