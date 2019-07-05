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
		+ "order by regdate desc "
		+ "select id, title, name, sequence, type, regdate "
		+ "from todo "
		+ "where type = ? "
		+ "order by regdate desc";

	private static String UPDATE_SQL_QU_TO_DOING = "update todo "
		+ "set type = 'DOING' "
		+ "where id = 1; ";
	private static String UPDATE_SQL_QU_TO_DONE = "update todo "
		+ "set type = 'DONE' "
		+ "where id = 1;";

	public TodoDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("CLASS Load FAIL");
		}
	}

	public int addTodo(TodoDto input_data) {
		return 1;
	}

	public List<TodoDto> getTodos() {
		List<TodoDto> data_from_database = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(SELECT_SQL_QU)) {

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					String description = rs.getString("DOING");
					int id = rs.getInt("role_id");
					//					TodoDto data = new TodoDto(id, description);
					//					list.add(role); // list에 반복할때마다 Role인스턴스를 생성하여 list에 추가한다.
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public int updateTodo(TodoDto update_data) {
		return 1;
	}
}
