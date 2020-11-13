<%@page import="com.mega.mvc01.sport.VideoVO"%>
<%@page import="com.mega.mvc01.sport.VideoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Neotube</title>
<link rel="stylesheet" href="../resources/css/watch.css">
<script src="../resources/js/jquery-3.5.1.js"></script>
<script type="text/javascript">
var userID = '<%=session.getAttribute("user_id")%>'
	console.log(userID)
	$(function() {
		video_id = $('#videoID').val()
		console.log(video_id)
		$.ajax({
			url : "user_record.do",
			data : {
				user_id : userID,
				video_id : video_id
			},
			success : function(result) {
			}
		})

		$.ajax({
			url : "viewnum_update.do",
			data : {
				video_id : video_id
			},
			success : function() {
				console.log('비디오id 넘기기')
			}
		})

		$.ajax({

			url : "subscribeBoolean.do",
			data : {
				user_id : userID, //세션 아이디 값으로 변경예정
				channel_id : $('#channelID').val()
			},
			success : function(result) {
				var sub = parseInt(result);
				console.log("컨트롤러에서 넘어온 값>>>" + sub)
				//sub값 변경시
				$('#subscribe').ready(function() {
					if (sub == 0) {
						$('#subscribe').text('구독취소')
						$('#subscribe').css('background', '#aaaaaa')
					} else {
						$('#subscribe').text('구독')
						$('#subscribe').css('background', '#cc0000')
					}
				}) //ready 
			}
		})

		sub = 0;
		console.log('초기sub값' + sub)

		//구독하기
		$('#subscribe').click(function() {
			channel_id = $('#channelID').val()
			//userid,channelid
			$.ajax({
				url : "subscribeDispose.do",
				data : {
					user_id : userID, //세션 아이디 값으로 변경예정
					channel_id : channel_id
				},
				success : function(result) {
					sub = parseInt(result);
									//sub값 변경시
				$('#subscribe').ready(function() {
					if (sub == 0) {
						$('#subscribe').text('구독취소')
						$('#subscribe').css('background', '#aaaaaa')
					} else {
						$('#subscribe').text('구독')
						$('#subscribe').css('background', '#cc0000')
					}
				}) //ready 
				}
			})
		})

		$('#like').click(function() {
			video_id = $('#videoID').val()
			console.log(video_id)
			$.ajax({
				url : "like.do",
				data : {
					user_id : userID,
					video_id : video_id
				},
				success : function(result) {
					alert('좋아요 완료')
				}
			})
		})

	})
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
			<a href="../main.jsp" style="font-size: 30px;">홈</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="popular.do" style="font-size: 30px;">인기</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="subscribe.do?user_id=<%=session.getAttribute("user_id")%>" style="font-size: 30px;">구독</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="likepage.do" style="font-size: 30px;">좋아요한 동영상</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="record_view.do" style="font-size: 30px;">시청기록</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="../payment/payment.do" style="font-size: 30px;">결제</a>
		</div>
	</aside>
	<!-- 본문 -->
	<div class="main_page"></div>
	<c:forEach items="${list}" var="vo" varStatus="status">
		<c:set var="view_num" value="${vo.play_num}" />
		<div class="main_frame">
			<div class="watch_frame">
				<iframe width="1061.48" height="598.5"
					src="https://www.youtube.com/embed/${vo.video_id}" frameborder="0"
					allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
					allowfullscreen></iframe>
			</div>
			<div class="iframe_frame">
				${vo.tag}<br>
				<h2>${vo.video_title}</h2>
				<p>
					조회수
					<fmt:formatNumber type="number" maxFractionDigits="3"
						value="${view_num}" var="playNum" />
					${playNum} 회 · ${vo.video_date}
				</p>
				<div style="width: 300px; height: 50px; float: right;">
					<div id="likeButton"
						style="cursor: pointer; width: 145px; height: 50px; float: left;">
						<img src="../resources/img/thumbs-up.png"
							style="fill: #065fd4; width: 30px;"><span id="likenum">&nbsp;&nbsp;${vo.like_num}</span>
					</div>
					<div id="dislikeButton"
						style="cursor: pointer; width: 150px; height: 50px; float: right;">
						<img src="../resources/img/thumb-down.png"
							style="fill: #065fd4; width: 30px;"><span id="likenum">&nbsp;&nbsp;${vo.dislike_num}</span>
					</div>
				</div>

				<input id="videoID" type="hidden" value="${vo.video_id}"> <input
					id="channelID" type="hidden" value="${vo.channel_id}"> <br>
				<br> <br> <br> <br> <br> <br>
				<hr>
				<br>
				<c:forEach items="${list2}" var="vo" varStatus="status">
					<div
						style="display: grid; grid-template-columns: [left-edge] 50px[artwork-edge] 790px[title-edge] 140px[right-edge]; grid-column-gap: 16px;">
						<div>
							<img style="border-radius: 50px;" width="100%"
								src="${vo.channel_img}">
						</div>
						<div style="padding-top: 15px;">${vo.channel_title}</div>
						<div id="subscribe"
							style="cursor: pointer; background: #cc0000; width: 100%; color: white; text-align: center; font-weight: bolder; padding-top: 17px;">구독</div>
					</div>
				</c:forEach>

				<br> <br>
				<hr>
				댓글 div
			</div>
		</div>
	</c:forEach>

</body>
</html>