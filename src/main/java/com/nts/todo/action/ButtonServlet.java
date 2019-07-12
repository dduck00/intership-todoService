package com.nts.todo.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todo.dao.TodoDao;
import com.nts.todo.dto.TodoDto;

/**
 * 사용자로부터 post요청을 받아 데이터베이스를 업데이트 하는 서블릿
 * @author 이상덕
 * @version 1.0
 */
@WebServlet("/action")
public class ButtonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 데이터베이스를 업데이트 하는 메소드
	 * @author 이상덕
	 * @exception ServeltException, IOException, NullPointerException, IllegalArgumentException
	 * @param HttpServeltRequest, HttpServletResponse
	 * @return void
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		try {
			TodoDao todoAccess = new TodoDao();
			TodoDto todo = makeTodoDto(request);

			if (todo.getType() != null) {
				todo.setType(todo.getType().contentEquals("TODO") ? "DOING" : "DONE");
			}

			todoAccess.updateTodo(todo);
		} catch (SQLException | IllegalArgumentException e) {
			response.getOutputStream().print("Update Fail");
		}

	}

	/**
	 * Todo를 생성해주는 클래스
	 * @author 이상덕
	 * @exception IllegalArgumentException, NullPointerException
	 * @param HttpServeltRequest
	 * @return TodoDto
	 */
	private TodoDto makeTodoDto(HttpServletRequest request) throws IllegalArgumentException {
		TodoDto todo = new TodoDto();

		todo.setType(request.getParameter("type"));
		todo.setId(Long.parseLong(request.getParameter("id")));

		return todo;
	}

}
