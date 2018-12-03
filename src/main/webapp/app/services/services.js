'use strict';

define(function (require) {

	let angular = require('angular'),
		config = require('config'),
		services = angular.module('app.services', ['app.config']);

	// user
	services.factory('UserService', require('services/user/user.service'));

	// board
	services.factory('BoardService', require('services/board/board.service'));
	services.factory('BoardMovingService', require('services/board/board.moving.service'));

	return services;
});