<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
			name = $("#name").val()
			tel = $("#tel").val()
			$.ajax({
				url :"select3.do",
				data: {
					name : name,
					tel : tel
					
				},
				success :function(result){
					if (result == "1") {
						location.href = '../mvc00/select4.do?name=' + name + '&tel='+ tel
						
					}else{
					  alert("이름또는번호를 확인해주세요");
					
				
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
            <h2 class="card-title text-center">ID찾기</h2>
            
              <div class="form-label-group">
                <input type="text" id="name" name="name" class="form-control" placeholder="Email address" required autofocus>
                <label for="inputEmail">이름</label>
              </div>
               <br>
              <div class="form-label-group">
                <input type="password" id="tel" name="tel" class="form-control" placeholder="Password" required>
                <label for="inputPassword">전화번호</label>
              </div>
				<br>
             
              <button class="btn btn-lg btn-primary btn-block text-uppercase"  id="b1" name="b1">찾기</button>
             
           
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</body>
</html>