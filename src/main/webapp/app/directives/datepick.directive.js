"use strict";

define(['angular', 'i18n!i18n-nls/app/nls/app-i18n'], function (angular, i18n) {

	let directive = function (ToastService) {
		return function (scope, elm, attrs) {
			let $this = $('#' + elm[0].id);

			$this.datepick({
				dateFormat: 'yy-mm-dd',
				yearRange: 'c-50:c+5',
				closeAtTop: false
			});

			$this.change(function ($event) {
				let name = $this.attr('name');
				if (name == 'withdrawStartDate' || name == 'withdrawEndDate') {
					let selectedDate = this.value;
					if (!selectedDate) {
						return false;
					}
					selectedDate = selectedDate.replace(/-/gi, '');
					let now = new Date();
					let year = '' + now.getFullYear();
					let month = now.getMonth() + 1;
					if (month < 10) {
						month = '0' + month;
					}
					let day = '' + now.getDate();
					let currentDate = year + month + day;
					if (parseInt(selectedDate) < parseInt(currentDate)) {
						ToastService.warning(i18n.startDateCanNotBeEarlierThanToday);
						$this.val(year + '-' + month + '-' + day);
						return false;
					}
				}
			});
		};
	};

	directive.$inject = ['ToastService'];
	return directive;
});