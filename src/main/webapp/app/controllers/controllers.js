'use strict';

define(function (require) {

	const angular = require('angular'),
		services = require('services/services'),
		controllers = angular.module('app.controllers', ['app.services']);

	controllers.controller('InitCtrl', require('controllers/init.controller'));

	// Board
	controllers.controller('BoardCtrl', require('controllers/board/board.controller'));

	controllers.run(['$rootScope', function ($rootScope) {
		$rootScope.sampleParam = "value";
	}]);

	return controllers;
});