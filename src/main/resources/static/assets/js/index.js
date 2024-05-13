/**
 * 
 */

$(document).ready(function() {
	var session = '<%=(String)session.getAttribute("admin")%>';
	console.log('session: ' + session);
});