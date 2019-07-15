package com.nts.todo.page;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 에러를 기록하고 에러페이지로 이동하는 서블릿
 * @author 이상덕
 * @version 1.0
 */
@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 에러메시지 출력 후 리다이렉트
	 * @author 이상덕
	 * @exception ServeltException, IOException
	 * @param HttpServeltRequest, HttpServletResponse
	 * @return void
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		int httpStatusCode = Integer.parseInt(request.getAttribute("javax.servlet.error.status_code").toString());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsps/error.jsp");

		System.out.println(request.getAttribute("javax.servlet.error.message"));

		if (httpStatusCode >= 400) {
			request.setAttribute("errorStatus", httpStatusCode);
		}

		requestDispatcher.forward(request, response);
	}

}
