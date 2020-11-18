<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		$("#button").click(function() {
			user_id = $("#user_id").val() // 로그인 세션아이디값
			pw = $("#pw").val() //pw입력값
			pw2 = $("#pw2").val() //pw입력값
			name = $("#name").val()//name입력값
			tel = $("#tel").val()//tel입력값
			$.ajax({
				url : "Modification1.do", //Controller
				data : {
					user_id : user_id,//데이터값 보내기
					pw : pw,
                    name : name,
                    tel : tel
				},
				success : function() { //처리후
					alert("회원수정되었습니다");
					location.href="client.do" //수정후 게시판으로 이동

				}//success
			})

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

	<div class="container" style="margin-top: 200px">
		<div class="row">
			<div class="col-sm-12">
				<div class="col-sm-2"></div>
				<div class="col-sm-9">
					<h2 class="text-center">회원 정보 수정하기</h2>

					

						<table class="table table-striped">
							<tr>
								<td>아이디</td>
								<td><input type="text" name="user_id" id="user_id" class="form-control" value="${vo.user_id}"></td>
							</tr>

							<tr>
								<td>이름</td>
								<td><input type="text" name="name" id="name" class="form-control" value="${vo.name}"></td>
							</tr>

							<tr>
								<td>전화번호</td>
								<td><input type="text" name="tel" id="tel" class="form-control" value="${vo.tel}"></td>
							</tr>
							<tr>
								<td>패스워드</td>
								<td><input type="password" name="pw" id="pw" 
									class="form-control" value="${vo.pw}"></td>
							</tr>

							

							<tr>
								<td colspan="2" class="text-center">
									<button type="button" class="btn btn-warning"
										id ="button">회원수정</button>
								</td>
							</tr>

						</table>

				</div>
			</div>
			<!-- col-sm-12 -->
		</div>
		<!-- row -->
	</div>
</body>
</html>