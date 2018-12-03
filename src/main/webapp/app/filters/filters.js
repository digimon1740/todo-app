// define(['angular', 'filters/owner.search.filter', 'services/services'],
define(['angular', 'services/services'],
	function (angular) {
		'use strict';

		var filters = angular.module('app.filters', ['app.services']);

		// filters.filter('OwnerSearch', OwnerSearchFilter);

		return filters;

	});