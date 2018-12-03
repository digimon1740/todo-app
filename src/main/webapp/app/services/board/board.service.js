'use strict';

define(['angular'], function (angular) {

	let factory = function ($resource) {
		return $resource('/boards/:id', {}, {
			list: {
				isArray: true,
			},
			get: {
				isArray: false,
			},
			create: {
				method: 'POST'
			},
			update: {
				method: 'PUT'
			},
			remove : {
				method : 'DELETE'
			}
		});
	};

	factory.$inject = ['$resource'];
	return factory;
});