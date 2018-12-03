"use strict";

define(['angular'], function (angular) {

	let directive = function () {
		return {
			restrict: 'A',
			link: function (scope, element, attrs) {
				let onChangeHandler = scope.$eval(attrs.customOnChange);
				element.bind('change', onChangeHandler);
			}
		};
	};

	directive.$inject = [];
	return directive;
});