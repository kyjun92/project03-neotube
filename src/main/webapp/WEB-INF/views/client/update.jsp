
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/client.css">
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
		$("#b1").click(function() {
			border_id = ${VO.border_id}
			border_title = $("#title").val()
			content = $("#content").val()
		
			
			//문자열 길이 
				location.href = '../client/update01.do?border_id=' + border_id
				+ '&border_title=' + border_title + '&content=' + content 
					
				
				
				
			
		
	})
	})
</script>

</head>
<body>
<nav
		class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
		<div class="container">
			<h3><a href="client.do">고객센터</a> </h3>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="../sports_index.jsp"">메인
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#"></a></li>
					<li class="nav-item"><a class="nav-link" href="#"></a></li>
					<li class="nav-item"><a class="nav-link" href="#"></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<table style="padding-top: 100px; margin: 0 auto; margin-top:250px; width: 1250px; cellpadding:2">
		<tr>
			<td height=20 align=center bgcolor=#ccc><font color=white>
					글수정</font></td>
		</tr>
		<tr>
			<td bgcolor=white>
				<table class="table2">
					<tr>
						<td>작성자</td>
						<td><input type=text name=user_id id="user_id"size=20 value="${user_id}">
						</td>
					</tr>

					<tr>
						<td>제목</td>
						<td><input type=text name=title id="title" size=60 value="${VO.border_title}"></td>
					</tr>

					<tr>
						<td>내용</td>
						<td><textarea name=content id="content" cols=85 rows=15 >${VO.content}</textarea></td>
					</tr>

				</table>


				<button value="작성" name="b1" id="b1">글수정</button>

			</td>
		</tr>

	</table>

</body>
</html>