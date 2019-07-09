package com.nts.page;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;

@WebServlet("/addTodo")
public class AddTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final TodoDao DB_CONNECTOR = new TodoDao();

	@Override
	//TODO: AJAX 테스트 필요
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsps/registerTodo.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		TodoDto todo = new TodoDto();
		todo.setName(request.getParameter("name"));
		todo.setSequence(Integer.parseInt(request.getParameter("sequence")));
		todo.setTitle(request.getParameter("title"));
		try {
			DB_CONNECTOR.addTodo(todo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		response.sendRedirect("/main");
	}

}
