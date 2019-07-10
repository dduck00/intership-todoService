package com.nts.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;

@WebServlet("/action")
public class ButtonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		try {
			TodoDao todoAccess = new TodoDao();
			TodoDto todo = new TodoDto();

			todo.setType(request.getParameter("type"));
			todo.setId(Long.parseLong(request.getParameter("id")));

			if (todo.getType().equals("TODO")) {
				todo.setType("DOING");
			} else if (todo.getType().equals("DOING")) {
				todo.setType("DONE");
			}

			todoAccess.updateTodo(todo);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (NumberFormatException e1) {
			throw new RuntimeException(e1);
		} catch (NullPointerException e2) {
			throw new RuntimeException(e2);
		}

	}

}
