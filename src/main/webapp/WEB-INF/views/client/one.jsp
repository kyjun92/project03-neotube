<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<script type="text/javascript" src="../resources/js/jquery-3.5.1.js"></script>
<script type="text/javascript">

$(function(){
	$('#reply').click(function(){
		content = $('#content').val()
		
		$.ajax({
			url: "create.do",
			data: {
				
				user_id: '${user_id}',
				border_id: '${one.border_id}',
				content: content
			},
			success: function(result){
				$('#table2').append(result)
				location.reload();
				
			}
		})
	})
})
</script>
 
</head>
<body>
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
	
	<table style="padding-top: 50px; margin-top: 200px" align=center width=1000 border=0
		cellpadding=2>
		<tr>
			<td height=20 align=center bgcolor=#ccc><font color=white>
					글쓰기</font></td>
		</tr>
		<tr>
			<td bgcolor=white>
				<table class="table2" style="margin-top: 50px" >
					<tr>
					<tr>
						<td><h6>작성자</h6></td>
						</tr>
						<td>
							<div >${one.user_id}"</div>
						</td>
					</tr>
				</table>
				<hr>
				<table class="table2" >
					<tr>
					<tr>
						<td>제목</td>
						</tr>
						<td><div>${one.border_title}"></div>
					</tr>
					</table>
					<hr>
               <table class="table2" >
					<tr>
					<tr>
						<td>내용</td>
						</tr>
						<td><div>${one.content}</div></td>
					</tr>
                    </table>
				<br><br><br><br><br><br><br><br><br><br>
				<a href="update.do?border_id=${one.border_id} "id ="modify"><button value="수정" >글수정</button></a>
				
				<a href="delete01.do?border_id=${one.border_id}"id="remove" ><button value="삭제" >글삭제</button></a>
				</tr>
				</table align=center; >
				</table>
				<div style="margin: 0 auto; width: 350px;" id="comment">
				<hr style="margin-top: 150px">댓글 : &nbsp;&nbsp;&nbsp;&nbsp;<input id="content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="reply">입력</button>
				</div>
	<div id="d2" style="width: 450px; height: 200px; ">
		<table id="table2" align="center" width=450px; >
			<c:forEach items="${list}" var="vo" varStatus="status">
			<div  style="margin-right: 300px"  width=1000 >
				<tr>
					<td style="width: 50px;">${status.index+1}</td>
					<td style="width: 300px;">${vo.content}</td>
					<td style="width: 100px;" >${vo.user_id}</td>
										<td style="width: 100px;">
						<!-- one.do?no=1  --> 
						<a href="delete3.do?reply_id=${vo.reply_id}&border_id=${vo.border_id}" id=delete>
							<button >삭제</button>
					</a>
					</td>
				</tr>
				</div>
			</c:forEach>
		</table>
	</div>
					
</body>
<script>
var loginUserId = '${user_id}'                    
var detailUserId = '${one.user_id}'
	if(loginUserId == detailUserId){      
	$("#remove").css('display','inline-block');
	$("#modify").css('display','inline-block');	
	}else{
	$("#remove").css('display','none');	
	$("#modify").css('display','none'); 
	}  
var loginUserId = '${user_id}'                    
	  console.log(loginUserId);                        
	if(loginUserId == null || loginUserId ==''){      
	$("#comment").css('display','none'); 
	}else{                                
	$("#comment").css('display','inline-block');
	}   
	
</script>
</html>