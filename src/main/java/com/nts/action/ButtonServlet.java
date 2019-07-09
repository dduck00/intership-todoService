package com.nts.action;

import java.io.IOException;
import java.io.PrintWriter;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		TodoDao todoAccess = new TodoDao();
		TodoDto todo = new TodoDto();

		todo.setType(request.getParameter("type"));
		todo.setId(Long.parseLong(request.getParameter("id")));

		if (todo.getType().equals("TODO")) {
			todo.setType("DOING");
		} else if (todo.getType().equals("DOING")) {
			todo.setType("DONE");
		}
		int ret = 0;
		try {
			ret = todoAccess.updateTodo(todo);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

		PrintWriter out = response.getWriter();
		out.println(ret);

	}

}
