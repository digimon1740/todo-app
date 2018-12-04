'use strict';

define(function () {

	function controller($scope, $modalInstance, board, BoardService, $route) {

		$scope.board = board;
		$scope.edit = edit;
		$scope.close = $modalInstance.close;

		function edit(board) {
			if (!board || !$.trim(board.content)) {
				alert('내용을 입력해주세요.');
				return;
			}
			if (!confirm('내용을 수정하시겠습니까?')) {
				return;
			}
			board.$update({
				id: board.id
			}, () => {
				$modalInstance.close();
				$route.reload();
			});
		}
	}

	controller.$inject = ['$scope', '$modalInstance', 'board', 'BoardService', '$route'];
	return controller;

});