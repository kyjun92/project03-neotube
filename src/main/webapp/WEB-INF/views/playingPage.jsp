<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/index_page.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

	var userId = '<%=session.getAttribute("user_id")%>' // 세션에 잡혀있는 userId를 저장할 변수
	var video_id = '${videoVO.video_id}'  //비디오 id를 저장할 변수
	var like_num = ${videoVO.like_num } // 좋아요수를 저장할 변수 
	var dislike_num = ${videoVO.dislike_num } // 싫어요수를 저장할 변수
	var channelId = '${channelVO.channel_id}' // 채널 id를 저장할 변수
	var like		//변경되는 좋아요/싫어요 상태를 저장하는 변수
	var likeOrigin // 변경되기 전 페이지 시작시 db에서 가져온 좋아요/싫어요 상태를 저장하는 변수
	var sub     // 구독상태를 저장할 변수
	
	$(function() {
		
		if(userId != 'null'){ // 로그인 된 상태
			like = ${like}					//페이지 시작시 가져온 좋아요 정보를 저장 (좋아요 체크 변경시 변수 변경)
			likeOrigin = like;				//페이지 시작시 가져온 좋아요 정보를 저장
			sub = ${sub}					// 구독 정보 저장
			likeCheck(like);				// 좋아요 상태에 따라 버튼의 상태 변경
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
			
			/* 좋아요 버튼 함수 */
			$('#likeButton').click(function() {		// 좋아요 버튼 클릭 시 변경 함수
				if(like == 1){						//좋아요(1) 상태 였을 때, 
					like = 0;						//불이 꺼진(좋아요 싫어요 모두 꺼진 상태)로 변경	
					like_num -= 1;					//전체 좋아요 숫자에서 -1
					$('#likeNum').html(like_num);   //좋아요 숫자 div에 반영
				
				}else{								//좋아요(1) 상태가 아닐때,
					if(like == 2){					// 싫어요(2) 상태일 때,
						dislike_num -= 1;			// 싫어요 전체 숫자 -1
					}
					like = 1;						//좋아요(1) 상태로 변경
					like_num++;						//전체 좋아요 숫자 +1
					$('#likeNum').html(like_num);	
					$('#dislikeNum').html(dislike_num);	// 좋아요 and 싫어요 숫자 반영
				}
			likeCheck(like);						// 버튼의 css 변경 함수 실행
			})
		
			$('#dislikeButton').click(function() {		//싫어요 버튼 클릭 시 변경 함수
				if(like == 2){							//싫어요(2) 상태였을때
					like = 0;							//좋아요/싫어요 모두 불꺼진 상태로 변경
					dislike_num -= 1;					//싫어요 수 -1
					$('#dislikeNum').html(dislike_num); //html 변경
				}else{									//싫어요(2)상태가 아닐 때, (0 or 1)
					if(like == 1){						//좋아요(1)상태 일 때, 
						like_num -= 1;					//좋아요를 -1
					}
					like = 2;							//싫어요(2) 상태로 like_index 변경
					dislike_num++;						//싫어요 갯수 +1
					$('#likeNum').html(like_num);		//좋아요 and 싫어요 숫자 반영
					$('#dislikeNum').html(dislike_num);
				}
				likeCheck(like);						// 버튼의 css 변경 함수 실행
			})
		
			
			/* 구독 버튼 함수 */
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
				location.href="login/logn.do"
			})
			$('#dislikeButton').click(function() {		//싫어요 버튼 클릭 시 변경 함수
				location.href="login/logn.do"
			})
			
			$('#subscribe').ready(function() {
			
					$('#subscribe').text('구독')
					$('#subscribe').css('background','#cc0000')
				
			}) //ready
	
			$('#subscribe').click(function() {
				console.log("클릭")
				location.href="login/logn.do"
			}) //click
		}
		
		
		
	})
	function likeCheck(x) {	// like_index에 따라 css 변경하는 함수
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

</head>
<body>
	<!-- 상단 네비게이션 -->
	<header>
		<nav class="nav_fix">
			<div id="main_icon">
				<h3>
					<a href="game_index.jsp"><img id="logo"
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
		<!-- 각 메뉴의 인덱스 값을 파라미터로 보냄 -->
		<!-- 메뉴의 인덱스값을 index페이지에서 받아 선택한 메뉴를 출력 -->
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="game_index.jsp?page_i='0'" style="font-size: 30px;">홈</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="game_index.jsp?page_i='1'" style="font-size: 30px;">인기</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="game_index.jsp?page_i='2'" style="font-size: 30px;">구독</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="game_index.jsp?page_i='3'" style="font-size: 30px;">좋아요한 동영상</a>
		</div>
		<div style="margin-left: -65px; margin-top: -15px;">
			<a href="game_index.jsp?page_i='4'" style="font-size: 30px;">시청기록</a>
		</div>
		<div id="aside5" class="aside_div" style="margin-left: -65px; margin-top: -15px;">
			<a href="game_index.jsp?page_i='5'" style="font-size: 30px;">추천</a>
		</div>
	</aside>
	<!-- 본문 -->
	<div class="main_page"></div>
	<div class="main_frame">
		<div class="watch_frame">
			<p class="iframe_p" style="margin: 0">
			
			<!-- Youtube 비디오 플레어 // 선택한 영상의 video_id값을 받아 알맞은 영상을 출력 -->
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
					<!-- 실행한 비디오에 맞는 영상 정보를 출력하는 div -->
					<div>
						<a style="color: blue;" href="#">${videoVO.tag }</a><br>
						<h2>${videoVO.video_title }</h2>
						<p>조회수 ${videoVO.play_num } 회 · ${videoVO.video_date }</p>
					</div>
					
					<!-- 좋아요 / 싫어요 버튼 -->
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
			<!-- 채널정보 출력 div -->
			<div
				style="display: grid; grid-template-columns: [left-edge] 50px[artwork-edge] 790px[title-edge] 140px[right-edge]; grid-column-gap: 16px;">
				<div>
					<img style="border-radius: 50px;" width="100%"
						src="${channelVO.channel_img }">
				</div>
				<div style="padding-top: 15px;">${channelVO.channel_title }</div>
				<!-- 구독 버튼 -->
				<div id="subscribe"
					style="cursor: pointer; background: #cc0000; width: 100%; color: white; text-align: center; font-weight: bolder; padding-top: 17px;">구독</div>
			</div>
			<hr>
			댓글 div
		</div>

	</div>

</body>
</html>