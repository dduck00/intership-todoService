package com.nts.page;

import java.io.IOException;
import java.sql.SQLException;
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
		List<TodoDto> listTodos = null;
		try {
			listTodos = DB_CONNECTOR.getTodos();
		} catch (SQLException e) {
			response.sendRedirect("/error.jsp");
		}

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		request.setAttribute("listTodos", listTodos);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doGet(request, response);
	}

}
