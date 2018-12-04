package com.kakao.todoapp.core.repository.board;

import com.kakao.todoapp.core.domain.board.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class BoardRepositoryTests {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Before
	public void setUp() {
		Board board1 = new Board();
		board1.setContent("[첫번째] 열심히 공부하기!!");
		board1.setOrdinal(1);
		this.entityManager.persist(board1);

		Board board2 = new Board();
		board2.setContent("[두번째] 맛있게 밥먹기!!");
		board2.setOrdinal(2);
		this.entityManager.persist(board2);
	}

	@Test
	public void findBoardShouldReturnBoard() throws Exception {
		Board actual = this.boardRepository.findById(1L).orElse(null);

		assertThat(actual).isNotNull();
		assertThat(actual.getContent()).isEqualTo("[첫번째] 열심히 공부하기!!");
		assertThat(actual.getOrdinal()).isEqualTo(1);

		log.info("actual board : {}", actual);
	}

	@Test
	public void findBoardsShouldReturnBoards() {
		List<Board> actual = Optional.of(this.boardRepository.findAll()).orElse(null);

		assertThat(actual).isNotNull();
		assertThat(actual.size()).isGreaterThan(0);
		assertThat(actual.size()).isEqualTo(2);

		log.info("actual board : {}", actual);
	}
}
