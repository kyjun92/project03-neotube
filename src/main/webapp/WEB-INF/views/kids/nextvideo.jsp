<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="<c:url value="/resources/css/nextvideo.css?after" />" type="text/css" />
<c:forEach items = "${bag}" var="vo">
    <div class="next-video" onclick="location.href='video?id=${ vo.video_id }'">
   		<div class="next-video-thumbnail">
   			<img src="${vo.thumbnail}">
   		</div>
   		<div class="next-video-desc">
			<p><span class="next-video-desc-title">${ vo.video_title }</span></p>
			<p class="next-video-desc-etc">
				<span class="next-video-desc-channel-title one-line-ellipsis">${ vo.channel_title }</span><br>
				조회수: <fmt:formatNumber value="${ vo.play_num }"/> /
				<fmt:formatDate value="${ vo.video_date }"/> 
			</p>
   		</div>
   		<div class="next-video-mouseon-highlighter"></div>
   	</div>
</c:forEach>
<script src="<c:url value="/resources/js/nextvideo.js?after" />"></script>