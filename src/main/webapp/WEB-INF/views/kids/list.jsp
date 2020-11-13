<%@page import="com.mega.mvc01.kids.KidsVO"%><%@page import="java.util.List"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach items="${bag}" var="vo">	
	<div class="item" onclick="location.href='video?id=${ vo.video_id }'">
		<input name="" value="" hidden="hidden">
		<div class="item-thumbnail">
			<img class="thumbnail" src="${ vo.thumbnail }">
			<div class="item-video-leng-wrapper">
				<div class="item-video-leng">
					<!-- 재생시간값의 리스트를 int형태로 받아왔기 때문에 편의상 뷰에서 처리 -->
					<c:set var="hour"><fmt:formatNumber value="${(vo.video_leng - (vo.video_leng mod 3600)) div 3600}" minIntegerDigits="2" maxFractionDigits="0" /></c:set>
					<c:set var="minute"><fmt:formatNumber value="${(((vo.video_leng mod 3600) - (vo.video_leng mod 60)) div 60) mod 1}" minIntegerDigits="2" maxFractionDigits="0" /></c:set>
					<c:set var="second"><fmt:formatNumber value="${vo.video_leng mod 60}" minIntegerDigits="2" maxFractionDigits="0" /></c:set>
					<c:if test='${hour ne "00"}'>${hour}:</c:if>${minute}:${second}
				</div>
			</div>
		</div>
		<div class="item-description">
			<p><span class="item-desc-title">${ vo.video_title }</span></p>
			<p>
				<span class="item-desc-channel-title one-line-ellipsis">${ vo.channel_title }</span><br>
				조회수: <fmt:formatNumber value="${ vo.play_num }"/> /
				<fmt:formatDate value="${ vo.video_date }"/> 
			</p>
		</div>
		<div class="item-mouseon-highlighter"></div>
	</div>
</c:forEach>
<script src="<c:url value="/resources/js/list.js" />"></script>
