'use strict';

define(function () {

	function controller($scope, $routeParams, UserService, BoardService, BoardMovingService, $modal) {
		$scope.boards = [];
		$scope.emptyBoard = {
			content: '',
			attachments: null
		};

		$scope.add = add;
		$scope.remove = remove;
		$scope.up = up;
		$scope.down = down;
		$scope.editView = editView;

		function fetchBoards() {
			BoardService.list({}, (data) => {
				$scope.boards = data;
				$scope.emptyBoard = {
					content: '',
					attachments: null
				};
			});
		}

		fetchBoards();

		function add(emptyBoard) {
			if (!emptyBoard || !$.trim(emptyBoard.content)) {
				alert('내용을 입력해 주세요.');
				return;
			}
			BoardService.create(emptyBoard, fetchBoards);
		}

		function remove($event, board) {
			$event && $event.preventDefault();
			if (!board || !board.id) {
				return;
			}
			if (!confirm('삭제하시겠습니까?')) {
				return;
			}
			board.$delete({id: board.id}, fetchBoards);
		}

		function up() {
			let $checkbox = $('input[type=checkbox]:checked');
			if ($checkbox.length === 0 || !$checkbox.val()) {
				return;
			}

			BoardMovingService.move({
				id: $checkbox.val(),
				move: 'up'
			}, fetchBoards);
		}

		function down() {
			let $checkbox = $('input[type=checkbox]:checked');
			if ($checkbox.length === 0 || !$checkbox.val()) {
				return;
			}

			BoardMovingService.move({
				id: $checkbox.val(),
				move: 'down'
			}, fetchBoards);
		}

		function editView(board) {
			$modal.open({
				templateUrl: '../../../partial/board/edit.html',
				controller: 'BoardEditCtrl',
				windowClass: 'modal inmodal fade',
				size: 'lg',
				resolve: {
					board: () => board,
				}
			});
		}


	}

	controller.$inject = ['$scope', '$routeParams', 'UserService', 'BoardService', 'BoardMovingService', '$modal'];
	return controller;

});