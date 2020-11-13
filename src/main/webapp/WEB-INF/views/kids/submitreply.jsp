<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><div class="reply-item card">
		<div class="card-header">작성자: ${replyvo.user_id} / 작성일시: <fmt:formatDate value="${ replyvo.date }" pattern="yyyy. MM. dd. HH:mm:ss"/></div>
		<div class="card-content">${replyvo.content}</div>
	</div>