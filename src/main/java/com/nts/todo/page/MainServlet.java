package com.nts.todo.page;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todo.dao.TodoDao;
import com.nts.todo.dto.TodoDto;

/**
 * 사용자에게 메인 페이지를 제공하는 서블릿
 * @author 이상덕
 * @version 1.0
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final TodoDao DB_CONNECTOR = new TodoDao();

	/**
	 * 데이터베이스로부터 정보를 읽어 jsp로 데이터를 전달한다.
	 * @author 이상덕
	 * @exception ServeltException, IOException, SQLException, NullPointerException, IllegalArgumentException
	 * @param HttpServeltRequest, HttpServletResponse
	 * @return void
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		try {

			List<TodoDto> listTodo = null;
			listTodo = DB_CONNECTOR.getTodos();

			List<TodoDto> todos = new ArrayList<>();
			List<TodoDto> doings = new ArrayList<>();
			List<TodoDto> dones = new ArrayList<>();

			for (TodoDto todo : listTodo) {
				switch (todo.getType()) {
					case "TODO":
						todos.add(todo);
						break;
					case "DOING":
						doings.add(todo);
						break;
					case "DONE":
						dones.add(todo);
						break;
				}
			}

			request.setAttribute("Todos", todos);
			request.setAttribute("Doings", doings);
			request.setAttribute("Dones", dones);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsps/index.jsp");
			requestDispatcher.forward(request, response);

		} catch (SQLException e) {
			response.getOutputStream().println("<script>alert('DB load Fail');</script>");
		}

	}
}
