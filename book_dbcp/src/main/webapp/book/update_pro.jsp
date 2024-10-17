<%@page import="dto.BookDTO"%>
<%@page import="dao.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	// 1. 가져올 값(== 넘어오는 값)이 있는지 확인
	// 시작하는 페이지에서 form 이 존재
	// a 태그 ? 다음에 넘어오는지
			
	// pk(), 수정할 컬럼(price, description) 가져오기
		BookDTO updateDto = new BookDTO();
		updateDto.setCode(Integer.parseInt(request.getParameter("code")));
		updateDto.setDescription(request.getParameter("description"));
		updateDto.setPrice(Integer.parseInt(request.getParameter("price")));
		
	// 2. DB 작업
	BookDAO dao = new BookDAO();
	int updateRow = dao.update(updateDto); 
	
	// 3. 결과값 공유(수정, 삭제, 삭입 시 결과값 공유할 필요 X)
	
	//4. 페이지 이동(클라이언트가 볼 페이지)
	// 0 이 리턴되면 modify_pro.jsp 로 이동
	// 1 이 리턴되면 list_pro.jsp 로 이동
	if(updateRow == 0){
		response.sendRedirect("modify_pro.jsp?code="+updateDto.getCode());
	}else{
		response.sendRedirect("list_pro.jsp");
	}
%>