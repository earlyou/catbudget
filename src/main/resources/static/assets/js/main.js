/**
 * 
 */

$(document).ready(function() {

	$('#addfile').on('change', function(e) {
		console.log('file changed')
		const f = e.target.files[0];
		if (f) {
			const r = new FileReader();
			r.onload = function(e) {
				$('#previewImage').attr('src', e.target.result).show();
			}
			r.readAsDataURL(f);
			
			$('#previewImage').prop('hidden', false);
		} else {
			$('#previewImage').prop('hidden', true);
			$('#previewImage').attr('src', '').hide();
		}
	});

	$('#add').click(function() {
		$('#addform').attr({
			'method': 'post',
			'action': '/catbudget/add'
		});
		$('#addform').submit();
	});

	/** URL의 param 숨기기
	 * (ex. https://earlyou.com/catbudget/?tt=test&test1=test1 => https://earlyou.com/catbudget/) 
	 * */
	history.replaceState({}, null, location.pathname);


	var startdate = $('#startdate').val();
	var enddate = $('#enddate').val();
	/** initiate date picker start date */
	$('#startdate').datepicker({
		format: 'yyyy-mm-dd',
		todayBtn: "linked",
		clearBtn: true,
		language: "ko",
		autoclose: true,
		todayHighlight: true
	});

	/** initiate date picker */
	$('#datepicker').datepicker({
		format: 'yyyy-mm-dd',
		todayBtn: 'linked',
		clearBtn: true,
		language: 'ko',
		todayHighlight: true
	});

	/** initiate date picker end date */
	$('#enddate').datepicker({
		format: 'yyyy-mm-dd',
		todayBtn: "linked",
		clearBtn: true,
		language: "ko",
		todayHighlight: true
	});

	/** date search button */
	$('#datesrchbtn').on('click', function() {
		startdate = $('#startdate').val();
		enddate = $('#enddate').val();

		$(location).attr('href', '/catbudget?startdate=' + startdate + '&enddate=' + enddate + '&page=' + page + '&ipp=' + ipp);

	});

	/** 
	 * pagination 생성
	 * ipp = items per page
	 * sin = start item number */
	$('#prevpage').children('a').attr('href', '/catbudget?startdate=' + startdate + '&enddate=' + enddate + '&page=' + (page - 1) + '&ipp=' + ipp);
	$('#nextpage').children('a').attr('href', '/catbudget?startdate=' + startdate + '&enddate=' + enddate + '&page=' + (page + 1) + '&ipp=' + ipp);
	for (var i = 1; i <= maxpage; i++) {
		if (i == page) {
			console.log('active: ' + i);
			$('#nextpage').before('<li class="page-num active" id="page' + i + '"><a class="page-link" href="/catbudget?startdate=' + startdate + '&enddate=' + enddate + '&page=' + i + '&ipp=' + ipp + '">' + i + '</a></li>');
		} else {
			console.log('!active: ' + i);
			$('#nextpage').before('<li class="page-num" id="page' + i + '"><a class="page-link" href="/catbudget?startdate=' + startdate + '&enddate=' + enddate + '&page=' + i + '&ipp=' + ipp + '">' + i + '</a></li>');
		}
	}
	if (page == 1) {
		$('#prevpage').addClass('disabled');
	} else {
		$('#prevpage').removeClass('disabled');
	}
	if (page == maxpage) {
		$('#nextpage').addClass('disabled');
	} else {
		$('#nextpage').removeClass('disabled');
	}

	/** 페이지당 데이터 개수 변경 시 이벤트 */
	$('#ipp').change(function() {

		ipp = $('#ipp').val();
		page = 1;
		$(location).attr('href', '/catbudget?startdate=' + startdate + '&enddate=' + enddate + '&page=' + page + '&ipp=' + ipp);

	});
});


