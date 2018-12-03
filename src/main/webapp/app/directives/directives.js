
define(function (require) {

	'use strict';

	let angular = require('angular'),
		services = require('services/services'),
		directives = angular.module('app.directives', ['app.services']);

	directives.directive('datepick', require('directives/datepick.directive'));
	directives.directive('customOnChange', require('directives/custom.change.directive'));
	directives.directive('tableHover', require('directives/table.hover.directive'));
	directives.directive('enter', require('directives/enter.directive'));

	return directives;
});