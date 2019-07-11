package com.nts.todo.page;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todo.dao.TodoDao;
import com.nts.todo.dto.TodoDto;

/**
 * 사용자로부터 post요청을 받아 데이터베이스에 기록하는 서블릿
 * @author 이상덕
 * @version 1.0
 */
@WebServlet("/add-todo")
public class AddTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final TodoDao DB_CONNECTOR = new TodoDao();

	/**
	 * 사용자가 새로운 TODO를 추가하고자 할 경우 해당 페이지로 연결해주는 기능 수행
	 * @author 이상덕
	 * @exception ServeltException, IOException
	 * @param HttpServeltRequest, HttpServletResponse
	 * @return void
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsps/registerTodo.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * 데이터베이스에 추가하고 메인페이지로 이동하는 메소드
	 * @author 이상덕
	 * @exception ServeltException, IOException, NumberFormatException, NullPointerException
	 * @param HttpServeltRequest, HttpServletResponse
	 * @return void
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		try {

			TodoDto todo = new TodoDto();
			todo.setName(request.getParameter("name"));
			todo.setSequence(Integer.parseInt(request.getParameter("sequence")));
			todo.setTitle(request.getParameter("title"));

			DB_CONNECTOR.addTodo(todo);

			response.sendRedirect("/main");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (NumberFormatException e1) {
			throw new RuntimeException(e1);
		} catch (NullPointerException e2) {
			throw new RuntimeException(e2);
		}

	}

}
