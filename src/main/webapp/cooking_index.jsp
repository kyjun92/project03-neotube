<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Neotube</title>
<link rel="stylesheet" href="resources/css/index_page.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		<c:set var="id" value="kyjun92" scope="session"/>
		$
				.ajax({
					url : "cooking/select_main.cooking",
					success : function(json) {
						console.log(json);
						for (var i = 0; i < json.length; i++) {
							date = json[i].video_date
							array = date.split(" ")
							$(".main_frame")
									.append(
											"<div id='"+json[i].video_id+"' class='video_frame'><div class='thumbnail_frame'><img width='100%' src='"
													+ json[i].thumbnail
													+ "'></div><div class='content_frame'><h2>"
													+ json[i].video_title
													+ "</h2><p>조회수 "
													+ json[i].play_num
													+ "회 ㆍ "
													+ array[0]
													+ " </p></div></div>");
						} //for
						$(".video_frame")
								.click(
										function() {
											var videoId = $(this).attr('id');
											console.log(videoId);
											location.href = "cooking/playingPage.cooking?videoId="
													+ videoId;
										}) //click
					} //success
				}) //ajax

	}) //start
</script>
<!-- 메인페이지 css -->
</head>
<body>
	<!-- 상단 네비게이션 -->
	<header>
		<nav class="nav_fix">
			<div id="main_icon">
				<h3>
					<a href="index.jsp"><img id="logo"
						src="resources/img/logo3.png" width=150 height=30.61></a>
				</h3>
			</div>
			<div id="nav_category">
				<ul>
					<li><a href="sports_index.jsp">Sports</a></li>
					<li><a href="game_index.jsp">Games</a></li>
					<li><a href="cooking_index.jsp">Cooking</a></li>
					<li><a href="kids_index.jsp">Kids</a></li>
					<li><a href="client/client.do">Supports</a></li>
					<li><a href="login/logn.do">Login</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- 좌측 사이드바 -->
	<aside>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="#" style="font-size: 30px;">홈</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="" style="font-size: 30px;">인기</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="" style="font-size: 30px;">구독</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="" style="font-size: 30px;">좋아요한 동영상</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="" style="font-size: 30px;">시청기록</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="" style="font-size: 30px;">결제</a>
		</div>
	</aside>
	<!-- 본문 -->
	<div class="main_page"></div>
	<div class="main_frame"
		style="height: auto; overflow: hidden; width: 1950px; margin-left: 195px;">
	</div>

	<script type="text/javascript">
		
	</script>
</body>
</html>