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
	$(function() { //dom tree!!
		check = 'false'; //id가 가입 되어있음.
		$("#birthday").datepicker({
			dateFormat : 'yy-mm-dd'
		})//datepicker

		$("#user_id").keyup(function() {
			user_id = $('#user_id').val()

			$.ajax({
				url : "select.do",
				data : {
					user_id : user_id
				},
				success : function(result) {
					if (result == 'false') {
						$("#d1").html('<font color="red">이미가입된아이디입니다</font>')
						
					} else {
						$("#d1").html('<font color="blur">가입가능한아이디입니다</font>')
						check = 'true'; //id가 가입 가능할 때.
						
					}//else
				}//success
			})//ajax
		})//blur 

		$("#pw2").keyup(function() {
			pw = $('#pw').val()
			pw2 = $('#pw2').val()

			if (pw == pw2) {

				$("#d2").html('<font color="red"></font>')
			} else {
				$("#d2").html('<font color="red">패스워드확인해주세요</font>')
			}//else
		})//blur



		$("#create").click(
				function() {
					//(flag1 == true && flag2 == true) 
					user_id = $('#user_id').val()
					pw = $("#pw").val()
					pw1 = $("#pw2").val()
					name = $("#name").val()
					gender = $("input[name='gender']:checked").val()
					birthday = $("#birthday").val()
					tel = $("#tel").val()
					
					if (check == 'false') {
						alert("아이디를확인해주세요");
						 //submit넘기지 않음.!!
						} else if (user_id.length == "" || pw.length == ""
								|| pw1.length == "" || name.length == ""
								|| gender.length == ""
								|| birthday.length == ""
								|| tel.length == "") {
							 alert("모든값을입력해주세요");

						} else if(pw==pw1){ ///*  else if (condition) 
						// 회원가입하는 페이지로!!
						alert("회원가입 성공!")
						location.href = '../login/insert.do?user_id=' + user_id
								+ '&pw=' + pw + '&name=' + name + '&gender='
								+ gender + '&birthday=' + birthday + '&tel='
								+ tel
					}else{
						 alert("비밀번호를 확인해주세요");
					} 
				

				})//click
	})
</script>


</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-10 col-md-10 col-lg-10 mx-auto">
				<div class="card card-signin my-5">
					<div class="card-body">
						<h5 class="card-title text-center">회원가입</h5>
						
							<div class="form-label-group col-sm-9 col-md-9 col-lg-9 mx-auto">
								<input type="text" id="user_id" name="user_id"
									class="form-control" placeholder="Email address" required
									autofocus> <label for="inputEmail"> 아이디</label>
								<div id="d1" name="d1"></div>
							</div>
							<br>

							<div class="form-label-group col-sm-9 col-md-9 col-lg-9 mx-auto">
								<input type="password" id="pw" name="pw" class="form-control"
									placeholder="Password" required> <label
									for="inputPassword">비밀번호</label>
							</div>
							<br>

							<div class="form-label-group col-sm-9 col-md-9 col-lg-9 mx-auto">
								<input type="password" id="pw2" name="pw2" class="form-control"
									placeholder="Password" required> <label
									for="inputPassword">비밀번호 확인</label>
								<div id="d2" name="d2"></div>
							</div>
							<br>

							<div class="form-label-group col-sm-9 col-md-9 col-lg-9 mx-auto">
								<input type="text" id="name" name="name" class="form-control"
									placeholder="Name" required autofocus> <label
									for="inputEmail">이름</label>
								<div id="d3" name="d3"></div>
							</div>
							<br>


							<div class="form-label-group col-sm-9 col-md-9 col-lg-9 mx-auto">
								<label class="btn btn-primary"> <input type="radio"
									name="gender" id="option2" autocomplete="off" value="m">
									남자
								</label> <label class="btn btn-primary"> <input type="radio"
									name="gender" id="option3" autocomplete="off" value="f">
									여자
								</label><br> <label for="inputgender">성별</label>
								<div id="d4" name="d4"></div>
							</div>

							<br>

							<div class="form-label-group col-sm-9 col-md-9 col-lg-9 mx-auto">
								<input type="text" id="birthday" name="birthday"
									class="form-control" placeholder="생년월일" required
									autofocus> <label for="inputEmail">생년월일 </label>
								<div id="d5" name="d5"></div>
							</div>
							<br>
							<div class="form-label-group col-sm-9 col-md-9 col-lg-9 mx-auto">
								<input type="text" id="tel" name="tel" class="form-control"
									placeholder="Tel" required autofocus> <label
									for="inputEmail">전화번호 </label>
								<div id="d6" name="d6"></div>
							</div>
							<br>


							<button
								class="btn btn-lg btn-primary btn-block text-uppercase col-sm-9 col-md-9 col-lg-9 mx-auto"
								id="create">가입</button>
					


					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>