<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

	var userId = '<%=session.getAttribute("id")%>'
	var video_id = '${videoVO.video_id}'
	var like_num = ${videoVO.like_num }
	var dislike_num = ${videoVO.dislike_num }
	var channelId = '${channelVO.channel_id}'
	var like
	var likeOrigin
	var sub
	
	$(function() {
		if(userId != 'null'){ // 로그인 된 상태
			like = ${like}
			likeOrigin = like;
			sub = ${sub}
			likeCheck(like);
			$(window).on('beforeunload',function() { // 페이지 나갈 때, 변경 된 좋아요 정보 DB update
				if(like != likeOrigin){
					$.ajax({
						url : "updateLike.game",
						data : {
							videoId : video_id,
							like : like,
							likeOrigin : likeOrigin
						},
						success : function() {
						}
					})
				}
			})
		
			$('#likeButton').click(function() {		// 좋아요 버튼 클릭 시 변경 함수
				if(like == 1){
					like = 0;
					like_num -= 1;
					$('#likeNum').html(like_num);
				
				}else{
					if(like == 2){
						dislike_num -= 1;
					}
					like = 1;
					like_num++;
					$('#likeNum').html(like_num);
					$('#dislikeNum').html(dislike_num);
				}
			likeCheck(like);
			})
		
			$('#dislikeButton').click(function() {		//싫어요 버튼 클릭 시 변경 함수
				if(like == 2){
					like = 0;
					dislike_num -= 1;
					$('#dislikeNum').html(dislike_num);
				}else{
					if(like == 1){
						like_num -= 1;					
					}
					like = 2;
					dislike_num++;
					$('#likeNum').html(like_num);
					$('#dislikeNum').html(dislike_num);
				}
				likeCheck(like);
			})
		
			
			
			$('#subscribe').ready(function() {
				if (sub == 1) {
					$('#subscribe').text('구독취소')
					$('#subscribe').css('background','#aaaaaa')
				} else {
					$('#subscribe').text('구독')
					$('#subscribe').css('background','#cc0000')
				}
			}) //ready

			$('#subscribe').click(function() {
				console.log("클릭")
				$.ajax({
					url: "updateSuscribe.game",
					data: {
						channelId: channelId,
					},
					success: $('#subscribe').ready(function() {
						console.log("석시스")
						location.reload()
					}) //success
				}) //ajax
			}) //click
		
		}else{ // 로그인이 안된 상태
			
			$('#likeButton').click(function() {		// 좋아요 버튼 클릭 시 변경 함수
				location.href = "index.jsp"
			})
			$('#dislikeButton').click(function() {		//싫어요 버튼 클릭 시 변경 함수
				location.href = "index.jsp"
			})
			
			$('#subscribe').ready(function() {
			
					$('#subscribe').text('구독')
					$('#subscribe').css('background','#cc0000')
				
			}) //ready
	
			$('#subscribe').click(function() {
				console.log("클릭")
				location.href="index.jsp"
			}) //click
		}
		
		
		
	})
	function likeCheck(x) {
		if (like == 1) { // 페이지 시작시 좋아요 정보에 따라 좋아요/싫어요의 버튼 활성화 체크
			$('#likeButton').css('background', '#065fd4');
			$('#dislikeButton').css('background', '#fff');
		} else if (like == 2) {
			$('#likeButton').css('background', '#fff');
			$('#dislikeButton').css('background', '#065fd4');
		}else{
			$('#likeButton').css('background', '#fff');
			$('#dislikeButton').css('background', '#fff');
			
		}
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
					<a href="index.jsp"><img id="logo"
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
			<a href="index.jsp?page_i='0'" style="font-size: 30px;">홈</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="index.jsp?page_i='1'" style="font-size: 30px;">인기</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="index.jsp?page_i='2'" style="font-size: 30px;">구독</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="index.jsp?page_i='3'" style="font-size: 30px;">좋아요한 동영상</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="index.jsp?page_i='4'" style="font-size: 30px;">시청기록</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="" style="font-size: 30px;">결제</a>
		</div>
	</aside>
	<!-- 본문 -->
	<div class="main_page"></div>
	<div class="main_frame">
		<div class="watch_frame">
			<p class="iframe_p" style="margin: 0">
				<iframe width="1059" height="596"
					src="https://www.youtube.com/embed/${videoVO.video_id}?autoplay=1"
					frameborder="0"
					allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
					allowfullscreen></iframe>
			</p>
		</div>
		<div class="iframe_frame">



			<div>
				<div
					style="display: grid; grid-template-columns: [artwork-edge] 700px[title-edge] 140px[right-edge]140px; grid-column-gap: 20px;"
					class="btn-group" role="group" aria-label="Basic example">
					<div>
						<a style="color: blue;" href="#">${videoVO.tag }</a><br>
						<h2>${videoVO.video_title }</h2>
						<p>조회수 ${videoVO.play_num } 회 · ${videoVO.video_date }</p>
					</div>

					<div>
						<div id="likeButton"
							style="cursor: pointer;margin-top: 120px; font-size: 20px; font-weight: bolder;">
							<img style="fill: #065fd4;" width="30px" alt=""
								src="./resources/img/thumbs-up.png"> <span id="likeNum">${videoVO.like_num }</span>
						</div>
					</div>
					<div>
						<div id="dislikeButton"
							style="cursor: pointer;margin-top: 120px; font-size: 20px; font-weight: bolder;">
							<img width="30px" alt="" src="./resources/img/thumb-down.png">
							<span id="dislikeNum">${videoVO.dislike_num }</span>
						</div>
					</div>
				</div>
			</div>

			<hr>
			<div
				style="display: grid; grid-template-columns: [left-edge] 50px[artwork-edge] 790px[title-edge] 140px[right-edge]; grid-column-gap: 16px;">
				<div>
					<img style="border-radius: 50px;" width="100%"
						src="${channelVO.channel_img }">
				</div>
				<div style="padding-top: 15px;">${channelVO.channel_title }</div>
				<div id="subscribe"
					style="cursor: pointer; background: #cc0000; width: 100%; color: white; text-align: center; font-weight: bolder; padding-top: 17px;">구독</div>
			</div>
			<hr>
			댓글 div
		</div>

	</div>

</body>
</html>