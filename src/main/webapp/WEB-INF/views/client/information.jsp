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
	
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<div class="col-sm-2"></div>
				<div class="col-sm-9">
					<h2 class="text-center">회원 정보</h2>

					<form action="MemberDeleteProc.jsp" method="post">

						<table class="table table-striped">
							<tr>
								<td>아이디</td>
								<td>${vo.user_id}</td>
							</tr>

							<tr>
								<td>이름</td>
								<td>${vo.name}</td>
							</tr>

							<tr>
								<td>생년월일</td>
								<td>${vo.birthday}</td>
							</tr>
							<tr>
								<td>전화번호</td>
								<td>${vo.tel}</td>
							</tr>

							<tr>
								<td>성별</td>
								<td>${vo.gender}</td>
							</tr>

						

						</table>

					</form>

				</div>
			</div>
			<!-- col-sm-12 -->
		</div>
		<!-- row -->
	</div>
</body>
</html>