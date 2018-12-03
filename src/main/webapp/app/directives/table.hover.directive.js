"use strict";

define(['angular'], function (angular) {

	let directive = function () {
		return {
			restrict: 'A',
			link: function (scope, element, attrs) {
				let className = attrs['tableHover'];
				element.on('mouseenter', function () {
					element.addClass(className);
				});
				element.on('mouseleave', function () {
					element.removeClass(className);
				});
			}
		};
	};

	directive.$inject = [];
	return directive;
});