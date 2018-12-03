// 'use strict';

define(['app'], function (app) {

	app.config(['$routeProvider', ($routeProvider) => {
		$routeProvider.when('/boards', {
			templateUrl: '../partial/board/board.html',
			controller: 'BoardCtrl'
		}).otherwise({
			redirectTo: '/boards'
		});
	}]);
});
