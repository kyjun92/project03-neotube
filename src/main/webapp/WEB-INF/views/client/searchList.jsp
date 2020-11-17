<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<!-- CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<script>
$(function() {
	$("#searchBtn").click(function(){
		searchValue = $('#search').val()
		console.log(searchValue);
		location.href="searchList.do?border_title="+searchValue;
		
	})//serachBtn click
	
	
})
</script>
</head>
<body>
	<!-- Navigation -->
	
	<nav
		class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
		<div class="container">
			<h3>고객센터</h3>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="#">메인
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#"></a></li>
					<li class="nav-item"><a class="nav-link" href="#"></a></li>
					<li class="nav-item"><a class="nav-link" href="#"></a></li>
				</ul>
			</div>
		</div>
	</nav>
 
    <div class="btn-group-vertical" style="margin-top: 300px" id="member">
<a href="information.do?user_id="><button id="b1" >회원정보</button></a><br>
<a href="Modification.do?user_id="><button id="b2" >회원수정</button></a><br>
<a href="taltoe.do?user_id="><button id="b3" >회원삭제</button></a>
    </div>
	<br>
	<div
		class="col-sm-4 col-md-7 col-lg-8"  style="margin-top: 300px; margin-left: 500px; position: relative;  text-align:center; width: 1000px;" >
		<table class="table" style="margin:0 auto;">
			<thead class="thead-dark">
				<tr>
					<th scope="col">번호</th>
					<th scope="col">아이디</th>
					<th scope="col">제목</th>
					<th scope="col">글쓴날짜</th>
				</tr>
				
				<sql:setDataSource url="jdbc:mysql://Localhost:3366/neotube"
					driver="com.mysql.jdbc.Driver" user="root" password="1234"
					scope="application" var="db" />
				<sql:query var="list" dataSource="${db}">
    select * from border
</sql:query>
			</thead>
			

			<tbody>
				 <c:forEach var="Client2VO" items="${list1}" varStatus="status"> 
					<tr>
						<th scope="row"><a href="one.do?border_id=${Client2VO.border_id}">${Client2VO.border_id}</a></th>
						<td><a href="one.do?border_id=${Client2VO.border_id}">${Client2VO.user_id}</a></td>
						<td><a href="one.do?border_id=${Client2VO.border_id}">${Client2VO.border_title}</a></td>
						<td><a href="one.do?border_id=${Client2VO.border_id}">${Client2VO.date}</a></td>
					</tr>

				 </c:forEach> 

			</tbody>
		</table>
		<br>
<div id="searchBtn" style="cursor:pointer;border:1px solid black; width:100px;float:right;">검색</div>
	</div>
		<input id="search" type="text" style="float:right; margin-right:10px;">
	<form action="Client2.do">
		<button style="margin-left: 550px; margin-top: 100px" id="writing">글쓰기</button>
	</form>
	
</body>
<script type="text/javascript">
 var loginUserId = '${user_id}'                    
			  console.log(loginUserId);                        
			if(loginUserId == null || loginUserId ==''){      
			$("#member").css('display','none'); 
			$("#writing").css('display','none');
			}else{                                
			$("#member").css('display','inline-block');
			$("#writing").css('display','inline-block');
			}     
</script>
</html>



