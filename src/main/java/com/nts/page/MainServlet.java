package com.nts.page;

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

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final TodoDao DB_CONNECTOR = new TodoDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		try {

			List<TodoDto> listTodo = null;
			listTodo = DB_CONNECTOR.getTodos();

			List<TodoDto> Todos = new ArrayList<>();
			List<TodoDto> Doings = new ArrayList<>();
			List<TodoDto> Dones = new ArrayList<>();

			for (TodoDto todo : listTodo) {
				if (todo.getType().equals("TODO")) {
					Todos.add(todo);
				} else if (todo.getType().equals("DOING")) {
					Doings.add(todo);
				} else if (todo.getType().equals("DONE")) {
					Dones.add(todo);
				}
			}

			request.setAttribute("Todos", Todos);
			request.setAttribute("Doings", Doings);
			request.setAttribute("Dones", Dones);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsps/index.jsp");
			requestDispatcher.forward(request, response);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (NumberFormatException e1) {
			throw new RuntimeException(e1);
		} catch (NullPointerException e2) {
			throw new RuntimeException(e2);
		}

	}
}
