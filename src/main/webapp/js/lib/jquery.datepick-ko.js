/* Korean initialisation for the jQuery calendar extension. */
/* Written by DaeKwon Kang (ncrash.dk@gmail.com). */
(function($) {
	$.datepick.regional['ko'] = {
		clearText: '지우기', clearStatus: '',
		closeText: '닫기', closeStatus: '',
		prevText: '◀', prevStatus: '',
		prevBigText: '&#x3c;&#x3c;', prevBigStatus: '',
		nextText: '▶', nextStatus: '',
		nextBigText: '&#x3e;&#x3e;', nextBigStatus: '',
		currentText: '오늘', currentStatus: '',
		monthNames: ['1월','2월','3월','4월','5월','6월', '7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1월','2월','3월','4월','5월','6월', '7월','8월','9월','10월','11월','12월'],
		monthStatus: '', yearStatus: '',
		weekHeader: '주', weekStatus: '',
		dayNames: ['일','월','화','수','목','금','토'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		dayStatus: 'DD', dateStatus: 'D, M d',
		dateFormat: 'yy-mm-dd', firstDay: 0,
		initStatus: '', isRTL: false,
		showMonthAfterYear: true, yearSuffix: ''};
	$.datepick.setDefaults($.datepick.regional['ko']);
})(jQuery);
