'use strict';

define(function () {

	function controller($rootScope, $scope, $routeParams, UserService) {

		$scope.user = {};

		$scope.goToMain = goToMain;

		UserService.get({}, (data) => {
			$scope.user = data;
		});

		function goToMain() {
			location.href = '/main';
		}
	}

	controller.$inject = ['$rootScope', '$scope', '$routeParams', 'UserService'];
	return controller;

});