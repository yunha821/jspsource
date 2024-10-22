<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 클라이언트가 요청한 파일명 가져오기
	String fileName = request.getParameter("fileName"); 

	// 서버의 업로드 폴더에 가서 클라이언트가 요청한 파일 읽은 후 보내기
	// c:\\upload\\2e5cbd7d-e187-492a-b5c5-dd2f285fc2dd_PW 정보.txt
	String saveDir = "c:\\upload";
	String downPath = saveDir + File.separator + fileName;
	
	// 파일 읽기
	FileInputStream fis = new FileInputStream(downPath);
	
	// 읽은 파일(이미지, 텍스트, 동영상...) 브라우저 헤더에 붙여서 보내기
	// application/octet-stream 
	response.setContentType("application/octet-stream");
	// %2b
	fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
	
	// c:\\upload\\2e5cbd7d-e187-492a-b5c5-dd2f285fc2dd_PW 정보.txt
	int start = fileName.lastIndexOf("_");
	String oriName = fileName.substring(start + 1);
	
	//2e5cbd7d-e187-492a-b5c5-dd2f285fc2dd_PW 정보.txt or PW_정보.txt
	response.setHeader("content-disposition", "attachment; filename=" + oriName);
			
	out.clear();
	out = pageContext.pushBody();
	
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	
	int numRead = 0;
	byte b[] = new byte[4096];
	while((numRead = fis.read(b)) != -1){
		bos.write(b);
	}
	
	bos.flush();
	bos.close();
	fis.close();
%>