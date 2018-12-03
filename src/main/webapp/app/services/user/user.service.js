'use strict';

define(['angular'], function (angular) {

	let factory = function ($resource) {
		return $resource('/users/me', {}, {
			get: {
				isArray: false,
			},
		});
	};

	factory.$inject = ['$resource'];
	return factory;
});