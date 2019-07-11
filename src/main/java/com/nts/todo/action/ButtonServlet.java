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
	 * @exception ServeltException, IOException, NumberFormatException, NullPointerException, IllegalArgumentException
	 * @param HttpServeltRequest, HttpServletResponse
	 * @return void
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		try {
			TodoDao todoAccess = new TodoDao();
			TodoDto todo = new TodoDto();

			todo.setType(request.getParameter("type"));
			todo.setId(Long.parseLong(request.getParameter("id")));

			switch (todo.getType()) {
				case "TODO":
					todo.setType("DOING");
					break;
				case "DOING":
					todo.setType("DONE");
					break;
				default:
					throw new IllegalArgumentException("잘못된 type이 넘어왔습니다.");
			}

			todoAccess.updateTodo(todo);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (NumberFormatException e1) {
			throw new RuntimeException(e1);
		} catch (NullPointerException e2) {
			throw new RuntimeException(e2);
		} catch (IllegalArgumentException e3) {
			throw new RuntimeException(e3);
		}

	}

}
