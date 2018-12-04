package com.kakao.todoapp.core.service.board;

import com.kakao.todoapp.core.domain.board.Board;
import com.kakao.todoapp.core.domain.user.Role;
import com.kakao.todoapp.core.domain.user.User;
import com.kakao.todoapp.core.repository.board.BoardRepository;
import com.kakao.todoapp.core.service.message.MessageSourceService;
import com.kakao.todoapp.web.error.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@Slf4j
@RunWith(SpringRunner.class)
public class BoardServiceTests {

	@MockBean
	private MessageSourceService messageSourceService;

	@MockBean
	private BoardRepository boardRepository;

	private BoardService boardService;

	private static final String USER_NAME = "user";

	private static final String CONTENT = "내일까지 숙제해야함";

	@Before
	public void setUp() {
		boardService = new BoardService(boardRepository, messageSourceService);
	}

	@Test
	public void findByIdAndUserIdTest() {
		long userId = 1L, boardId = 1L;
		User user = new User(USER_NAME, "password", Role.ROLE_USER);
		given(this.boardRepository.findByIdAndUserId(boardId, userId))
			.willReturn(Optional.of(new Board(user, CONTENT, 0)));

		Board actual = boardService.findByIdAndUserId(boardId, userId);

		assertThat(actual).isNotNull();
		assertThat(actual.getContent()).isEqualTo(CONTENT);
		assertThat(actual.getOrdinal()).isEqualTo(0);

		log.info("actual : {}", actual);
	}

	@Test
	public void findByIdAndUserIdOrElseThrowTest() {
		long userId = 1L, boardId = 1L;
		User user = new User(USER_NAME, "password", Role.ROLE_USER);
		given(this.boardRepository.findByIdAndUserId(boardId, userId))
			.willReturn(Optional.of(new Board(user, CONTENT, 0)));

		assertThatThrownBy(() -> boardService.findByIdAndUserIdOrElseThrow(1L, 2L))
			.isInstanceOf(ResourceNotFoundException.class);
	}

	@Test
	public void findAllByUserIdOrderByOrdinalTest() {
		long userId = 1L;
		User user = new User(USER_NAME, "password", Role.ROLE_USER);
		given(this.boardRepository.findAllByUserIdOrderByOrdinal(userId))
			.willReturn(Collections.singletonList(new Board(user, CONTENT, 0)));

		List<Board> actual = boardService.findAllByUserIdOrderByOrdinal(userId);
		assertThat(actual).size().isEqualTo(1);
		assertThat(actual.get(0).getContent()).isEqualTo(CONTENT);
		assertThat(actual.get(0).getOrdinal()).isEqualTo(0);
		assertThat(actual.get(0).getUser()).isNotNull();
		assertThat(actual.get(0).getUser()).isEqualTo(user);

		log.info("actual : {}", actual);
	}

	@Test
	public void createTest() {
		User user = new User(USER_NAME, "password", Role.ROLE_USER);
		Board actual = new Board(user, CONTENT, 0);
		given(this.boardRepository.save(actual)).willReturn(actual);

		Board board = boardService.create(actual, user);

		assertThat(board).isNotNull();
		assertThat(board.getContent()).isEqualTo(CONTENT);
		assertThat(board.getOrdinal()).isEqualTo(1);
		assertThat(board.getUser()).isNotNull();
		assertThat(board.getUser()).isEqualTo(user);

		log.info("board : {}", board);
	}

	@Test
	public void modifyTest() {
		long userId = 1L, boardId = 1L;
		User user = new User(USER_NAME, "password", Role.ROLE_USER);
		given(this.boardRepository.findByIdAndUserId(boardId, userId))
			.willReturn(Optional.of(new Board(user, CONTENT, 0)));

		String newContent = "새로운 내용";
		Board actual = new Board(boardId, user, newContent, 0, null, null);
		given(this.boardRepository.save(actual)).willReturn(actual);

		Board board = boardService.modify(actual, 1L);

		assertThat(board).isNotNull();
		assertThat(board.getContent()).isEqualTo(newContent);
		assertThat(board.getUser()).isNotNull();
		assertThat(board.getUser()).isEqualTo(user);

		log.info("board : {}", board);
	}
}
