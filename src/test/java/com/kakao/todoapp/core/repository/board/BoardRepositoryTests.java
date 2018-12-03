package com.kakao.todoapp.core.repository.board;

import com.kakao.todoapp.core.domain.board.Attachment;
import com.kakao.todoapp.core.domain.board.Attachments;
import com.kakao.todoapp.core.domain.board.Board;
import com.kakao.todoapp.core.domain.user.Role;
import com.kakao.todoapp.core.domain.user.User;
import com.kakao.todoapp.core.repository.user.UserRepository;
import com.kakao.todoapp.utils.crypto.PasswordEncodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
		board1.setAttachments(new Attachments(getAttachments()));
		this.entityManager.persist(board1);

		Board board2 = new Board();
		board2.setContent("[두번째] 맛있게 밥먹기!!");
		board2.setOrdinal(2);
		board2.setAttachments(null);
		this.entityManager.persist(board2);
	}

	private List<Attachment> getAttachments() {
		Attachment attachment1 = new Attachment();
		attachment1.setRealName("hello.jpg");
		attachment1.setName(UUID.randomUUID().toString());
		attachment1.setContentType("image/jpg");
		attachment1.setSize(10000);

		Attachment attachment2 = new Attachment();
		attachment2.setRealName("bye.png");
		attachment2.setName(UUID.randomUUID().toString());
		attachment2.setContentType("image/png");
		attachment2.setSize(20000);

		List<Attachment> attachments = new ArrayList<>();
		attachments.add(attachment1);
		attachments.add(attachment2);
		return attachments;
	}

	@Test
	public void findBoardShouldReturnBoard() throws Exception {
		Board actual = this.boardRepository.findById(1L).orElse(null);

		assertThat(actual).isNotNull();
		assertThat(actual.getContent()).isEqualTo("[첫번째] 열심히 공부하기!!");
		assertThat(actual.getAttachments()).isNotNull();
		assertThat(actual.getOrdinal()).isEqualTo(1);

		log.info("actual board : {}", actual);
	}

	@Test
	public void findAttachmentShouldReturnAttachment() throws Exception {
		List<Board> boards = this.boardRepository.findAll();
		assertThat(boards).isNotNull();
		assertThat(boards.size()).isGreaterThan(0);

		Board board = boards.stream()
			.filter(b -> b.getContent().equals("[첫번째] 열심히 공부하기!!"))
			.findFirst().orElse(null);
		assertThat(board).isNotNull();
		assertThat(board.getAttachments()).isNotNull();

		Attachment actual = board.getAttachments().findFirstByRealName("hello.jpg");
		assertThat(actual).isNotNull();
		assertThat(actual.getContentType()).isEqualTo("image/jpg");

		log.info("actual attachment : {}", actual);
	}
}
