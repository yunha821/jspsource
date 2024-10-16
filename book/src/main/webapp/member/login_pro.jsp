<%@page import="dao.MemberDAO"%>
<%@page import="dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. 가져올 값(== 넘어오는 값)이 있는지 확인
	// 	  시작하는 페이지에서 form 이 존재
	//    a 태그 ? 다음에 넘어오는지
			
	MemberDTO loginDto = new MemberDTO();
	
	loginDto.setUserid(request.getParameter("userid"));
	loginDto.setPassword(request.getParameter("password"));
	loginDto.setName(request.getParameter("name"));		
		
	// 2. DB 작업
	MemberDAO dao = new MemberDAO();
	MemberDTO dto = dao.isLogin(loginDto);
	
	// 3. 결과값 공유 (select 는 공유 필요)
	//session.setAttribute
	// 4. 페이지 이동
	// == null : login.jsp
	// != null : 도서 목록	
	if(dto != null){
		// 서버 쪽에서 정보 보관
		session.setAttribute("loginDto", dto);
		response.sendRedirect("/book/list_pro.jsp");
	}else{
		response.sendRedirect("login.jsp");
		
	}
	
%>