"use strict";

define(['angular'], function (angular) {

	let directive = function () {
		return {
			restrict: 'A',
			link: function (scope, element, attrs) {
				element.bind("keydown keypress", function (event) {
					if (event.which === 13) {
						scope.$apply(function () {
							scope.$eval(attrs.enter);
						});
						event.preventDefault();
					}
				});
			}
		};
	};

	directive.$inject = [];
	return directive;
});