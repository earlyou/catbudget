/**
 * 
 */

$(document).ready(function() {

	$('#addfile').on('change', function(e) {
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

	$('#addbtn').click(function() {
		$('#addform').removeClass('was-validated');
		$('#addregdate').val('')
		$('#adddetail').val('');
		$('#addprice').val('');
		$('#addfile').val('');
		$('#addmemo').val('');
	});

	$('#add').click(function() {
		if (!$('#addregdate').val() || !$('#adddetail').val() || !$('#addprice').val() || !$('#addfile').val()) {
			if (!$('#addregdate').val()) {
				$('#addform').addClass('was-validated');
				$('#addregdate').attr('required', true);
			}
			if (!$('#adddetail').val()) {
				$('#addform').addClass('was-validated');
				$('#adddetail').attr('required', true);
			}
			if (!$('#addprice').val()) {
				$('#addform').addClass('was-validated');
				$('#addprice').attr('required', true);
			}
			if (!$('#addfile').val()) {
				$('#addform').addClass('was-validated');
				$('#addfile').attr('required', true);
			}
			console.log('validation');
		} else {
			console.log('submit');
			$('#addform').attr({
				'method': 'post',
				'enctype': 'multipart/form-data',
				'action': '/catbudget/add'
			});
			$('#addform').submit();
		}
	});

	$('#delpic').on('click', function() {
		$('#addfile').val('');
		$('#previewImage').prop('hidden', true);
		$('#previewImage').attr('src', '').hide();
	});

	$('.mdbtn').click(function() {
		const readid = $(this).parent().parent().parent().parent().attr('id');
		var readregdate = $('#readdate' + readid).val();
		var readdetail = $('#readdetail' + readid).val();
		var readprice = $('#readprice' + readid).val();
		var readpic = $('#readpic' + readid).attr("src");
		var readmemo = $('#readmemo' + readid).val();

		$('#snum').val(readid);
		$('#mdfydate').val(readregdate);
		$('#mdfydetail').val(readdetail);
		$('#mdfyprice').val(readprice);
		$('#mdfypreviewImage').attr('src', readpic);
		$('#mdfymemo').val(readmemo);
	});
	$('#mdfydelpic').click(function() {
		$('#mdfyfile').val('');
		$('#mdfyfile').prop('hidden', false);
		$('#mdfypreviewImage').prop('hidden', true);
		$('#mdfypreviewImage').attr('src', '').hide();

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
			$('#nextpage').before('<li class="page-num active" id="page' + i + '"><a class="page-link" href="/catbudget?startdate=' + startdate + '&enddate=' + enddate + '&page=' + i + '&ipp=' + ipp + '">' + i + '</a></li>');
		} else {
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


