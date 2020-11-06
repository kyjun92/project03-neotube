<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

console.log("<%=session.getAttribute("id")%>")
function clickLike(id) {
	
	console.log(id);
	
}

</script>
<link rel="stylesheet" href="resources/css/index_page.css">
</head>
<body>
	<!-- 상단 네비게이션 -->
	<header>
		<nav class="nav_fix">
			<div id="main_icon">
				<h3>
					<a href="index.html"><img id="logo"
						src="resources/img/logo3.png" width=150 height=30.61></a>
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
			<a href="index.jsp" style="font-size: 30px;">홈</a>
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
	<div class="main_frame">
		<div class="watch_frame">
			<p class="iframe_p" style="margin:0">
				<iframe width="1059" height="596"
					src="https://www.youtube.com/embed/${videoVO.video_id}?autoplay=1" frameborder="0"
					allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
					allowfullscreen></iframe>
			</p>
		</div>
		<div class="iframe_frame">
			<a style="color:blue;" href="#">${videoVO.tag }</a><br>
			<h2>${videoVO.video_title }</h2>
			<p>조회수 ${videoVO.play_num } 회 · ${videoVO.video_date }</p>
			<br> ${videoVO.dislike_num }<input onclick="clickLike("<%=session.getAttribute("id")%>")" type='button' class='btn' name='btn' value='싫어요'>
			${videoVO.like_num }<input onclick="clickLike("<%=session.getAttribute("id")%>")" type='button' class='btn' name='btn' value='좋아요'><br>
			<br> <br> <br>
			<hr>
			<div style="display: grid; grid-template-columns: [left-edge] 50px[artwork-edge] 790px[title-edge] 140px[right-edge]; grid-column-gap: 16px;">
			<div><img style="border-radius: 50px;" width="100%" src="${channelVO.channel_img }"></div>
			<div style="padding-top: 15px;"> ${channelVO.channel_title }</div>
			<div style="cursor: pointer; background: #cc0000; width: 100%; color:white; text-align: center; font-weight: bolder; padding-top:17px;">구독</div>
			</div>
			<hr>
			댓글 div
		</div>

	</div>

</body>
</html>