<%@page import="dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<title>Book - MVC1</title>
</head>
<body>
<nav class="py-2 bg-body-tertiary border-bottom">
    <div class="container d-flex flex-wrap">
      <ul class="nav me-auto">
        <li class="nav-item"><a href="/" class="nav-link link-body-emphasis px-2 active" aria-current="page">Home</a></li>
        <li class="nav-item"><a href="/book/list_pro.jsp" class="nav-link link-body-emphasis px-2">도서목록</a></li>
        <li class="nav-item"><a href="/book/create.jsp" class="nav-link link-body-emphasis px-2">도서입력</a></li>
        <li class="nav-item"><a href="#" class="nav-link link-body-emphasis px-2">FAQs</a></li>
        <li class="nav-item"><a href="#" class="nav-link link-body-emphasis px-2">About</a></li>
      </ul>
      
      <% 
      	// 세션에 담긴 정보는 어느 페이지(jsp,servlet)던 사용 가능함
      	MemberDTO loginDto = (MemberDTO)session.getAttribute("loginDto");
      	if(loginDto == null){
      %>
      <!-- 로그인 전 보여줄 메뉴 -->
      <ul class="nav">
        <li class="nav-item"><a href="/member/login.jsp" class="nav-link link-body-emphasis px-2">Login</a></li>
        <li class="nav-item"><a href="/member/register.jsp" class="nav-link link-body-emphasis px-2">Sign up</a></li>
      </ul>
      <% }else{ %>
      <!-- 로그인 후 보여줄 메뉴 -->
      <ul class="nav">
        <li class="nav-item"><a href="/member/logout_pro.jsp" class="nav-link link-body-emphasis px-2">Logout</a></li>
        <li class="nav-item"><a href="/member/info.jsp" class="nav-link link-body-emphasis px-2">Info</a></li>
      </ul>
      <% } %>
    </div>
  </nav>
  <header class="py-3 mb-4 border-bottom">
    <div class="container d-flex flex-wrap justify-content-center">
      <a href="/" class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto link-body-emphasis text-decoration-none">
        <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
        <span class="fs-4">Book</span>
      </a>
      <form class="col-12 col-lg-auto mb-3 mb-lg-0" role="search">
        <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
      </form>
    </div>
  </header>
<div class="container">
	<!-- main content -->
