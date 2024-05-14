/**
 * 
 */


$(document).ready(function() {
	if (v == 'f') {
		$('#login_form').addClass('was-validated');
	};
	
	$('input').keypress(function() {
		$('#login_form').removeClass('was-validated');
	});
});