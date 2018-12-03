'use strict';

define(['angular', 'i18n!i18n-nls/app/nls/app-i18n', 'controllers/controllers', 'filters/filters', 'services/services', 'directives/directives',
	'angular.resource', 'angular.InfiniteScroll', 'angular.stomp'], function (angular, i18n) {

	const dependencies = ['ngRoute', 'ngResource', 'ui.bootstrap', 'infinite-scroll', 'ngStomp', 'app.controllers', 'app.filters', 'app.services', 'app.directives'];

	const app = angular.module('app', dependencies);

	app.run(($rootScope, $location) => {

		$(function () {
			let layoutScripts = [
				"assets/javascript/app/helpers.js",
				"assets/javascript/app/layoutControl.js",
				"assets/javascript/app/rightSidebar.js",
				"assets/javascript/app/sidebar.js",
				"assets/javascript/app/main.js",
				"assets/vendor/js/highstock.min.js",
				"assets/javascript/highchart-themes/highcharts&highstock-theme.js",
			];

			layoutScripts.forEach((layoutScript) => {
				let script = document.createElement('script');
				script.src = layoutScript;
				script.async = false;
				document.body.appendChild(script);
			});

			// setTimeout(() => {
			// 	$('a.action-toggle-sidebar-slim').click();
			// }, 500);
		}());
	});

	return app;
});
