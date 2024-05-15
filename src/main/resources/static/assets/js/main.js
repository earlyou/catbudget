/**
 * 
 */

$(document).ready(function() {
	var ipp = $('#ipp').val();
	var lastpage = Math.ceil(listsize / ipp);
	for (var i = 1; i <= lastpage; i++) {
		$('#nextpage').before('<li class="page-num" id="page' + i + '"><a class="page-link" href="#">' + i + '</a></li>');
	}
	$('#page1').addClass('active');
	$('#prevpage').addClass('disabled');

	$('.page-num').on('click', function() {
		$('.page-num').removeClass('active');
		$(this).addClass('active');

		dpi(lastpage);
	});

	$('#prevpage').on('click', function() {
		if (!$(this).hasClass('disabled')) {
			$('.page-num.active').prev().addClass('active');
			$('.page-num.active').eq('1').removeClass('active');
			dpi(lastpage);
		}
	});

	$('#nextpage').on('click', function() {
		if (!$(this).hasClass('disabled')) {
			$('.page-num.active').next().addClass('active');
			$('.page-num.active').eq('0').removeClass('active');
			dpi(lastpage);
		}

		reqlist('admin', 0, 10);
	})



});

/** Disable page-item */
function dpi(lastpage) {
	if ($('#page1').attr('class') == 'page-num active') {
		$('#prevpage').addClass('disabled');
	} else {
		$('#prevpage').removeClass('disabled');
	}
	if ($('#page' + lastpage).attr('class') == 'page-num active') {
		$('#nextpage').addClass('disabled');
	} else {
		$('#nextpage').removeClass('disabled');
	}
};

/** request payment list */
function reqlist(uid, sin, ipp) {
	$.ajax({
		url: '/catbudget/reqlist',
		data: { 'uid': uid, 'sin': sin, 'ipp': ipp },
		success: function(data) {
			$('tr').remove('.listinfo');

			$.each(data, function(o, i) {
				$('#paymentinfo').append(
					'<tr class="listinfo">' +
						'<td>' + i.regdate + '</td>' +
						'<td><button type="button" class="btn btn-link" data-bs-toggle="modal" data-bs-target="#' + i.num + '">' + i.detail + '</button></td>' +
						'<td>' + i.price + '</td>' +
						'<td><button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#openimg">보기</button></td>' +
					'</tr>'
				);
				$('#maindiv').after(
					'<div class="modal fade" id="' + i.num + '">' +
						'<div class="modal-dialog modal-lg modal-dialog-centered">' +
							'<div class="modal-content">' +
								'<div class="modal-header">' +
									'<h4 class="modal-title">상세 보기</h4>' +
									'<button type="button" class="btn-close" data-bs-dismiss="modal"></button>' +
								'</div>' +
								'<div class="container mt-3">' +
									'<form action="/action_page.php">' +
										'<div class="mb-3 mt-3">' +
											'<label for="date">날짜:</label>' +
											'<input type="date" class="form-control" id="date" placeholder="날짜" name="date" value="' + i.regdate + '" readonly>' +
										'</div>' +
										'<div class="mb-3">' +
											'<label for="detail">내역:</label>' +
											'<input type="text" class="form-control" id="detail" placeholder="결제 내역" name="detail" value="' + i.detail + '" readonly>' +
										'</div>' +
										'<div class="mb-3">' +
											'<label for="price">금액:</label>' +
											'<input type="number" class="form-control" id="price" placeholder="금액" name="price" value="' + i.price + '" readonly>' +
										'</div>' +
										'<div class="row">' +
											'<div class="col-sm-6">' +
												'<div class="mb-3">' +
													'<label for="bill">영수증:</label>' +
													'<p></p>' +
													'<img src="https://previews.123rf.com/images/yupiramos/yupiramos1703/yupiramos170312447/74389048-%EA%B7%80%EC%97%AC%EC%9A%B4-%EC%9E%91%EC%9D%80-%EC%83%88-%EC%95%84%EC%9D%B4%EC%BD%98-%EB%B2%A1%ED%84%B0-%EC%9D%BC%EB%9F%AC%EC%8A%A4%ED%8A%B8-%EB%A0%88%EC%9D%B4-%EC%85%98-%EB%94%94%EC%9E%90%EC%9D%B8.jpg" class="mx-auto d-block img-thumbnail" alt="Cinque Terre" width="304" height="304">' +
												'</div>' +
											'</div>' +
											'<div class="col-sm-6">' +
												'<div class="mb-3">' +
													'<label for="memo">메모:</label>' +
													'<textarea class="mx-auto d-block form-control mt-3 w-75" rows="12" id="comment" name="text" readonly>' + i.memo + '</textarea>' +
												'</div>' +
											'</div>' +
										'</div>' +
										'<button type="submit" class="btn btn-primary mb-2">수정하기</button>' +
									'</form>' +
								'</div>' +
								'<div class="modal-footer">' +
									'<button type="button" class="btn btn-success">확인</button>' +
									'<button type="button" class="btn btn-danger" data-bs-dismiss="modal">닫기</button>' +
								'</div>' +
							'</div>' +
						'</div>' +
					'</div>'
				);
			});

			

			console.log(data);
		}
	});
};