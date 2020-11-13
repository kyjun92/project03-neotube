/**
 * 
 */
$(function() {
	count = 0
	// 시청기록
	if('' != '') {
		$.ajax({
			url: "addhistory",
			data: {
				user_id: user_id,
				video_id: video_id,
			},
			success: function(result) {
			}
		})
	}
	// 리플불러오기
	$.ajax({
		url: "reply",
		data: {video_id: video_id},
		type: "GET",
		success: function(result){
			$("div#reply-list").html(result)
		}
	})
	// 다음영상 리스트 불러오기(임시로 모든 영상 불러오게 했음)
	loadnextvideo = _=>{
		if(count < 10) {
			$.ajax({
				url: 'nextvideo',
				data: {
					category: "키즈",
					count: count
				},
				success: function (result) {
					$("div.next-video-container").append(result)
				}
			})
		}
	}
	$(window).scroll(function() {
		if($(window).scrollTop() >= $(document).height() - $(window).height()) {
			count++;
			loadnextvideo()
		}
	})
	$(document).ready(function(){loadnextvideo()})
	//좋아요
	
	$('button#like').click(function(){
		if (user_id == "") {
			alert("로그인 후 이용할 수 있습니다")
			return
		} 
		$.ajax({
			url: "like",
			data: {
				video_id:video_id,
				user_id:user_id
			},
			success: function(result){
				switch(result) {
				case "do":
					$('button#like').attr('class', 'btn btn-primary');
					$('button#like').html("좋아요 " + (parseInt($('button#like').html().substring(4)) + 1))
					break;
				case "toggle":
					$('button#like').attr('class', 'btn btn-primary');
					$('button#like').html("좋아요 " + (parseInt($('button#like').html().substring(4)) + 1))
					$('button#dislike').attr('class', 'btn btn-dark');
					$('button#dislike').html("싫어요 " + (parseInt($('button#dislike').html().substring(4)) - 1))
					break;
				case "undo":
					$('button#like').attr('class', 'btn btn-dark');
					$('button#like').html("좋아요 " + (parseInt($('button#like').html().substring(4)) - 1))
					break;
				}
			}
		})
	})
	//싫어요
	$('button#dislike').click(function(){
		if (user_id == "") {
			alert("로그인 후 이용할 수 있습니다")
			return
		} 
		$.ajax({
			url: "dislike",
			data: {
				video_id:video_id,
				user_id:user_id
			},
			success: function(result){
				switch(result) {
				case "do":
					$('button#dislike').attr('class', 'btn btn-danger');
					$('button#dislike').html("싫어요 " + (parseInt($('button#dislike').html().substring(4)) + 1))
					break;
				case "toggle":
					$('button#dislike').attr('class', 'btn btn-danger');
					$('button#dislike').html("싫어요 " + (parseInt($('button#dislike').html().substring(4)) + 1))
					$('button#like').attr('class', 'btn btn-dark');
					$('button#like').html("좋아요 " + (parseInt($('button#like').html().substring(4)) - 1))
					break;
				case "undo":
					$('button#dislike').attr('class', 'btn btn-dark');
					$('button#dislike').html("싫어요 " + (parseInt($('button#dislike').html().substring(4)) - 1))
					break;
				}
			}
		})
	})
	//구독
	$('button#subscribe').click(function() {
		$.ajax({
			url: "subscribe",
			type: "POST",
			data: {
				user_id: user_id,
				channel_id: channel_id
			},
			success: function (result) {
				switch(result) {
				case "do":
					$('button#subscribe').attr('class', 'btn btn-danger')
					$('button#subscribe').html('구독취소')
					break;
				case "undo":
					$('button#subscribe').attr('class', 'btn btn-dark')
					$('button#subscribe').html('구독')
					break;
				}
			}
		})
	})
	
	//리플달기
	$('button#reply-submit').click(function() {
		if ($('#reply-content').val() == '') {
			alert("내용을 입력하세요")
		} else if ('user_id' == '') {
			alert("로그인 후에 이용할 수 있습니다")
		} else {
			$.ajax({
				url: "reply",
				type: "POST",
				data: {
					user_id: user_id,
					video_id: video_id,
					content: $('#reply-content').val()
				},
				success: function(result) {
					$('#reply-list').prepend(result);
					$('#reply-content').val('');
				}
			})
		}
	})
})