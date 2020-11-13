<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%
	session.setAttribute("id","admin");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NUTUBE KIDS</title>
<link rel="stylesheet" href="<c:url value="/resources/css/index_page.css?after" />">
<link href="<c:url value="/resources/css/index.css?after" />" type="text/css" rel="stylesheet" />
</head>
<body>
	<!-- 상단 네비게이션 -->
	<header>
		<nav class="nav_fix">
			<div id="main_icon">
				<h3>
					<a href="."><img id="logo" src="<c:url value="/resources/img/logo3.png" />" width=150 height=30.61></a>
				</h3>
			</div>
			<div id="nav_category">
				<ul>
					<li><a href="">Sports</a></li>
					<li><a href="">Games</a></li>
					<li><a href="">Cooking</a></li>
					<li><a href="">Kids</a></li>
					<li><a href="">Supports</a></li>
					<li><a href="">Login</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- 좌측 사이드바 -->
	<aside>
		<div><a href="../kids/home">홈</a></div>
		<div><a href="../kids/popular">인기</a></div>
		<div><a href="../kids/subscribelist">구독</a></div>
		<div><a href="../kids/likelist">좋아요</a></div>
		<div><a href="../kids/history">시청기록</a></div>
		<div><a href="">결제</a></div>
	</aside>
	<!-- 본문 -->
	<div class="main_page">
		<h1>홈</h1>
	</div>
	<div class="main_frame">
		<div id="item-list">
	
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="<c:url value="/resources/js/listloader.js" />"></script>
<script>
	url = 'list'
	user_id = '${sessionScope.id}'
</script>
</html>

