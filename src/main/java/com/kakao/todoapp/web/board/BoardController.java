package com.kakao.todoapp.web.board;

import com.kakao.todoapp.core.domain.board.Board;
import com.kakao.todoapp.core.domain.user.User;
import com.kakao.todoapp.core.dto.board.BoardDto;
import com.kakao.todoapp.core.service.board.BoardService;
import com.kakao.todoapp.core.service.user.UserService;
import com.kakao.todoapp.utils.mapper.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping(path = "/boards")
public class BoardController {

	private UserService userService;

	private BoardService boardService;

	private ModelMapperUtils modelMapperUtils;

	@Autowired
	public BoardController(UserService userService,
	                       BoardService boardService,
	                       ModelMapperUtils modelMapperUtils) {
		this.userService = userService;
		this.boardService = boardService;
		this.modelMapperUtils = modelMapperUtils;
	}

	@GetMapping
	public ResponseEntity<List<?>> findAll(Principal principal) {
		User user = userService.findByPrincipal(principal);
		List<?> boards = modelMapperUtils.convertToDto(boardService.findAllByUserIdOrderByOrdinal(user.getId()), List.class);
		return new ResponseEntity<>(boards, HttpStatus.OK);
	}

	@PostMapping
	ResponseEntity<BoardDto> create(Principal principal, @RequestBody BoardDto boardDto) {
		User user = userService.findByPrincipal(principal);
		boardService.create(modelMapperUtils.convertToEntity(boardDto, Board.class), user);
		return new ResponseEntity<>(boardDto, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	ResponseEntity<BoardDto> modify(Principal principal, @RequestBody BoardDto boardDto) {
		User user = userService.findByPrincipal(principal);
		boardService.modify(modelMapperUtils.convertToEntity(boardDto, Board.class), user.getId());
		return new ResponseEntity<>(boardDto, HttpStatus.ACCEPTED);
	}

	@PutMapping(value = "/{id}/up")
	ResponseEntity<Void> up(Principal principal, @PathVariable("id") Long id) {
		User user = userService.findByPrincipal(principal);
		boardService.up(id, user.getId());
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@PutMapping(value = "/{id}/down")
	ResponseEntity<Void> down(Principal principal, @PathVariable("id") Long id) {
		User user = userService.findByPrincipal(principal);
		boardService.down(id, user.getId());
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> delete(Principal principal, @PathVariable("id") Long id) {
		User user = userService.findByPrincipal(principal);
		boardService.delete(id, user.getId());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


}
