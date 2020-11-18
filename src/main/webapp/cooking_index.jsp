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
	
	var userId = '<%=session.getAttribute("user_id") %>'   
	var page_index = <%=request.getParameter("page_i")%>
	$(function() {
		if(page_index == null){ 
			aside_menu_button(0);
		}else{
			aside_menu_button(page_index);
		}
		
		$(".aside_button").click(function() {	// 페이지의 인덱스에 따른 메인프레임 동영상 목록 변경
			page_index = $(this).attr('id')		// 변경된 페이지 인덱스에 맞는 설정 값으로 비디오 목록 reload
			aside_menu_button(page_index)
			$(".main_frame *").remove();
			
		})
		login_check()
	})
	
	function aside_menu_button(x) {  // aside 버튼 css 변경
		$(".aside_div").css("background", "#212121")
		$(".aside_button").css("color", "#fff")
		$("#aside"+x).css("background", "#fff")
		$("#"+x).css("color", "#000")
		if(userId == 'null'){
			if(x>1 || x == 5){
				open_login();
			}else{
				page_load(x)
			}
		}else{
			if(x==5){
				location.href="http://127.0.0.1:8000/polls"				
			}else{
				page_load(x)
			} // else2
		} // else1
	} // aside_menu_button()
	
	function page_load(x) {
			
			$.ajax({							// 메인페이지 로딩 시에 나열될 동영상 정보를 json으로 가져옴(로그인 안한 상태)
				url : "cooking/select_main2.cooking",		// 레코드가 없어 최신순으로 불러옴
				data : {
					page_index : x				// aside의 menu_index에 따라 불러오는 영상의 순서를 변경 >> aside_index를 controller로 보냄
				},
				success : function(json) { 					// 불러온 영상 list를 json의 형태로 반환받아
					for (var i = 0; i < json.length; i++) { // 순서에 받게 append로 main_frame에 출력
						date = json[i].video_date			// datetime의 형태(yyyy-MM-dd HH:mm:ss)로 넘어온 String 값을
						array = date.split(" ")				// 스플릿함수를 이용하여 시간부분은 잘라내고 사용 >> (yyyy-MM-dd)
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
					}//for
					$(".video_frame").click(function() { // 영상 클릭 시 해당 영상 플레이 페이지로 넘어감
						var id = $(this).attr('id');
						location.href = "cooking/playingPage2.cooking?videoId=" + id;
					})// click
				} // success
			}) // ajax
	} //page_load
	
	function login_check() {
		if(userId == 'null'){      
			 $("#logout").css('display','none'); 
			 $("#login").css('display','inline-block');
		}else{                                
			$("#login").css('display','none'); 
			$("#logout").css('display','inline-block');
		}  
	}
	function open_login() {
		window.open(href="login/logn.do", '_blank', 'width=900px,height=700px,toolbars=no,scrollbars=no'); 
		login_check()
	}
	function logout_func() {
		$.ajax({
			url : "cooking/errorMail.cooking",
			success: function(result) {
				location.reload()
			}
		})
	}
	
</script>
<!-- 메인페이지 css -->
</head>
<body>
	<!-- 상단 네비게이션 -->
	<!-- 로그인 및 각 카테고리별로 이동할 수 있는 a태그 모음 -->
	<header>
		<nav class="nav_fix">
			<div id="main_icon">
				<h3>
					<a href="cooking_index.jsp"><img id="logo"
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
					<li><a onclick="open_login()" id='login'>Login</a></li>
					<li><a onclick="logout_func()"  id="logout">Logout</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- 좌측 사이드바 -->
	<!-- 각 카테고리에 기능들을 선택하는 nav 바 -->
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
			<a id = "5" class="aside_button" style="font-size: 30px;">x</a>
		</div>
	</aside>
	<!-- 본문 -->
	<div class="main_page"></div>
	<!-- 영상 목록이 호출되는 메인프레임 -->
	<div class="main_frame"
		style="height: auto; overflow: hidden; width: 1950px; margin-left: 195px;">
	</div>

</body>
</html>