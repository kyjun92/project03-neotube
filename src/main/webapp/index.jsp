<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%-- <c:set var="id" value="kyjun92" scope="session"/> --%> <!-- 세션 설정 (로그인 엮으면 지워야 됨) -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Neotube</title>
<link rel="stylesheet" href="resources/css/index_page.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	
	var userId = '<%=session.getAttribute("id") %>'
	var page_index = <%=request.getParameter("page_i")%>
	$(function() {
		if(page_index == null){ 
			aside_menu_button(0);
		}
		aside_menu_button(page_index);
		
		$(".aside_button").click(function() {	// 페이지의 인덱스에 따른 메인프레임 동영상 목록 변경
			page_index = $(this).attr('id')
			aside_menu_button(page_index)
			$(".main_frame *").remove();
			
		})
	})
	
	function aside_menu_button(x) {  // aside 버튼 색 변경
		
		$(".aside_div").css("background", "#212121")
		$(".aside_button").css("color", "#fff")
		$("#aside"+x).css("background", "#fff")
		
		$("#"+x).css("color", "#000")
		page_load(x)
	}
	
	function page_load(x) {
		if(userId == 'null'){		//로그인 상태 체크
			$.ajax({							// 메인페이지 로딩 시에 나열될 동영상 정보를 json으로 가져옴(로그인 안한 상태)
				url : "select_main.game",		// 레코드가 없어 최신순으로 불러옴
				data : {
					page_index : x
				},
				success : function(json) {
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
					}
					$(".video_frame").click(function() { // 영상 클릭 시 해당 영상 플레이 페이지로 넘어감
						var id = $(this).attr('id');
						console.log(id);
						location.href = "playingPage.game?videoId=" + id;
					})
				}
			})
		}else{
			$.ajax({							// 메인페이지 로딩 시에 나열될 동영상 정보를 json으로 가져옴(로그인 상태)
				url : "select_main.game",		// 레코드와 각종 정보 기반 추천 알고리즘 순으로 출력 (각자 수정해야되~)
				data : {
					page_index : x
				},
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
					}
					$(".video_frame").click(function() { // 영상 클릭 시 해당 영상 플레이 페이지로 넘어감
						var id = $(this).attr('id');
						console.log(id);
						location.href = "playingPage.game?videoId=" + id;
					})
				}
			})
		}
	}
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
					<li><a href="">Sports</a></li>
					<li><a href="">Games</a></li>
					<li><a href="">Cooking</a></li>
					<li><a href="">Kids</a></li>
					<li><a href="">Supports</a></li>
					<li><a href="login.jsp">Login</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- 좌측 사이드바 -->
	<aside>
		<div id="aside0" class="aside_div" style="margin-left: -65px; margin-top: -15px;">
			<a id = "0" class="aside_button" style="font-size: 30px;">홈</a>
		</div>
		<div id="aside1" class="aside_div" style="margin-left: -65px; margin-top: -15px;">
			<a id = "1" class="aside_button" style="font-size: 30px;">인기</a>
		</div>
		<div id="aside2" class="aside_div" style="margin-left: -65px; margin-top: -15px;">
			<a id = "2" class="aside_button" style="font-size: 30px;">구독</a>
		</div>
		<div id="aside3" class="aside_div" style="margin-left: -65px; margin-top: -15px;">
			<a id = "3" class="aside_button" style="font-size: 30px;">좋아요한 동영상</a>
		</div>
		<div id="aside4" class="aside_div" style="margin-left: -65px; margin-top: -15px;">
			<a id = "4" class="aside_button" style="font-size: 30px;">시청기록</a>
		</div>
		<div id="aside5" class="aside_div" style="margin-left: -65px; margin-top: -15px;">
			<a href="#" style="font-size: 30px;">결제</a>
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