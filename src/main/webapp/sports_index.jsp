<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Neotube</title>
<link rel="stylesheet" href="resources/css/index_page.css">
<script src="resources/js/jquery-3.5.1.js"></script>
<script type="text/javascript">
$(function(){ //페이지 시작시 ajax실행
	$.ajax({ 
		url :"sports/main_view.do", // 페이지 시작시 main_view.do 와 mapping되는 controller로 mapping 
		error : function(){
	        alert('ajax 실패'); // 실패 시 처리
	       },
		success : function(result){ //성공시 main_view 에있는 내용들 result에 담기
				$('.main_frame').append(result) // result에 담겨 있는 내용 class="main_frame" 에 append(붙여넣기) 결과적으론 추천알고리즘을 띄워줌 
		}// success
	})//ajax
	 var loginUserId = '${user_id}'                    
		  console.log(loginUserId);                        
		if(loginUserId == null || loginUserId ==''){      
		 $("#logout").css('display','none'); 
		 $("#login").css('display','inline-block');
		}else{                                
		$("#login").css('display','none'); 
		$("#logout").css('display','inline-block');
		}       
})// function
</script>
<!-- 메인페이지 css -->
</head>
<body>
	<!-- 상단 네비게이션 -->
	<header>
		<nav class="nav_fix">
			<div id="main_icon">
				<h3>
					<a href="index.html"><img id="logo" src="resources/img/logo3.png" width=150 height=30.61></a>
				</h3>
			</div>
			<div id="nav_category">
				<ul>
					<li><a href="sports_index.jsp">Sports</a></li>
					<li><a href="game_index.jsp">Games</a></li>
					<li><a href="cooking_index.jsp">Cooking</a></li>
					<li><a href="kids_index.jsp">Kids</a></li>
					<li><a href="client/client.do">Supports</a></li>
					<li><a href="login/logn.do" onclick="window.open(this.href, '_blank', 'width=900px,height=700px,toolbars=no,scrollbars=no'); return false;" id="login">Login</a></li>
					<li><a href="login/logout.do" id="logout">logout</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- 좌측 사이드바 -->
	<aside>
		<div style="margin-left: -65px;margin-top: -15px;"><a id="home" href="sports_index.jsp" style="font-size: 30px; ">홈</a></div>
		<div style="margin-left: -65px;margin-top: -15px;"><a id="popular" href="sports/popular.do" style="font-size: 30px;">인기</a></div>
		<div style="margin-left: -65px;margin-top: -15px;"><a id="subscribe" href="sports/subscribe.do?user_id=<%=session.getAttribute("user_id")%>" style="font-size: 30px;">구독</a></div>
		<div style="margin-left: -65px;margin-top: -15px;"><a id="like_video" href="sports/likepage.do" style="font-size: 30px;">좋아요한 동영상</a></div>
		<div style="margin-left: -65px;margin-top: -15px;"><a id="recoding_view" href="sports/record_view.do" style="font-size: 30px;">시청기록</a></div>
		<div style="margin-left: -65px;margin-top: -15px;"><a id="payment "href="payment/payment.do" style="font-size: 30px;">결제</a></div>
	</aside>
	<!-- 본문 -->
	<div class="main_page" >
	
	</div>
	<div class="main_frame" style=" height:auto; overflow:hidden; width: 1950px; margin-left: 195px; ">
	</div> 
</body>
</html>