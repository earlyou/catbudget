/**
 * 
 */

$(document).ready(function() {
	console.log(uid);
	var ipp = $('#ipp').val();
	var lastpage = Math.ceil(listsize / ipp);
	if (lastpage >= 2) {
		for (var i = 2; i <= lastpage; i++) {
			$('#lastpagination').before('<li class="page-item"><a class="page-link" href="#">'+i+'</a></li>');
		}
	}
});