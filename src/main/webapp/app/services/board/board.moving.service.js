'use strict';

define(['angular'], function (angular) {

	let factory = function ($resource) {
		return $resource('/boards/:id/:move', {}, {
			move: {
				method: 'PUT',
				params: {id: '@id', move: '@move'}
			},
		});
	};

	factory.$inject = ['$resource'];
	return factory;
});