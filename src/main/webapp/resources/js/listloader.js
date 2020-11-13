/**
 * 
 */
	$(function() {
		count = 0;
		load = _=>{
			$.ajax({
				url: url,
				data: {
					category: "키즈",
					count: count,
					user_id: user_id
				},
				success: function (result) {
					$("div#item-list").append(result)
				}
			})
		}
		$(window).scroll(function() {
			if($(window).scrollTop() >= $(document).height() - $(window).height()) {
				console.log(count)
				count++
				load()
			}
		})
		$(window).ready(function() {load()})
	})