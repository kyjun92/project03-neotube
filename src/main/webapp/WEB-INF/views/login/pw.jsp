<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
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
		$("#b1").click(function() {
			user_id = $("#user_id").val()
			tel = $("#tel").val()
			$.ajax({
				url :"select5.do",
				data: {
					user_id : user_id,
					tel : tel
					
				},
				success :function(result){
					if (result == "1") {
						location.href = '../login/select6.do?user_id=' + user_id + '&tel='
						+ tel
						
					}else{
					  alert("아이디또는전화번호를 확인해주세요");
					
				
				}//else
				
			}//success

		})

	})
	})
</script>
<!-- CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<body>
  <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h2 class="card-title text-center">PW찾기</h2>
            <form class="form-signin">
              <div class="form-label-group">
                <input type="text" id="user_id" name="user_id" class="form-control" placeholder="Email address" required autofocus>
                <label for="inputEmail">아이디</label>
              </div>
              <br>
              <div class="form-label-group">
                <input type="password" id="tel" name="tel" class="form-control" placeholder="tel" required>
                <label for="inputPassword">전화번호</label>
              </div>
              <br>
              
              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" id="b1" name="b1">찾기</button>
              
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</body>
</html>