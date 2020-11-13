<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Neotube</title>
<link rel="stylesheet" href="../resources/css/index_page.css">
<script src="../resources/js/jquery-3.5.1.js"></script>
<script type="text/javascript">
	
</script>
<!-- 메인페이지 css -->
</head>
<body>
	<!-- 상단 네비게이션 -->
	<header>
		<nav class="nav_fix">
			<div id="main_icon">
				<h3>
					<a href="index.html"><img id="logo"
						src="../resources/img/logo3.png" width=150 height=30.61></a>
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
		<div style="margin-left: -65px; margin-top: -15px;">
			<a id="home" href="../main.jsp" style="font-size: 30px;">홈</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a id="popular" href="popular.do" style="font-size: 30px;">인기</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a id="subscribe" href="subscribe.do?user_id=<%=session.getAttribute("user_id")%>" style="font-size: 30px;">구독</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a id="like_video" href="likepage.do" style="font-size: 30px;">좋아요한 동영상</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a id="recoding_view" href="record_view.do" style="font-size: 30px;">시청기록</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a id="payment " href="../payment/payment.do"
				style="font-size: 30px;">결제</a>
		</div>
	</aside>
	<!-- 본문 -->
	<div class="main_page"></div>
	<div class="main_frame"
		style="height: auto; overflow: hidden; width: 1950px; margin-left: 195px;">
		<c:forEach items="${list}" var="vo" varStatus="status">
			<fmt:parseNumber var="view_num" integerOnly="true"
				value="${vo.play_num/10000}" />
			<div class="video_frame">
				<div class="thumbnail_frame">
					<!-- 452 260  ${vo.thumbnail}-->
					<a id="watch${status.index}"
						href="../sports/watch.do?video_id=${vo.video_id}&channel_id=${vo.channel_id}"><img
						src="${vo.thumbnail}" style="width: 452px; height: 260px;"
						alt="썸네일"></a>
					<!--sports/watch.do  -->
				</div>
				<div class="content_frame${status.index}" style="padding: 5px;">
					<h3 id="video_title${status.index}">${vo.video_title}</h3>
					<p id="video_chanel${status.index}">${vo.channel_title}</p>
					<c:if test="${view_num eq '0'}">
						<p id="video_view1${status.index}">조회수 : ${vo.play_num}회 ·
							${vo.video_date}</p>
					</c:if>
					<c:if test="${view_num ne '0'}">
						<p id="video_view2${status.index}">조회수 : ${view_num}만회 ·
							${vo.video_date}</p>
					</c:if>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>