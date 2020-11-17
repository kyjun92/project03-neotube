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

var userID = '<%=session.getAttribute("user_id")%>' //잡혀있는 세션의 id를 가져와 변수에 담는다.
	$(function() {
		var like_num = $('#likenumID').val() // 페이지 시작되자마자 id의 값을 가져옴
		var dislike_num = $('#dislikenumID').val()
		console.log(like_num)// 가져온 값을 찍음.
		console.log(dislike_num)
		video_id = $('#videoID').val()
		$.ajax({ //페이지가 시작되자마자 실행
			url : "user_record.do", //user_record(시청기록)db에 적재 되는 ajax
			data : { // 변수를 데이터로 넘겨주는 과정
				user_id : userID,
				video_id : video_id
			},
			success : function(result) { // 성공
			}
		})

		$.ajax({ //페이지가 시작되자마자 실행
			url : "makeLike.do", //  페이지가 실행될때 db에있는 like_index의 값을 가져와 처리해줌
			data : {
				user_id : userID,
				video_id : video_id
			},
			success : function(result) {
				console.log(result)
				like = parseInt(result);
				$('#likeButton').ready(function() { //likebutton
					if (like == 1) { //like_index의 값이 1일때 
						$('#likeButton').css('background', '#aaaaaa') //css변경
					}
				}) //ready 
				$('#dislikeButton').ready(function() { //dislikebutton
					if (like == 2) { //like_index의 값이 2일대
						$('#dislikeButton').css('background', '#aaaaaa')//css변경
					}
				}) //ready

			}
		})

		$.ajax({ // 조회수 변경 ajax
			url : "viewnum_update.do",
			data : {
				video_id : video_id
			},
			success : function() {
			}
		})

		$.ajax({ // 구독의 여부확인 ajax >> 페이지 시작시 확인하기 때문에 데이터베이스에 담겨있는 정보를 토대로 css을 변경하여 구별이 가능.
			url : "subscribeBoolean.do",
			data : {
				user_id : userID, // 세션 id값
				channel_id : $('#channelID').val() //채널 id값
			},
			success : function(result) {// 성공시 sub(result)값을 반환 
				var sub = parseInt(result);
				//sub값 변경시
				$('#subscribe').ready(function() { 
					if (sub == 0) {//sub값이 0일때 
						$('#subscribe').text('구독취소') //text 변경 (구독취소))
						$('#subscribe').css('background', '#aaaaaa')//css변경
					} else {
						$('#subscribe').text('구독')//text 변경 (구독))
						$('#subscribe').css('background', '#cc0000')//css변경
					}
				}) //ready 
			}
		})


		
		//구독하기
		$('#subscribe').click(function() { // 구독버튼 click 이벤트
			channel_id = $('#channelID').val() // 클릭했을시 channel_id값을 가져옴
			//userid,channelid
			$.ajax({
				url : "subscribeDispose.do", 
				data : {
					user_id : userID, //세션 id값
					channel_id : channel_id
				},
				success : function(result) {//클릭시 sub값 변경과 동시에 sub값 가져옴
					sub = parseInt(result);
					$('#subscribe').ready(function() {
						if (sub == 0) { // 동일하게 css와 text 변경
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

		$('#likeButton').click(function() { // 좋아요 버튼 클릭 시 변경 함수
			$.ajax({
				url : "likeButton.do",
				data : {
					user_id : userID,
					video_id : video_id
				},
				error : function(){
			        alert('로그인후 이용해주세요'); // 실패 시 처리
			       },
				success : function(result) {// 성공시 페이지가 새로고침 되며 css(background-color)변경
					location.reload();
				}
			})
		})

		$('#dislikeButton').click(function() { //싫어요 버튼 클릭 시 변경 함수
			$.ajax({
				url : "dislikeButton.do",
				data : {
					user_id : userID,
					video_id : video_id
				},
				success : function(result) {// 성공시 페이지가 새로고침 되며 css(background-color)변경
					location.reload();
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
				<li><a href="../sports_index.jsp">Sports</a></li>
					<li><a href="../game_index.jsp">Games</a></li>
					<li><a href="../cooking_index.jsp">Cooking</a></li>
					<li><a href="../kids_index.jsp">Kids</a></li>
					<li><a href="../client/client.do">Supports</a></li>
					<li><a href="../login/logn.do">Login</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- 좌측 사이드바 -->
	<aside>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="../sports_index.jsp" style="font-size: 30px;">홈</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="popular.do" style="font-size: 30px;">인기</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="subscribe.do?user_id=<%=session.getAttribute("user_id")%>"
				style="font-size: 30px;">구독</a>
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
	<c:forEach items="${list}" var="vo" varStatus="status"> <!-- 추천 알고리즘의 결과를 foreach로 for문 돌림 -->
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
				<input id="likenumID" type="hidden" value="${vo.like_num}">
				<input id="dislikenumID" type="hidden" value="${vo.dislike_num}">
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