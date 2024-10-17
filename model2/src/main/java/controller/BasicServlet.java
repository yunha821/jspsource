package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BasicServlet
 */
@WebServlet("*.do")
public class BasicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글처리
		request.setCharacterEncoding("utf-8");
		
		// 톰캣서버의 path 수정하지 않았다면
		// RequestURI => /프로젝트명/경로명 => /model2/login.do
		// ContextPath => /프로젝트명 		=> /model2
		// "/model2/login.do".substring(7) 경로명만 추출 => /login.do
		
		String requestUri = request.getRequestURI(); // /login.do
		String contextPath = request.getContextPath();
		String cmd = requestUri.substring(contextPath.length()); // /login.do
		
		// syso
//		System.out.println("requestUri : " + requestUri);
//		System.out.println("contextPath : " + contextPath);
		System.out.println("cmd : " + cmd);
		
		if(cmd.equals("/login.do")) {
			System.out.println("로그인 작업");
		}else if(cmd.equals("/register.do")){
			System.out.println("회원가입 작업");
		}
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
