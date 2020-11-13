/**
 * 
 */

	$(function(){
		$('div.next-video-mouseon-highlighter')
			.mouseenter(function() {
				$(this).css("background","#cccccc80")
			})
			.mouseleave(function() {
				$(this).css("background","#cccccc00")
			})
	})