<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- ajax 의 append 부분  -->
<c:forEach items="${list}" var="vo" varStatus="status">
	<fmt:parseNumber var="view_num" integerOnly="true"
		value="${vo.play_num/10000}" /><!-- 10000으로 나누어 ex) 조회수 : 236만회 로 표현 -->
	<div class="video_frame">
		<div class="thumbnail_frame">
			<!-- 452 260  ${vo.thumbnail}-->
			<a id="watch${status.index}"
				href="sports/watch.do?video_id=${vo.video_id}&channel_id=${vo.channel_id}"><!--controller 호출시 파라미터값 전달--><img
				src="${vo.thumbnail}" style="width: 452px; height: 260px;" alt="썸네일"></a>
			<!--sports/watch.do  -->
		</div>
		<!--EL태그를 이용한 class, id값 ex)content_frame0...1..2..3..4..5..-->
		<div class="content_frame${status.index}" style="padding: 5px;">
			<h3 id="video_title${status.index}">${vo.video_title}</h3>
				<p id="video_chanel${status.index}">${vo.channel_title}</p>
				<!-- c:if 문을 통하여 view_num 값이 0일때 8번째줄 value 가 0일때와 아닐때를 구분하여 나타냄 -->
			<c:if test="${view_num eq '0'}">
				<p id="video_view1${status.index}">조회수 : ${vo.play_num}회 · ${vo.video_date}</p>
			</c:if>
			<c:if test="${view_num ne '0'}">
				<p id="video_view2${status.index}">조회수 : ${view_num}만회 · ${vo.video_date}</p>
			</c:if>
		</div>
	</div>
</c:forEach>
