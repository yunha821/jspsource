
<%@page import="dto.ChangeDTO"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	// 1.
	ChangeDTO changeDto = new ChangeDTO();
	changeDto.setUserid(request.getParameter("userid"));
	changeDto.setCurrentPassword(request.getParameter("current_password"));
	changeDto.setChangePassword(request.getParameter("change_password"));

	// 2.
	MemberDAO dao = new MemberDAO();
	int updateRow = dao.update(changeDto);
	
	// 4. 0 => 비밀번호 변경 x => info.jsp
	//    1 => 세션해제 + 로그인 페이지로 이동
	if(updateRow == 1){
		session.invalidate();
		response.sendRedirect("login.jsp");
	}else{
		response.sendRedirect("info.jsp");
	}


%>